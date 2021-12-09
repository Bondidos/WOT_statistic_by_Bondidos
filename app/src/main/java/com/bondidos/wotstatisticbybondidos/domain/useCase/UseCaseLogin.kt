package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UseCaseLogin @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(): Resource<User> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.success(repository.getUser())
            } catch (e: Exception) {
                Resource.error("Can't find valid user, please login", null)
            }
        }
    }
}
