package com.bondidos.wotstatisticbybondidos.domain.entityes

data class User(
    val nickName: String,
    val id: Long,
    val access_token: String?,
    val expires_at: Long?
    )