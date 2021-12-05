package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.content.Context
import android.util.Log
import com.bondidos.wotstatisticbybondidos.data.entityes.achieves.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject



class CreateAchievesDBIfNotExist @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(): Resource<String> {

        return if (repository.isAchievesDataBaseExist()) {
            return Resource.success("Database exist")
        }
        else try {
            repository.createAchievesDB()
            Resource.success("DataBase Created")
        } catch (e: Exception) {
            Resource.error(e.toString(), null)
        }
    }
}