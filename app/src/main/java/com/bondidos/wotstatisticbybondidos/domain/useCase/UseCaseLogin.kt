package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseLogin @Inject constructor(private val repository: Repository) {

    fun execute(): Flow<List<User?>> = repository.login()

}