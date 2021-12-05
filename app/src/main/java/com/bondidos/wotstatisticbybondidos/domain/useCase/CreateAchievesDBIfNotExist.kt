package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.content.Context
import android.util.Log
import com.bondidos.wotstatisticbybondidos.domain.Repository
import org.json.JSONObject
import javax.inject.Inject

const val ACHIEVES_COUNT = 365

class CreateAchievesDBIfNotExist @Inject constructor(
    private val context: Context,
    private val repository: Repository
    ) {

   /* suspend fun execute(): Boolean {
        if (repository.isAchievesDataBaseExist() != ACHIEVES_COUNT)
            return createAchievesDataBase()
        return true
    }

    private suspend fun createAchievesDataBase(): Boolean {
        val list = createAchievesFromJson()
        return list.size == repository.createAchieveDataBase(list).size
       //Log.d("Recorded",recorded.size.toString())
    }


    private fun createAchievesFromJson(): List<Achieve> {

        val file = "achieves.json"
        val myJson = context.assets.open(file).bufferedReader().use { it.readText() }

        val jsonObj = JSONObject(myJson)
        return jsonObj.toAchievesList()
    }*/
}