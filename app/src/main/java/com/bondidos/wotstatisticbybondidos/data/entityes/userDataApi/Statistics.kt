package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("all")
    val all: Map<String,Number?>,
    @SerializedName("epic")
    val epic: Map<String,Number>?,
    @SerializedName("random")
    val random: Map<String,Number>?,
    @SerializedName("stronghold_defense")
    val strongholdDefense: Map<String,Number>?,
    @SerializedName("stronghold_skirmish")
    val strongholdSkirmish: Map<String,Number>?,
    @SerializedName("trees_cut")
    val treesCut: Int?
)