package com.bondidos.wotstatisticbybondidos.data.entityes.tankImageApi


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("page")
    val page: Any?,
    @SerializedName("page_total")
    val pageTotal: Int?,
    @SerializedName("total")
    val total: Int?
)