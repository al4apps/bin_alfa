package com.al4apps.binlib.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.al4apps.binlib.R
import com.al4apps.binlib.domain.models.CardModel

@Composable
fun BinLookupLayout(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    onHistoryClick: () -> Unit,
    onNavigateToExternalApp: (String, UriType) -> Unit
) {
    var bin by remember { mutableStateOf("") }
    val isLoading = viewModel.isLoading.collectAsState()

    val binInfo = viewModel.cardInfo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(R.string.bin_lookup_title),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = bin,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { bin = it },
                label = { Text(stringResource(R.string.bin_text_field_hint)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (bin.isNotEmpty()) {
                        val binNumber = bin.toIntOrNull()
                        binNumber?.let { viewModel.fetchBinInfo(it) }
                    }
                },
                enabled = bin.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.lookup_button_text))
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            } else {
                binInfo.value?.let { info ->
                    BinInfoCard(
                        cardModel = info,
                        onNavigateToExternalApp = onNavigateToExternalApp
                    )
                }
            }
        }
        Button(
            onClick = onHistoryClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.view_history_button_text))
        }
    }
}

@Composable
fun BinInfoCard(
    cardModel: CardModel,
    onNavigateToExternalApp: (String, UriType) -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            cardModel.country?.name?.let {
                Text(stringResource(R.string.bin_info_country_name, it))
            }
            val lat = cardModel.country?.latitude
            val long = cardModel.country?.longitude
            if (lat != null && long != null) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.bin_info_coordinates))
                        withStyle(
                            style = SpanStyle(color = Color.Blue)
                        ) {
                            append(lat.toString())
                            append(", ")
                            append(long.toString())
                        }
                    },
                    modifier = Modifier.clickable {
                        onNavigateToExternalApp("$lat,$long", UriType.Map)
                    },
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            cardModel.type?.let { Text(stringResource(R.string.bin_info_card_type, it)) }
            cardModel.brand?.let { Text("Brand: ${cardModel.brand}") }
            cardModel.scheme?.let { Text("Scheme: ${cardModel.scheme}") }
            Spacer(modifier = Modifier.height(8.dp))
            cardModel.bank?.let { bank ->
                bank.name?.let { Text(stringResource(R.string.bin_info_bank_name, it)) }
                bank.url?.let { url ->
                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(R.string.bin_info_website))
                            withStyle(
                                style = SpanStyle(color = Color.Blue)
                            ) {
                                append(url)
                            }
                        },
                        modifier = Modifier.clickable {
                            onNavigateToExternalApp(url, UriType.Website)
                        },
                    )
                }
                bank.phone?.let { phone ->
                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(R.string.bin_info_phone))
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append(phone)
                            }
                        },
                        modifier = Modifier.clickable {
                            onNavigateToExternalApp(phone, UriType.Phone)
                        },
                    )
                }

            }
        }
    }
}



enum class UriType {
    Website, Phone, Map
}