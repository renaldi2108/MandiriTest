package id.renaldi.mandiritest.data.remote.auth.mapper

import id.renaldi.mandiritest.data.remote.auth.dto.UserResponseDto
import id.renaldi.mandiritest.domain.model.User

internal fun UserResponseDto.toDomain(): User = User(
    address = address,
    email = email,
    id = id,
    name = name,
    password = password,
    phone = phone,
    username = username
)