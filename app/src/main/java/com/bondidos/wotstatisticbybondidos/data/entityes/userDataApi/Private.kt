package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class Private(
    @SerializedName("ban_info")
    val banInfo: Any?,
    @SerializedName("ban_time")
    val banTime: Any?,
    @SerializedName("battle_life_time")
    val battleLifeTime: Int?,
    @SerializedName("bonds")
    val bonds: Int?,
    @SerializedName("credits")
    val credits: Int?,
    @SerializedName("free_xp")
    val freeXp: Int?,
    @SerializedName("gold")
    val gold: Int?,
    @SerializedName("is_bound_to_phone")
    val isBoundToPhone: Boolean?,
    @SerializedName("is_premium")
    val isPremium: Boolean?,
    @SerializedName("premium_expires_at")
    val premiumExpiresAt: Int?,
    @SerializedName("restrictions")
    val restrictions: Restrictions?
)