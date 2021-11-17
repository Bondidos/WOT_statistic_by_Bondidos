package com.bondidos.wotstatisticbybondidos.domain

import com.bondidos.wotstatisticbybondidos.domain.entityes.User

interface Repository {

    fun saveUser(user: User)

    fun getUser(): User

    fun getStatistic()
}