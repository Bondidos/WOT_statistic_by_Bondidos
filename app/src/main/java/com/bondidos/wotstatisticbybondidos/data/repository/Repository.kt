package com.bondidos.wotstatisticbybondidos.data.repository

import com.bondidos.wotstatisticbybondidos.data.entiyes.Data
import com.bondidos.wotstatisticbybondidos.data.entiyes.WotApiResponse
import com.bondidos.wotstatisticbybondidos.data.repository.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.repository.room.AppDatabase
import com.bondidos.wotstatisticbybondidos.data.repository.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor (
    private val networkService: WotApi,
    private val roomStorage: RoomRepositoryDao
    ) : Repository {

    override suspend fun login(): User {
        TODO("Not yet implemented")
    }

    override suspend fun searchUser(search: String): List<User> {

        return withContext(Dispatchers.IO) {
            val apiData = networkService.searchUser()
            if(apiData.status == "ok") mapWotUserToUser(apiData.data) else emptyList()
        }
    }

    override suspend fun saveUserToCash(user: User): Long {
        return roomStorage.saveUser(user)
    }

    private fun mapWotUserToUser(listOfWotUser: List<Data>) : List<User>{
        return listOfWotUser.map { User(it.nickname,it.accountId, null, null) }
    }
}