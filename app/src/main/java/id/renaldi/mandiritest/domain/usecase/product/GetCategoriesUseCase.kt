package id.renaldi.mandiritest.domain.usecase.product

import id.renaldi.mandiritest.data.repository.product.ProductsRepository

class GetCategoriesUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): List<String> {
        val categories = productsRepository.getProductCategories()
        return listOf("All") + categories
    }
}