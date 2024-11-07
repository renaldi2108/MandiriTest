package id.renaldi.mandiritest.data.repository.auth

import com.oyamo.dooka_app.core.util.Resource
import id.renaldi.mandiritest.data.local.auth.AuthPreferences
import id.renaldi.mandiritest.data.remote.auth.AuthApiService
import id.renaldi.mandiritest.data.remote.auth.dto.UserResponseDto
import id.renaldi.mandiritest.data.remote.auth.request.LoginRequest
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest, rememberMe: Boolean): Resource<Unit>
    suspend fun autoLogin(): Resource<Unit>
    suspend fun logout(): Boolean
}

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val authPreferences: AuthPreferences
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest, rememberMe: Boolean): Resource<Unit> {
        Timber.d("Login called")
        return try {
            val response = authApiService.loginUser(loginRequest)
            Timber.d(response.token)
            authPreferences.saveAccessToken(response.token)
            getAllUsers(loginRequest.username)?.let { authPreferences.saveUserdata(it) }
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(message = "Could not reach the server, please check your internet connection!")
        } catch (e: HttpException) {
            Resource.Error(message = "An Unknown error occurred, please try again!")
        }
    }

    override suspend fun autoLogin(): Resource<Unit> {
        val accessToken = authPreferences.getAccessToken.first()
        Timber.d("Auto login access token: $accessToken")
        return if (accessToken != "") {
            Resource.Success(Unit)
        } else {
            Resource.Error("")
        }
    }

    override suspend fun logout(): Boolean {
        authPreferences.removeData()
        return true
    }

    private suspend fun getAllUsers(name: String): UserResponseDto? {
        val response = authApiService.getAllUsers()
        return response.find { it.username == name }
    }
}