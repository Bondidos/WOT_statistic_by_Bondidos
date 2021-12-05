package com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi


import com.google.gson.annotations.SerializedName

data class Restrictions(
    @SerializedName("chat_ban_time")
    val chatBanTime: Any?
)