package com.bondidos.wotstatisticbybondidos.data.repository

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val networkService: WotApi) : Repository {

    /*@Inject
    lateinit var networkService: WotApi*/

    override suspend fun login(): User {
        TODO("Not yet implemented")
    }

    override suspend fun searchUser(search: String): List<User> {
        return mapWotUserToUser(networkService.searchUser(search = search))
    }

    private fun mapWotUserToUser(listOfWotUser: List<WotUser>) : List<User>{
        return listOfWotUser.map { it -> User(it.nickname,it.account_id, null, null) }
    }
}