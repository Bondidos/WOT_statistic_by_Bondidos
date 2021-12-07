package com.bondidos.wotstatisticbybondidos.domain.entityes

sealed class MultiViewModel{

    data class Banner (
        val header: String,
        val image: Any?
    ): MultiViewModel()

    data class CardWithText (
        val header: String,
        val text: String
    ): MultiViewModel()

    data class CardWithImage (
        val header: String,
        val image: Int?
    ): MultiViewModel()

    data class AchieveCard (
        val scored: String,
        val image: String
    ): MultiViewModel()
}
