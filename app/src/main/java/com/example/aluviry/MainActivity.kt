package com.example.aluviry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.aluviry.ui.screens.HomeScreen
import com.example.aluviry.ui.theme.AluviryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    AluviryTheme {
        Surface {
            HomeScreen()
        }
    }
}




