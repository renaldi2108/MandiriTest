package id.renaldi.mandiritest.data.remote.product.mapper

import id.renaldi.mandiritest.data.remote.product.dto.ProductDto
import id.renaldi.mandiritest.data.remote.product.dto.RatingDto
import id.renaldi.mandiritest.domain.model.Product
import id.renaldi.mandiritest.domain.model.Rating

internal fun ProductDto.toDomain(): Product {
    return Product(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = ratingDto.toDomain(),
        title = title
    )
}

internal fun RatingDto.toDomain(): Rating {
    return Rating(
        count = count,
        rate = rate
    )
}