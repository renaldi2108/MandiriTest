package id.renaldi.mandiritest.data.local.wish_list

import androidx.room.Entity
import id.renaldi.mandiritest.data.local.wish_list.util.Constant.RATING_TABLE_NAME

@Entity(tableName = RATING_TABLE_NAME)
data class RatingEntity(
    val count: Int,
    val rate: Double
)