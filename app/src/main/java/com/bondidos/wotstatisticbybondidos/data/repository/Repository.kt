package com.bondidos.wotstatisticbybondidos.data.repository

import com.bondidos.wotstatisticbybondidos.data.repository.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.repository.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.AchievesResponse
import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.UserAchievesData
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.extensions.mapToUserList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val APPLICATION_ID = "5d489c586717c2b76ade8bea16607167"

class RepositoryImpl @Inject constructor (
    private val networkService: WotApi,
    private val roomStorage: RoomRepositoryDao
    ) : Repository {


    override suspend fun searchUser(search: String): List<User> {

        return withContext(Dispatchers.IO) {
            val apiData = networkService.searchUser()
            if(apiData.status == "ok") apiData.data.mapToUserList() else emptyList()
        }
    }

    override suspend fun saveUserToCash(user: User): Long = roomStorage.saveUserToCache(user)

    override fun getUserFromCache(): List<User?> = roomStorage.getUserFromCache()

    override suspend fun deleteUserFromCache(user: User): Int {
        TODO("Not yet implemented")
    }

    override suspend fun isAchievesDataBaseExist(): Int =
        roomStorage.isAchievesDataBaseExist()


    override suspend fun createAchieveDataBase(achieves: List<Achieve>): List<Long> =
        roomStorage.createAchieveDataBase(achieves)

    override fun getAchievesData(achievesList: List<String>): Flow<List<Achieve>> {
        TODO("Not yet implemented")
    }

    override fun getAchieves(id: Int): Flow<AchievesResponse> {
        return flow{
            val i =networkService.getUserAchieves(APPLICATION_ID,id)
            emit(i)
        }.flowOn(Dispatchers.IO)
    }


}


