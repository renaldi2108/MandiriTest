package id.renaldi.mandiritest.data.local.auth

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import id.renaldi.mandiritest.data.local.auth.util.Constants.AUTH_KEY
import id.renaldi.mandiritest.data.local.auth.util.Constants.USER_DATA
import id.renaldi.mandiritest.data.remote.auth.dto.UserResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferences(private val dataStore: DataStore<Preferences>, private val gson: Gson) {

    suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_KEY] = accessToken
        }
    }

    val getAccessToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[AUTH_KEY] ?: ""
    }

    suspend fun saveUserdata(user: UserResponseDto) {
        dataStore.edit { preferences ->
            Log.d("AUTH", "saveUserdata: $" + user)
            preferences[USER_DATA] = gson.toJson(user)
        }
    }

    suspend fun removeData() {
        dataStore.edit {
            it.clear()
        }
    }

    val getUserData: Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_DATA] ?: ""
    }
}