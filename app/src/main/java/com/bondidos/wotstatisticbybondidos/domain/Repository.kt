package com.bondidos.wotstatisticbybondidos.domain


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

    suspend fun getUserData()

}