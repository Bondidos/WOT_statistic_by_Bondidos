package com.bondidos.wotstatisticbybondidos.data

import android.util.Log
import com.bondidos.wotstatisticbybondidos.data.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.entityes.achieves.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.data.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.domain.Repository
import javax.inject.Inject

const val APPLICATION_ID = "5d489c586717c2b76ade8bea16607167"
const val ACCESS_TOKEN = "b98830431fc22d1565bfc3a84f59077f8baa0aff"
const val ACCOUNT_ID = 560508396
const val EXTRA = "statistics.epic, statistics.random"
const val FIELDS = "-statistics.frags, -statistics.clan, -statistics.regular_team, -statistics.company, -statistics.historical, -statistics.team"

class RepositoryImpl @Inject constructor (
    private val networkService: WotApi,
    private val roomStorage: RoomRepositoryDao
    ) : Repository {

    override suspend fun createAchievesDB(list: List<AchievesDBItem>) = roomStorage.createAchievesDB(list)

    override suspend fun isAchievesDataBaseExist(): Int = roomStorage.isAchievesDBExist()


    /*override suspend fun searchUser(search: String): List<User> {
            val apiData = networkService.searchUser()
            return if(apiData.status == "ok") apiData.data.mapToUserList() else emptyList()
    }

    override suspend fun saveUserToCash(user: User): Long = roomStorage.saveUserToCache(user)

    override suspend fun getUserFromCache(): User = roomStorage.getUserFromCache()

    override suspend fun deleteUserFromCache(user: User): Int = roomStorage.deleteUserFromCache(user)

    override suspend fun isAchievesDataBaseExist(): Int = roomStorage.isAchievesDataBaseExist()

    override suspend fun createAchieveDataBase(achieves: List<Achieve>): List<Long> =
        roomStorage.createAchieveDataBase(achieves)

    override suspend fun getAchievesData(achievesList: List<String>): List<Achieve> =
        roomStorage.getAchievesData(achievesList)

    override fun getAchieves(id: Int): Flow<AchievesResponse> {
        return flow{
            val i =networkService.getUserAchieves(APPLICATION_ID,id)
            emit(i)
        }.flowOn(Dispatchers.IO)
    }*/


}


