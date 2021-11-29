package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.content.Context
import android.util.Log
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.other.extensions.toAchievesList
import com.google.gson.Gson
import org.json.JSONObject
import javax.inject.Inject

const val ACHIEVES_COUNT = 365

class CreateAchievesDBIfNotExist @Inject constructor(
    private val context: Context,
    private val repository: Repository
    ) {

    suspend fun execute() {
        if (repository.isAchievesDataBaseExist() != ACHIEVES_COUNT)
            createAchievesDataBase()
    }

    private suspend fun createAchievesDataBase() {
        val list = createAchievesFromJson()
        val recorded = repository.createAchieveDataBase(list)
       Log.d("Recorded",recorded.size.toString())
    }


    private fun createAchievesFromJson(): List<Achieve> {

        val file = "achieves.json"
        val myJson = context.assets.open(file).bufferedReader().use { it.readText() }

        val jsonObj = JSONObject(myJson)
        return jsonObj.toAchievesList()
    }
}