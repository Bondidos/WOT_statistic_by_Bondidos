package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.squareup.moshi.Json

data class Private(
    @Json(name = "ban_info")
    val banInfo: Any,
    @Json(name = "ban_time")
    val banTime: Any,
    @Json(name = "battle_life_time")
    val battleLifeTime: Int,
    @Json(name = "bonds")
    val bonds: Int,
    @Json(name = "credits")
    val credits: Int,
    @Json(name = "free_xp")
    val freeXp: Int,
    @Json(name = "gold")
    val gold: Int,
    @Json(name = "is_bound_to_phone")
    val isBoundToPhone: Boolean,
    @Json(name = "is_premium")
    val isPremium: Boolean,
    @Json(name = "premium_expires_at")
    val premiumExpiresAt: Int,
    @Json(name = "restrictions")
    val restrictions: Restrictions
)