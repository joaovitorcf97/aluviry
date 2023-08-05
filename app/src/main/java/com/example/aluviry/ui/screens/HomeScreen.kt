package com.example.aluviry.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aluviry.sampleData.sampleProducts
import com.example.aluviry.ui.components.ProductSection

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier)
        ProductSection(title = "Promoções", products = sampleProducts)
        ProductSection(title = "Doces", products = sampleProducts)
        ProductSection(title = "Pizzas", products = sampleProducts)
        Spacer(Modifier)
    }
}

