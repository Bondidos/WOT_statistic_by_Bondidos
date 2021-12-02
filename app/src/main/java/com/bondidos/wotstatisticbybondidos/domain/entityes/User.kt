package com.bondidos.wotstatisticbybondidos.domain.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nickname: String,
    val account_id: Int,
    val access_token: String?,
    val expires_at: Long?
    )