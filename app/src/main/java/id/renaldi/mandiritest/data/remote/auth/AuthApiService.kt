package id.renaldi.mandiritest.data.remote.auth

import id.renaldi.mandiritest.data.remote.auth.dto.UserResponseDto
import id.renaldi.mandiritest.data.remote.auth.request.LoginRequest
import id.renaldi.mandiritest.data.remote.auth.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("users/")
    suspend fun getAllUsers(): List<UserResponseDto>
}