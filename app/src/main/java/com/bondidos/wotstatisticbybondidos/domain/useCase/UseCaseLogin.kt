package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UseCaseLogin @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {

    suspend fun execute(): Resource<User> {

        return withContext(Dispatchers.IO) {
            try {
                Resource.success(getAndValidUser())
            } catch (e: Exception) {
                Resource.error("Can't find valid user, please login", null)
            }
        }
    }
    private suspend fun getAndValidUser(): User{
        val user = repository.getUser()
        return if(utils.isUserValid(requireNotNull(user)))  user else throw IllegalArgumentException()
    }
}
