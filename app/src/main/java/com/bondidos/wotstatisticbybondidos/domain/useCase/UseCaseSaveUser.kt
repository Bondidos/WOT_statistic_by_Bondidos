package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UseCaseSaveUser @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {

    suspend fun execute(url: String): Event<Boolean> = withContext(Dispatchers.IO) {
        try {
            val user = utils.getUserFromUrl(url)
            Event(repository.saveUser(requireNotNull(user)))
        } catch (e: Exception) {
            Event(false)
        }
    }
}