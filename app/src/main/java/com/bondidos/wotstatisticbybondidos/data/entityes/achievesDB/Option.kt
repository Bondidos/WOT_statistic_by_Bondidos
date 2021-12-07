package com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB


import com.squareup.moshi.Json

data class Option(
    @Json(name = "description")
    val description: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "image_big")
    val imageBig: String?,
    @Json(name = "name_i18n")
    val nameI18n: String?,
    @Json(name = "nation_images")
    val nationImages: String?
)