package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("status")
    val status: String?
)