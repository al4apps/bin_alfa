package com.al4apps.binlib.presentation.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.al4apps.binlib.presentation.navigation.AppScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        BinLookupLayout(
            paddingValues = paddingValues,
            viewModel = viewModel,
            onHistoryClick = { navController.navigate(AppScreens.History.route) },
            onNavigateToExternalApp = { url, uriType ->
                navigateToExternalApp(context, url, uriType)
            }
        )
    }
}

fun navigateToExternalApp(context: Context, url: String, uriType: UriType) {
    val intent = when(uriType) {
        UriType.Website -> {
            val formattedUrl = if (!url.startsWith("http://") || !url.startsWith("https://")) {
                "https://$url"
            } else {
                url
            }
            Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrl))
        }
        UriType.Phone -> Intent(Intent.ACTION_DIAL, Uri.parse("tel:$url"))
        UriType.Map -> {
            val geoUri = Uri.parse("geo:$url")
            Intent(Intent.ACTION_VIEW, geoUri)
        }
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
