package id.renaldi.mandiritest.data.repository.profile

import com.google.gson.Gson
import id.renaldi.mandiritest.data.local.auth.AuthPreferences
import id.renaldi.mandiritest.data.remote.auth.dto.UserResponseDto
import id.renaldi.mandiritest.data.remote.auth.mapper.toDomain
import id.renaldi.mandiritest.domain.model.User
import kotlinx.coroutines.flow.first

class ProfileRepository(
    private val authPreferences: AuthPreferences,
    private val gson: Gson
) {
    suspend fun getUserProfile(): User {
        val data = authPreferences.getUserData.first()
        val user = gson.fromJson(data, UserResponseDto::class.java)
        return user.toDomain()
    }
}