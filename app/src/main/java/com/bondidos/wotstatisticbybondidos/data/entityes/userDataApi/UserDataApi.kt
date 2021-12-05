package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.squareup.moshi.Json

data class UserDataApi(
    @Json(name = "data")
    val data: Data,
    @Json(name = "meta")
    val meta: Meta,
    @Json(name = "status")
    val status: String
)