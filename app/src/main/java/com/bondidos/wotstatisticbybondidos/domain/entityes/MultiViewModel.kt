package com.bondidos.wotstatisticbybondidos.domain.entityes

sealed class MultiViewModel{

    data class Banner (
        val header: String,
        val image: String
    ): MultiViewModel()

    data class CardWithText (
        val header: String,
        val text: String
    ): MultiViewModel()

    data class CardWithImage (
        val header: String,
        val image: String
    ): MultiViewModel()
}
