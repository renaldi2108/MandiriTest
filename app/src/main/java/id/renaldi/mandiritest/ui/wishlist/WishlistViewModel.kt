package id.renaldi.mandiritest.ui.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyamo.dooka_app.core.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import id.renaldi.mandiritest.data.repository.wish_list.WishlistRepository
import id.renaldi.mandiritest.domain.model.Wishlist
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    val wishlistItems = repository.getWishlist()/*.value?.map { it.toDomain() }*/

    fun insertFavorite(wishlist: Wishlist) {
        viewModelScope.launch {
            repository.insertToWishlist(wishlist)
        }
    }

    fun inWishlist(id: Int): LiveData<Boolean> {
        return repository.inWishlist(id)
    }

    fun deleteFromWishlist(wishlist: Wishlist) {
        viewModelScope.launch {
            repository.deleteOneWishlist(wishlist)
            _eventFlow.emit(
                UiEvents.SnackbarEvent(message = "Item removed from wishlist")
            )
        }
    }

    fun deleteAllWishlist() {
        viewModelScope.launch {
            if (wishlistItems.value.isNullOrEmpty()) {
                _eventFlow.emit(
                    UiEvents.SnackbarEvent(message = "No Wishlist items found")
                )
            } else {
                repository.deleteAllWishlist()
                _eventFlow.emit(
                    UiEvents.SnackbarEvent(message = "All items deleted from your wishlist")
                )
            }
        }
    }
}