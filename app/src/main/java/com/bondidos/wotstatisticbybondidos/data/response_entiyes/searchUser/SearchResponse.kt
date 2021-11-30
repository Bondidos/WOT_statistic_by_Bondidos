package com.bondidos.wotstatisticbybondidos.data.response_entiyes.searchUser
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("status")
    val status: String
)

