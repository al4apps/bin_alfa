package com.al4apps.binlib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.al4apps.binlib.presentation.navigation.Navigation
import com.al4apps.binlib.ui.theme.BinLibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BinLibTheme {
                Navigation()
            }
        }
    }
}