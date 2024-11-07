package id.renaldi.mandiritest.domain.usecase.cart

import com.google.gson.Gson
import com.oyamo.dooka_app.core.util.Resource
import id.renaldi.mandiritest.data.local.auth.AuthPreferences
import id.renaldi.mandiritest.data.remote.auth.dto.UserResponseDto
import id.renaldi.mandiritest.data.repository.cart.CartRepository
import id.renaldi.mandiritest.domain.model.cart.CartProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetCartItemsUseCase(
    private val cartRepository: CartRepository,
    private val authPreferences: AuthPreferences,
    private val gson: Gson

) {
    suspend operator fun invoke(): Flow<Resource<List<CartProduct>>> {
        val data = authPreferences.getUserData.first()
        val user = gson.fromJson(data, UserResponseDto::class.java)
        return cartRepository.getAllCartItems(user.id)
    }
}