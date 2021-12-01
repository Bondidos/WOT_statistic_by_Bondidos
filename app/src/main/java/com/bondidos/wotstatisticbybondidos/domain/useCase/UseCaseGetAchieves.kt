package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.AchievesResponse
import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.UserAchievesData
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.extensions.asMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.stream.Stream
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class UseCaseGetAchieves @Inject constructor(private val repository: Repository) {


    suspend fun execute(): Flow<Map<Int,Achieve>?> {
        // get user from cache
        val user: User = repository.getUserFromCache().first()!!

        // get user achieves from api
        val achievesResponse: AchievesResponse = getAchievesData(user.account_id)

        // convert api response to Map
        val listOfAchieveResponse: List<Map<String,Int>> = achievesResponse.convertToMap()

        // create lists for making call to Achieves database to retrieve list of requested Achieves (description)
        val listAchieves: List<String> = createListForRequest(listOfAchieveResponse)
        Log.d("achievementsObjAsMap", listAchieves.toString())

        // get achievesDescription
        val descriptionOfUserAchieves = repository.getAchievesData(listAchieves)
        Log.d("achievementsObjAsMap", descriptionOfUserAchieves.toString())
        return flow {  }
    }

    private fun createListForRequest( list : List<Map<String,Int>>): List<String> {

        val result = mutableListOf<String>()

        list.forEach { map ->
            map.forEach { it ->
                result.add(it.key)
            }
        }
        return result
    }

    private suspend fun getAchievesData(id: Int): AchievesResponse {
        var achieves: AchievesResponse? = null
        repository.getAchieves(id).collect{
            achieves = it
        }
        return achieves!!
    }
}

private fun AchievesResponse.convertToMap(): List<Map<String,Int>>{

    return listOf(
        this.data.userAchievesData.achievements.asMap(),
        this.data.userAchievesData.frags.asMap(),
        this.data.userAchievesData.maxSeries.asMap())
}

