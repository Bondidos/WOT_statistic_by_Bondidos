package com.bondidos.wotstatisticbybondidos.data.entityes.tankImageApi


import com.google.gson.annotations.SerializedName

data class TankImage(
    @SerializedName("data")
    val `data`: Map<String,X19969>,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("status")
    val status: String?
)