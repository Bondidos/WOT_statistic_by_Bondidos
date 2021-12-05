package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import javax.inject.Inject

class UseCaseLogin @Inject constructor(
    private val repository: Repository
    ) {

    suspend fun execute(): Resource<User> {
        val user = repository.getUser()
        return if( user != null) Resource.success(user) else Resource.error("Can't find valid user, please login",null)
    }
}
