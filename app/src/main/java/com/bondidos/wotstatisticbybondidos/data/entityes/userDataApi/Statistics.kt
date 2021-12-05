package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("all")
    val all: All?,
    @SerializedName("epic")
    val epic: Epic?,
    @SerializedName("random")
    val random: Random?,
    @SerializedName("stronghold_defense")
    val strongholdDefense: StrongholdDefense?,
    @SerializedName("stronghold_skirmish")
    val strongholdSkirmish: StrongholdSkirmish?,
    @SerializedName("trees_cut")
    val treesCut: Int?
)