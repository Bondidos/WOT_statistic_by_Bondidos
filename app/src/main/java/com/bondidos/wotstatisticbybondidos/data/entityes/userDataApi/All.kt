package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi

import com.google.gson.annotations.SerializedName

data class All (
    @SerializedName("all")
    val all: Map<String,Number>
)
