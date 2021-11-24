package com.bondidos.wotstatisticbybondidos.data.local_repository

import com.bondidos.wotstatisticbybondidos.domain.entityes.User

interface LocalRepository {

    fun saveUser()
    fun getUserIfExist(): User?
}