package id.renaldi.mandiritest.ui.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyamo.dooka_app.core.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import id.renaldi.mandiritest.data.repository.auth.AuthRepository
import id.renaldi.mandiritest.data.repository.profile.ProfileRepository
import id.renaldi.mandiritest.domain.model.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _profileState = mutableStateOf(User())
    val profileState: State<User> = _profileState

    private val _logoutFlow = MutableSharedFlow<Boolean>()
    val logoutFlow = _logoutFlow.asSharedFlow()

    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch {
            _profileState.value = profileRepository.getUserProfile()
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _logoutFlow.emit(authRepository.logout())
        }
    }
}