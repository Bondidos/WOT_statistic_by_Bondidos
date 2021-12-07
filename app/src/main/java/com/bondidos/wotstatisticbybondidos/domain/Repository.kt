package com.bondidos.wotstatisticbybondidos.domain

import android.content.Context
import com.bondidos.wotstatisticbybondidos.data.entityes.achieves.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import retrofit2.http.Url


interface Repository {

    // REMOTE API
/*
    suspend fun searchUser(search: String): List<User>

    // LocalCash
    // User table
    suspend fun saveUserToCash(user: User): Long

    suspend fun getUserFromCache(): User

    suspend fun deleteUserFromCache(user: User): Int

    // Achieves table
    suspend fun isAchievesDataBaseExist(): Int

    suspend fun createAchieveDataBase(achieves: List<Achieve>):List<Long>

    suspend fun getAchievesData(achievesList: List<String>): List<Achieve>

    fun getAchieves(id: Int): Flow<AchievesResponse>*/

    suspend fun createAchievesDB()

    suspend fun isAchievesDataBaseExist(): Boolean

    suspend fun saveUser(url: String): Boolean

    suspend fun getUser(): User?

    suspend fun fetchData(): List<MultiViewModel>

}