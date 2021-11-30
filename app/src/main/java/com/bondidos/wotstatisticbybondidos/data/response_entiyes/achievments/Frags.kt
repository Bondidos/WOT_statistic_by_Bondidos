package com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments


import com.google.gson.annotations.SerializedName

data class Frags(
    @SerializedName("beasthunter")
    val beasthunter: Int,
    @SerializedName("bruteForceMedal")
    val bruteForceMedal: Int,
    @SerializedName("crucialShotMedal")
    val crucialShotMedal: Int,
    @SerializedName("fightingReconnaissanceMedal")
    val fightingReconnaissanceMedal: Int,
    @SerializedName("fireAndSteelMedal")
    val fireAndSteelMedal: Int,
    @SerializedName("geniusForWarMedal")
    val geniusForWarMedal: Int,
    @SerializedName("guerrillaMedal")
    val guerrillaMedal: Int,
    @SerializedName("heavyFireMedal")
    val heavyFireMedal: Int,
    @SerializedName("infiltratorMedal")
    val infiltratorMedal: Int,
    @SerializedName("pattonValley")
    val pattonValley: Int,
    @SerializedName("prematureDetonationMedal")
    val prematureDetonationMedal: Int,
    @SerializedName("promisingFighterMedal")
    val promisingFighterMedal: Int,
    @SerializedName("pyromaniacMedal")
    val pyromaniacMedal: Int,
    @SerializedName("rangerMedal")
    val rangerMedal: Int,
    @SerializedName("reliableComrade")
    val reliableComrade: Int,
    @SerializedName("sentinelMedal")
    val sentinelMedal: Int,
    @SerializedName("sinai")
    val sinai: Int,
    @SerializedName("wolfAmongSheepMedal")
    val wolfAmongSheepMedal: Int
)