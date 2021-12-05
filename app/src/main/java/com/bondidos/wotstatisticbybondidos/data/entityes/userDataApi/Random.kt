package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class Random(
    @SerializedName("avg_damage_assisted")
    val avgDamageAssisted: Double?,
    @SerializedName("avg_damage_assisted_radio")
    val avgDamageAssistedRadio: Double?,
    @SerializedName("avg_damage_assisted_track")
    val avgDamageAssistedTrack: Double?,
    @SerializedName("avg_damage_blocked")
    val avgDamageBlocked: Double?,
    @SerializedName("battle_avg_xp")
    val battleAvgXp: Int?,
    @SerializedName("battles")
    val battles: Int?,
    @SerializedName("battles_on_stunning_vehicles")
    val battlesOnStunningVehicles: Int?,
    @SerializedName("capture_points")
    val capturePoints: Int?,
    @SerializedName("damage_dealt")
    val damageDealt: Int?,
    @SerializedName("damage_received")
    val damageReceived: Int?,
    @SerializedName("direct_hits_received")
    val directHitsReceived: Int?,
    @SerializedName("draws")
    val draws: Int?,
    @SerializedName("dropped_capture_points")
    val droppedCapturePoints: Int?,
    @SerializedName("explosion_hits")
    val explosionHits: Int?,
    @SerializedName("explosion_hits_received")
    val explosionHitsReceived: Int?,
    @SerializedName("frags")
    val frags: Int?,
    @SerializedName("hits")
    val hits: Int?,
    @SerializedName("hits_percents")
    val hitsPercents: Int?,
    @SerializedName("losses")
    val losses: Int?,
    @SerializedName("no_damage_direct_hits_received")
    val noDamageDirectHitsReceived: Int?,
    @SerializedName("piercings")
    val piercings: Int?,
    @SerializedName("piercings_received")
    val piercingsReceived: Int?,
    @SerializedName("shots")
    val shots: Int?,
    @SerializedName("spotted")
    val spotted: Int?,
    @SerializedName("stun_assisted_damage")
    val stunAssistedDamage: Int?,
    @SerializedName("stun_number")
    val stunNumber: Int?,
    @SerializedName("survived_battles")
    val survivedBattles: Int?,
    @SerializedName("tanking_factor")
    val tankingFactor: Double?,
    @SerializedName("wins")
    val wins: Int?,
    @SerializedName("xp")
    val xp: Int?
)