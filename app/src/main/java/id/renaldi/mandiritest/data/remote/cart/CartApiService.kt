package id.renaldi.mandiritest.data.remote.cart

import id.renaldi.mandiritest.data.remote.cart.dto.UserCartResponseDto
import id.renaldi.mandiritest.data.remote.product.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApiService {
    @GET("carts/user/{id}")
    suspend fun cartItems(
        @Path("id") id: Int
    ): List<UserCartResponseDto>

    @GET("products/{id}")
    suspend fun product(
        @Path("id") id: Int
    ): ProductDto
}