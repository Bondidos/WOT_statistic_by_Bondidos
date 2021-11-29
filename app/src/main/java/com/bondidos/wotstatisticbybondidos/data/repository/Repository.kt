package com.bondidos.wotstatisticbybondidos.data.repository

import com.bondidos.wotstatisticbybondidos.data.repository.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.repository.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.extensions.mapToUserList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor (
    private val networkService: WotApi,
    private val roomStorage: RoomRepositoryDao
    ) : Repository {

    override fun login(): Flow<List<User?>> = roomStorage.getUserFromCache()

    override suspend fun searchUser(search: String): List<User> {

        return withContext(Dispatchers.IO) {
            val apiData = networkService.searchUser()
            if(apiData.status == "ok") apiData.data.mapToUserList() else emptyList()
        }
    }

    override suspend fun saveUserToCash(user: User): Long = roomStorage.saveUserToCache(user)

    override fun getUserFromCache(id: Long): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserFromCache(user: User): Int {
        TODO("Not yet implemented")
    }

    override suspend fun isAchievesDataBaseExist(): Int = roomStorage.isAchievesDataBaseExist()


    override suspend fun createAchieveDataBase(achieves: List<Achieve>): List<Long> {
        return roomStorage.createAchieveDataBase(achieves)
    }


    override fun getAchievesData(achievesList: List<String>): Flow<List<Achieve>> {
        TODO("Not yet implemented")
    }
}