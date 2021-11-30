package com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments


import com.google.gson.annotations.SerializedName

data class UserAchievesData(
    @SerializedName("achievements")
    val achievements: Achievements,
    @SerializedName("frags")
    val frags: Frags,
    @SerializedName("max_series")
    val maxSeries: MaxSeries
)