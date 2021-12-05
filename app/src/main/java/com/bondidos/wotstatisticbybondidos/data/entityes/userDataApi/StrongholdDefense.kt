package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class StrongholdDefense(
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
    @SerializedName("max_damage")
    val maxDamage: Int?,
    @SerializedName("max_damage_tank_id")
    val maxDamageTankId: Int?,
    @SerializedName("max_frags")
    val maxFrags: Int?,
    @SerializedName("max_frags_tank_id")
    val maxFragsTankId: Int?,
    @SerializedName("max_xp")
    val maxXp: Int?,
    @SerializedName("max_xp_tank_id")
    val maxXpTankId: Int?,
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