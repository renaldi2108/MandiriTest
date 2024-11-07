package id.renaldi.mandiritest.data.local.wish_list.mapper

import id.renaldi.mandiritest.data.local.wish_list.RatingEntity
import id.renaldi.mandiritest.data.local.wish_list.WishlistEntity
import id.renaldi.mandiritest.domain.model.Product
import id.renaldi.mandiritest.domain.model.Rating
import id.renaldi.mandiritest.domain.model.Wishlist


internal fun RatingEntity.toDomain(): Rating {
    return Rating(
        count = count,
        rate = rate
    )
}

internal fun Rating.toEntity(): RatingEntity {
    return RatingEntity(
        count = count,
        rate = rate
    )
}


internal fun Rating.toProductRating(): Rating {
    return Rating(
        count = count,
        rate = rate
    )
}

internal fun Rating.toWishlistRating(): Rating = Rating(
    count = count,
    rate = rate
)

internal fun WishlistEntity.toDomain(): Wishlist = Wishlist(
    image = image,
    price = price,
    title = title,
    id = id,
    category = category,
    description = description,
    rating = rating.toDomain(),
    liked = liked
)

internal fun Wishlist.toEntity(): WishlistEntity = WishlistEntity(
    image = image,
    price = price,
    title = title,
    id = id,
    category = category,
    description = description,
    rating = rating.toEntity(),
    liked = liked
)

internal fun Wishlist.toProduct(): Product = Product(
    image = image,
    price = price,
    title = title,
    id = id,
    category = category,
    description = description,
    rating = rating.toProductRating(),
)

internal fun Product.toProduct(): Wishlist = Wishlist(
    image = image,
    price = price,
    title = title,
    id = id,
    category = category,
    description = description,
    rating = rating.toWishlistRating(),
)