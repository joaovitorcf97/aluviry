package com.example.aluviry.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aluviry.model.Product
import com.example.aluviry.sampleData.sampleCandies
import com.example.aluviry.sampleData.sampleDrinks
import com.example.aluviry.sampleData.sampleProducts
import com.example.aluviry.ui.components.CardProductItem
import com.example.aluviry.ui.components.ProductSection
import com.example.aluviry.ui.components.SearchTextField

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSection(): Boolean {
        return searchText.isBlank()
    }
}

@Composable
fun HomeScreen(products: List<Product>) {
    val sections = mapOf(
        "Todos produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks,
    )

    var text by remember {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(text, true) ||
                product.description?.contains(text, true) ?: false
    }

    val searchedProducts = remember(products, text) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) + products.filter(
                containsInNameOrDescription()
            )
        } else emptyList()

    }
    val state = remember(products, text) {
        HomeScreenUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = { text = it })
    }

    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val sections = state.sections
        val text = state.searchText
        val searchedProduct = state.searchedProducts

        SearchTextField(searchText = text, onSearchChange = state.onSearchChange)
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSection()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductSection(title = title, products = products)
                    }
                }
            } else {
                items(searchedProduct) { product ->
                    CardProductItem(
                        product = product,
                        Modifier.padding(horizontal = 16.dp),
                    )
                }
            }
        }
    }
}

