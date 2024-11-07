package id.renaldi.mandiritest.domain.usecase.auth

import com.oyamo.dooka_app.core.util.Resource
import id.renaldi.mandiritest.data.repository.auth.AuthRepository

class AutoLoginUseCase(
    private val loginRepository: AuthRepository
) {
    suspend operator fun invoke(): Resource<Unit> {
        return loginRepository.autoLogin()
    }
}