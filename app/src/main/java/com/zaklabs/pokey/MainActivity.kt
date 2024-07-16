package com.zaklabs.pokey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.zaklabs.pokey.ui.screens.PokeyApp
import com.zaklabs.pokey.ui.theme.PokeyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeyTheme {
                PokeyApp()
            }
        }
    }
}
