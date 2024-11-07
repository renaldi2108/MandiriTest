package id.renaldi.mandiritest.domain.model

import id.renaldi.mandiritest.data.remote.auth.dto.Address
import id.renaldi.mandiritest.data.remote.auth.dto.Name

data class User(
    val address: Address?= null,
    val email: String?= null,
    val id: Int?= null,
    val name: Name?= null,
    val password: String?= null,
    val phone: String?= null,
    val username: String?= null,
)
