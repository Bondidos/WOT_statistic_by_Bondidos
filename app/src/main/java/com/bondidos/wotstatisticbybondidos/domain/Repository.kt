package com.bondidos.wotstatisticbybondidos.domain

import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.entityes.User


interface Repository {

    suspend fun createAchievesDB()

    suspend fun isAchievesDataBaseExist(): Boolean

    suspend fun saveUser(url: String): Boolean

    suspend fun getUser(): User?

    suspend fun fetchData(): List<MultiViewModel>

    suspend fun fetchAchieves(): List<MultiViewModel>

    suspend fun logout(): Boolean
/*
    suspend fun clearDatabase()*/
}