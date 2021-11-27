package com.bondidos.wotstatisticbybondidos.data.entiyes
import com.google.gson.annotations.SerializedName

data class WotApiResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("status")
    val status: String
)