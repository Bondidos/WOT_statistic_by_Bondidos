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

const val ACHIEVES_COUNT = 365

class CreateAchievesDBIfNotExist @Inject constructor(
    private val context: Context,
    private val repository: Repository
) {

    suspend fun execute(): Resource<String> {
        if (repository.isAchievesDataBaseExist() != ACHIEVES_COUNT) {
            return try {
                withContext(Dispatchers.IO) { createAchievesDataBase() }
                Resource.success("DataBase Created")
            } catch (e: Exception) {
                Resource.error(e.toString(), null)
            }
        }
        return Resource.success("Database exist")
    }

    private suspend fun createAchievesDataBase() {
        val list = createAchievesFromJson()
        repository.createAchievesDB(list)
        Log.d("Recorded", list.size.toString())
    }


    private fun createAchievesFromJson(): List<AchievesDBItem> {

        val moshi = Moshi.Builder()
            .build()
        val arrayType = Types.newParameterizedType(List::class.java, AchievesDBItem::class.java)
        val adapter: JsonAdapter<List<AchievesDBItem>> = moshi.adapter(arrayType)

        val file = "achieves.json"
        val myJson = context.assets.open(file).bufferedReader().use { it.readText() }

        return adapter.fromJson(myJson) ?: emptyList()
    }
}