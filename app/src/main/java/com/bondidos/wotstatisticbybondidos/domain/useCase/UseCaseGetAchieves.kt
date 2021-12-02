package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.util.Log
import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.AchievesResponse
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.extensions.asMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCaseGetAchieves @Inject constructor(private val repository: Repository) {


    suspend fun execute(): Flow<List<Achieve>> {
        return flow {
            // get user from cache
            val user: User = repository.getUserFromCache().first()!!//todo kljj

            // get user achieves from api
            val achievesResponse: AchievesResponse = getAchievesData(user.account_id)

            // convert api response to Map
            val listOfAchieveResponse: Map<String,Int> = achievesResponse.convertToMap()

            // create lists for making call to Achieves database to retrieve list of requested Achieves (description)
            val listAchieves: List<String> = createListForRequest(listOfAchieveResponse)
           //Log.d("achievementsObjAsMap", listAchieves.toString())

            // get achievesDescription
            val descriptionOfUserAchieves:  List<Achieve> = repository.getAchievesData(listAchieves)
            descriptionOfUserAchieves.map {
                it.received = listOfAchieveResponse[it.name] ?: 0
            }
            Log.d("achievementsObjAsMap", descriptionOfUserAchieves.toString())
            emit(descriptionOfUserAchieves)
        }
    }

    private fun createListForRequest( list : Map<String,Int>): List<String> {

        val result = mutableListOf<String>()

        list.forEach {
                result.add(it.key)
        }
        return result.toList()
    }

    private suspend fun getAchievesData(id: Int): AchievesResponse {
        var achieves: AchievesResponse? = null
        repository.getAchieves(id).collect{
            achieves = it
        }
        return achieves!!
    }
}

private fun AchievesResponse.convertToMap(): Map<String,Int>{

    val resultList = mutableListOf<Pair<String,Int>>()
    resultList.addAll(this.data.userAchievesData.achievements.asMap().toList())
    resultList.addAll(this.data.userAchievesData.frags.asMap().toList())
    resultList.addAll(this.data.userAchievesData.maxSeries.asMap().toList())
    return resultList.toMap()
}
