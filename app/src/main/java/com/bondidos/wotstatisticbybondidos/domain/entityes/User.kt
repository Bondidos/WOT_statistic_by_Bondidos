package com.bondidos.wotstatisticbybondidos.domain.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    val nickname: String,
    @PrimaryKey
    val account_id: Int,
    val access_token: String?,
    val expires_at: Long?
    )