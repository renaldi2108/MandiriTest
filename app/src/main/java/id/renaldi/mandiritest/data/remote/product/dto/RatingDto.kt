package id.renaldi.mandiritest.data.remote.product.dto


import com.google.gson.annotations.SerializedName

data class RatingDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rate")
    val rate: Double
)