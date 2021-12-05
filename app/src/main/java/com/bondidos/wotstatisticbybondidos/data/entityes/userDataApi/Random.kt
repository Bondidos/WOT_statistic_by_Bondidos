package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.squareup.moshi.Json

data class Random(
    @Json(name = "avg_damage_assisted")
    val avgDamageAssisted: Double,
    @Json(name = "avg_damage_assisted_radio")
    val avgDamageAssistedRadio: Double,
    @Json(name = "avg_damage_assisted_track")
    val avgDamageAssistedTrack: Double,
    @Json(name = "avg_damage_blocked")
    val avgDamageBlocked: Double,
    @Json(name = "battle_avg_xp")
    val battleAvgXp: Int,
    @Json(name = "battles")
    val battles: Int,
    @Json(name = "battles_on_stunning_vehicles")
    val battlesOnStunningVehicles: Int,
    @Json(name = "capture_points")
    val capturePoints: Int,
    @Json(name = "damage_dealt")
    val damageDealt: Int,
    @Json(name = "damage_received")
    val damageReceived: Int,
    @Json(name = "direct_hits_received")
    val directHitsReceived: Int,
    @Json(name = "draws")
    val draws: Int,
    @Json(name = "dropped_capture_points")
    val droppedCapturePoints: Int,
    @Json(name = "explosion_hits")
    val explosionHits: Int,
    @Json(name = "explosion_hits_received")
    val explosionHitsReceived: Int,
    @Json(name = "frags")
    val frags: Int,
    @Json(name = "hits")
    val hits: Int,
    @Json(name = "hits_percents")
    val hitsPercents: Int,
    @Json(name = "losses")
    val losses: Int,
    @Json(name = "no_damage_direct_hits_received")
    val noDamageDirectHitsReceived: Int,
    @Json(name = "piercings")
    val piercings: Int,
    @Json(name = "piercings_received")
    val piercingsReceived: Int,
    @Json(name = "shots")
    val shots: Int,
    @Json(name = "spotted")
    val spotted: Int,
    @Json(name = "stun_assisted_damage")
    val stunAssistedDamage: Int,
    @Json(name = "stun_number")
    val stunNumber: Int,
    @Json(name = "survived_battles")
    val survivedBattles: Int,
    @Json(name = "tanking_factor")
    val tankingFactor: Double,
    @Json(name = "wins")
    val wins: Int,
    @Json(name = "xp")
    val xp: Int
)