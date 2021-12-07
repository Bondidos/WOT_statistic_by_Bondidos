package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import java.lang.Exception
import javax.inject.Inject

class UseCaseGetAchieves @Inject constructor(private val repository: Repository) {

    suspend fun execute() : Resource<List<MultiViewModel>>{
        return try{
            Resource.success(repository.fetchAchieves())
        }catch (e: Exception){
            Resource.error("Can't fetch achieves. Check connections",null)
        }
    }
}