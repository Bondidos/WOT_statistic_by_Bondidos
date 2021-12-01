package com.bondidos.wotstatisticbybondidos.domain.entityes

data class AchievesToDisplay(
    val name: String,
    val received: Int,
    val group: String?,
    val description: String?,
    val image: String?
)