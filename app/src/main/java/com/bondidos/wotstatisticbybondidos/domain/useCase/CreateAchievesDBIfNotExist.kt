package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CreateAchievesDBIfNotExist @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {

    suspend fun execute(): Resource<String> {

        return withContext(Dispatchers.IO) {
            val list = utils.jsonToAchievesList()
            if (repository.isAchievesDataBaseExist() == Constants.ACHIEVES_COUNT) {
                Resource.success("Database exist")
            } else try {
                repository.createAchievesDB(list)
                Resource.success("DataBase Created")
            } catch (e: Exception) {
                Resource.error(e.toString(), null)
            }
        }
    }
}
