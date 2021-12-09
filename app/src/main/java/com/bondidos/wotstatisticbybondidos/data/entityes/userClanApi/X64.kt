package com.bondidos.wotstatisticbybondidos.data.entityes.userClanApi


import com.google.gson.annotations.SerializedName

data class X64(
    @SerializedName("portal")
    val portal: String?,
    @SerializedName("wot")
    val wot: String?
)