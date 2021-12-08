package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseLogout @Inject constructor(private val repository: Repository) {

    suspend fun execute(): Boolean{
        return withContext(Dispatchers.IO){
            try {
                repository.logout()
            } catch (e: Exception){
                false
            }
        }
    }
}