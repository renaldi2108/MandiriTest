package id.renaldi.mandiritest.ui.auth.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyamo.dooka_app.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import id.renaldi.mandiritest.domain.usecase.auth.AutoLoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val autoLoginUseCase: AutoLoginUseCase
) :ViewModel() {

    private val _eventState = mutableStateOf(false)
    val eventState: State<Boolean> = _eventState

    init {
        autoLoginUser()
    }

    private fun autoLoginUser() {
        viewModelScope.launch {
            when (autoLoginUseCase()) {
                is Resource.Success -> {
                    _eventState.value = true
                }
                is Resource.Error -> {
                    _eventState.value = false
                }
                else -> {}
            }
        }
    }
}