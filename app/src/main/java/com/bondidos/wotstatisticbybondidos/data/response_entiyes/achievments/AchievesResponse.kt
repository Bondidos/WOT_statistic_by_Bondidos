package com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments


import com.google.gson.annotations.SerializedName

data class AchievesResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("status")
    val status: String
)