package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class X560508396(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("clan_id")
    val clanId: Int,
    @SerializedName("client_language")
    val clientLanguage: String,
    @SerializedName("created_at")
    val createdAt: Int,
    @SerializedName("global_rating")
    val globalRating: Int,
    @SerializedName("last_battle_time")
    val lastBattleTime: Int,
    @SerializedName("logout_at")
    val logoutAt: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("private")
    val `private`: Map<String,Any?>,
    @SerializedName("updated_at")
    val updatedAt: Int
)