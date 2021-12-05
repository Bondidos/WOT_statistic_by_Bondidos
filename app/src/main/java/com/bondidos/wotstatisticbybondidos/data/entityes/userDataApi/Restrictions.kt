package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.squareup.moshi.Json

data class Restrictions(
    @Json(name = "chat_ban_time")
    val chatBanTime: Any
)