package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.squareup.moshi.Json

data class Meta(
    @Json(name = "count")
    val count: Int
)