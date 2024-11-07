package id.renaldi.mandiritest.domain.usecase.product

import com.oyamo.dooka_app.core.util.Resource
import id.renaldi.mandiritest.data.repository.product.ProductsRepository
import id.renaldi.mandiritest.domain.model.Product
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Product>>> {
        return productsRepository.getProducts()
    }
}