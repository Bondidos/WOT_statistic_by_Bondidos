package com.bondidos.wotstatisticbybondidos.data.entiyes.searchUser
import com.bondidos.wotstatisticbybondidos.data.entiyes.achievments.Data
import com.bondidos.wotstatisticbybondidos.data.entiyes.achievments.Meta
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("status")
    val status: String
)

