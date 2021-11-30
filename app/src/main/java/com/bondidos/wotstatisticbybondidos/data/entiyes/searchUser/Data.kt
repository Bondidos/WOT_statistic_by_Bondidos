package com.bondidos.wotstatisticbybondidos.data.entiyes.searchUser

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("nickname")
    val nickname: String
)