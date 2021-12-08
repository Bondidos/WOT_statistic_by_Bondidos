package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class CreateAchievesDBIfNotExist @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(): Resource<String> {

        return withContext(Dispatchers.IO) {
            if (repository.isAchievesDataBaseExist()) {
                Resource.success("Database exist")
            } else try {
                repository.createAchievesDB()
                Resource.success("DataBase Created")
            } catch (e: Exception) {
                Resource.error(e.toString(), null)
            }
        }
    }
}
