package id.renaldi.mandiritest.data.local.wish_list.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import id.renaldi.mandiritest.data.local.wish_list.RatingEntity

@ProvidedTypeConverter
class Converters(private val gson: Gson) {

    @TypeConverter
    fun fromRating(ratingEntity: RatingEntity): String {
        return gson.toJson(ratingEntity)
    }

    @TypeConverter
    fun toRating(rating: String): RatingEntity {
        return gson.fromJson(rating, RatingEntity::class.java)
    }
}