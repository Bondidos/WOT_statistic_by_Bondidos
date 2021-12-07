package com.bondidos.wotstatisticbybondidos.data.entityes.userClanApi


import com.google.gson.annotations.SerializedName

data class ApiClanResponse(
    @SerializedName("data")
    val `data`: Map<String,X500002997?>,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("status")
    val status: String?
)