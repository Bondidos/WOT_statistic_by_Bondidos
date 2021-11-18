package com.bondidos.wotstatisticbybondidos.domain

import com.bondidos.wotstatisticbybondidos.domain.entityes.User

interface Repository {

    suspend fun login(): User

    suspend fun searchUser(search: String): List<User>
}