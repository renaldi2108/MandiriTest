package id.renaldi.mandiritest.ui.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyamo.dooka_app.core.util.Resource
import com.oyamo.dooka_app.core.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import id.renaldi.mandiritest.domain.usecase.cart.GetCartItemsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CartItemsState())
    val state: State<CartItemsState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getCartItems()
        }
    }

    private suspend fun getCartItems() {
        getCartItemsUseCase().collectLatest { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        cartItems = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                    _eventFlow.emit(
                        UiEvents.SnackbarEvent(
                            message = result.message ?: "Unknown error occurred!"
                        )
                    )
                }
            }
        }
    }
}