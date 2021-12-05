package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.squareup.moshi.Json

data class X560508396(
    @Json(name = "account_id")
    val accountId: Int,
    @Json(name = "clan_id")
    val clanId: Int,
    @Json(name = "client_language")
    val clientLanguage: String,
    @Json(name = "created_at")
    val createdAt: Int,
    @Json(name = "global_rating")
    val globalRating: Int,
    @Json(name = "last_battle_time")
    val lastBattleTime: Int,
    @Json(name = "logout_at")
    val logoutAt: Int,
    @Json(name = "nickname")
    val nickname: String,
    @Json(name = "private")
    val `private`: Private,
    @Json(name = "statistics")
    val statistics: Statistics,
    @Json(name = "updated_at")
    val updatedAt: Int
)