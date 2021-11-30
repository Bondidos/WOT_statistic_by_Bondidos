package com.bondidos.wotstatisticbybondidos.domain

import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.AchievesResponse
import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.UserAchievesData
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    // REMOTE API

    suspend fun searchUser(search: String): List<User>

    // LocalCash
    // User table
    suspend fun saveUserToCash(user: User): Long

    fun getUserFromCache(): List<User?>

    suspend fun deleteUserFromCache(user: User): Int

    // Achieves table
    suspend fun isAchievesDataBaseExist(): Int

    suspend fun createAchieveDataBase(achieves: List<Achieve>):List<Long>

    fun getAchievesData(achievesList: List<String>): Flow<List<Achieve>>

    fun getAchieves(id: Int): Flow<AchievesResponse>
}