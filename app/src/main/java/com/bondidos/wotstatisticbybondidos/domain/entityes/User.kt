package com.bondidos.wotstatisticbybondidos.domain.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val nickName: String,
    @PrimaryKey
    val id: Long,
    val access_token: String?,
    val expires_at: Long?
    )