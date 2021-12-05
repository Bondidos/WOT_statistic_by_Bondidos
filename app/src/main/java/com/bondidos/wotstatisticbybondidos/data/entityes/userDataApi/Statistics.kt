package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.squareup.moshi.Json

data class Statistics(
    @Json(name = "all")
    val all: All,
    @Json(name = "epic")
    val epic: Epic,
    @Json(name = "random")
    val random: Random,
    @Json(name = "stronghold_defense")
    val strongholdDefense: StrongholdDefense,
    @Json(name = "stronghold_skirmish")
    val strongholdSkirmish: StrongholdSkirmish,
    @Json(name = "trees_cut")
    val treesCut: Int
)