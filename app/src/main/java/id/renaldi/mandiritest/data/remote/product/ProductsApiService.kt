package id.renaldi.mandiritest.data.remote.product

import id.renaldi.mandiritest.data.remote.product.dto.ProductDto
import retrofit2.http.GET

interface ProductsApiService {

    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/categories")
    suspend fun getProductCategories(): List<String>
}