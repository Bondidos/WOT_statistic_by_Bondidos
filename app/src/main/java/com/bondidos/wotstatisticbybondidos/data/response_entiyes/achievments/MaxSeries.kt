package com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments


import com.google.gson.annotations.SerializedName

data class MaxSeries(
    @SerializedName("aimer")
    val aimer: Int,
    @SerializedName("armorPiercer")
    val armorPiercer: Int,
    @SerializedName("deathTrack")
    val deathTrack: Int,
    @SerializedName("diehard")
    val diehard: Int,
    @SerializedName("EFC2016")
    val eFC2016: Int,
    @SerializedName("handOfDeath")
    val handOfDeath: Int,
    @SerializedName("invincible")
    val invincible: Int,
    @SerializedName("tacticalBreakthrough")
    val tacticalBreakthrough: Int,
    @SerializedName("titleSniper")
    val titleSniper: Int,
    @SerializedName("victoryMarch")
    val victoryMarch: Int,
    @SerializedName("WFC2014")
    val wFC2014: Int
)