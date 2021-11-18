package com.bondidos.wotstatisticbybondidos.data.repository

import com.bondidos.wotstatisticbybondidos.data.entiyes.WotUser
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val networkService: WotApi) : Repository {

    override suspend fun login(): User {
        TODO("Not yet implemented")
    }

    override suspend fun searchUser(search: String): List<User> {

        return withContext(Dispatchers.IO) {
            val apiData = networkService.searchUser()
            if(apiData.status == "ok") mapWotUserToUser(apiData.data) else emptyList()
        }
    }

    private fun mapWotUserToUser(listOfWotUser: List<WotUser>) : List<User>{
        return listOfWotUser.map { User(it.nickname,it.account_id, null, null) }
    }
}