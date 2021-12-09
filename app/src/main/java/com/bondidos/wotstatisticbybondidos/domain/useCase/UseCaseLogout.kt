package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseLogout @Inject constructor(private val repository: Repository) {

    suspend fun execute(): Resource<User>{
        return withContext(Dispatchers.IO){
            try {
                if(repository.logout())
                    Resource.success(null)
                else Resource.error("Error while logging out",null)
            } catch (e: Exception){
                Resource.error(e.toString(),null)
            }
        }
    }
}