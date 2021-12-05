package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import javax.inject.Inject

class UseCaseSearch @Inject constructor(private val repository: Repository) {

    /*suspend fun execute(search: String) : List<User>{
        return repository.searchUser(search = search)
    }*/
}