package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseSaveUser @Inject constructor(private val repository: Repository) {

    suspend fun execute(url: String): Event<Boolean> = withContext(Dispatchers.IO) {
        Event(
            repository.saveUser(url)
        )
    }
}