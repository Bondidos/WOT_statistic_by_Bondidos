package com.bondidos.wotstatisticbybondidos.data.entityes.achievesApi

data class ApiAchievesResponse(
    val `data`: Map<String,UserAchieves>,
    val meta: Meta,
    val status: String
)