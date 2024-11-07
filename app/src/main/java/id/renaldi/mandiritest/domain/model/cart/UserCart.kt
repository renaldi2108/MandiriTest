package id.renaldi.mandiritest.domain.model.cart

data class UserCart(
    val date: String,
    val id: Int,
    val cartProducts: List<CartProduct>,
    val userId: Int,
)
