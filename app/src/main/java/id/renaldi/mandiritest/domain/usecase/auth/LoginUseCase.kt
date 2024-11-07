package id.renaldi.mandiritest.domain.usecase.auth

import id.renaldi.mandiritest.data.remote.auth.request.LoginRequest
import id.renaldi.mandiritest.data.repository.auth.AuthRepository
import id.renaldi.mandiritest.domain.model.login.LoginResult

class LoginUseCase(
    private val loginRepository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        rememberMe: Boolean
    ): LoginResult {
        val usernameError = if (username.isBlank()) "User name cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null


        if (usernameError != null) {
            return LoginResult(
                usernameError = usernameError
            )
        }

        if (passwordError != null) {
            return LoginResult(
                passwordError = passwordError
            )
        }


        val loginRequest = LoginRequest(
            username = username.trim(),
            password = password.trim()
        )

        return LoginResult(
            result = loginRepository.login(loginRequest, rememberMe)
        )
    }
}