package com.bondidos.wotstatisticbybondidos.data.entityes.tankImageApi


import com.google.gson.annotations.SerializedName

data class ImagesX(
    @SerializedName("big_icon")
    val bigIcon: String?,
    @SerializedName("contour_icon")
    val contourIcon: String?,
    @SerializedName("small_icon")
    val smallIcon: String?
)