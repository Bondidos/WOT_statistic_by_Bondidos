package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.repository.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.domain.Repository
import javax.inject.Inject

class UseCaseSaveUser @Inject constructor(private val repository: Repository) {

    fun execute(){
       // db.saveUser()
    }
}