package id.renaldi.mandiritest.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyamo.dooka_app.core.util.Resource
import com.oyamo.dooka_app.core.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import id.renaldi.mandiritest.domain.usecase.product.GetCategoriesUseCase
import id.renaldi.mandiritest.domain.usecase.product.GetProductsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) :
    ViewModel() {

    private val _selectedCategory = mutableStateOf("All")
    val selectedCategory: State<String> = _selectedCategory
    fun setCategory(value: String) {
        _selectedCategory.value = value
    }

    private val _productsState = mutableStateOf(ProductsState())
    val productsState: State<ProductsState> = _productsState

    private val _categoriesState = mutableStateOf(emptyList<String>())
    val categoriesState: State<List<String>> = _categoriesState

    private val _bannerImageState =
        mutableStateOf("https://unsplash.com/photos/sUlR4Iul-9c/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjY0MjY0Mzk5&force=true&w=640")
    val bannerImageState: State<String> = _bannerImageState

    private val _searchTerm = mutableStateOf("")
    val searchTerm: State<String> = _searchTerm

    fun setSearchTerm(term: String) {
        _searchTerm.value = term
    }

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    init {
        getProducts(selectedCategory.value)
        getCategories()

    }

    private fun getCategories() {
        viewModelScope.launch {
            _categoriesState.value = getCategoriesUseCase()
        }
    }

    fun getProducts(category: String) {
        viewModelScope.launch {
            getProductsUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        if (category == "All") {
                            _productsState.value = productsState.value.copy(
                                products = result.data ?: emptyList(),
                                isLoading = false
                            )
                        } else {
                            _productsState.value = productsState.value.copy(
                                products = result.data?.filter { it.category == category }
                                    ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _productsState.value = productsState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _productsState.value = productsState.value.copy(
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
}