package id.renaldi.mandiritest.data.repository.product

import com.oyamo.dooka_app.core.util.Resource
import id.renaldi.mandiritest.data.remote.product.ProductsApiService
import id.renaldi.mandiritest.data.remote.product.mapper.toDomain
import id.renaldi.mandiritest.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

interface ProductsRepository {
    suspend fun getProducts(): Flow<Resource<List<Product>>>
    suspend fun getProductCategories(): List<String>
}

class ProductsRepositoryImpl(private val productsApiService: ProductsApiService) :
    ProductsRepository {
    override suspend fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            val response = productsApiService.getProducts()
            emit(Resource.Success(response.map { it.toDomain() }))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Oops, something went wrong!"))
        }
    }

    override suspend fun getProductCategories(): List<String> {
        return productsApiService.getProductCategories()
    }
}