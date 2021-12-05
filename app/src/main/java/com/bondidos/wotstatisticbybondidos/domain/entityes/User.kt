package com.bondidos.wotstatisticbybondidos.domain.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(

    val nickname: String,
    val account_id: Int,
    val access_token: String?,
    val expires_at: Long?
    )