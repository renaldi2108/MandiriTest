package id.renaldi.mandiritest.ui.cart

import id.renaldi.mandiritest.domain.model.cart.CartProduct

data class CartItemsState(
    val cartItems: List<CartProduct> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)