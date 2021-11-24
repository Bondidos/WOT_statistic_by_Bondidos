package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.room_repository.RoomRepositoryDao
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val db: RoomRepositoryDao) {

    fun execute(){
       // db.saveUser()
    }
}