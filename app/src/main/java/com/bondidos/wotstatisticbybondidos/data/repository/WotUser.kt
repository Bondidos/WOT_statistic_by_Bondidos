package com.bondidos.wotstatisticbybondidos.data.repository

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListDataUsers(
    @Json(name = "data") val list: List<WotUser>
)

@JsonClass(generateAdapter = true)
data class WotUser (
    @Json(name = "nickname") val nickname : String,
    @Json(name = "account_id") val account_id : Long
)