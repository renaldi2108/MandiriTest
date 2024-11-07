package id.renaldi.mandiritest.ui.main

import id.renaldi.mandiritest.domain.model.Product

data class ProductsState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)