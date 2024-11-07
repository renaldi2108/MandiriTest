package id.renaldi.mandiritest.data.local.wish_list

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.renaldi.mandiritest.data.local.wish_list.util.Constant.WISHLIST_TABLE_NAME

@Entity(tableName = WISHLIST_TABLE_NAME)
data class WishlistEntity(
    val image: String,
    val price: Double,
    val title: String,
    val category: String,
    val description: String,
    val rating: RatingEntity,
    val liked: Boolean = false,
    @PrimaryKey val id: Int,
)