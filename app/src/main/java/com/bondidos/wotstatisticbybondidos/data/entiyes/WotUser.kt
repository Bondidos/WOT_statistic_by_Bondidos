package com.bondidos.wotstatisticbybondidos.data.entiyes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @Json(name = "status") val status: String,
    @Json(name = "data") val data: List<WotUser>
)

@JsonClass(generateAdapter = true)
data class WotUser (
    @Json(name = "nickname")
    val nickname : String,
    @Json(name = "account_id")
    val account_id : Long
)

