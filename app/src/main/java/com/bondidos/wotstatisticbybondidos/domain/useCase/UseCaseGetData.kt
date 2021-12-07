package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.util.Log
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import javax.inject.Inject

class UseCaseGetData @Inject constructor(private val repository: Repository) {


    suspend fun execute():Resource<List<MultiViewModel>>{
        return try {
            Resource.success(repository.fetchData())
        } catch (e: Exception){
            Resource.error("Can't retrieve data. Check connections",null)
        }
    }

    /*suspend fun execute(): Flow<List<Achieve>> {
        return flow {
            // get user from cache
            val user: User = repository.getUserFromCache()

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
    }*/

    private fun createListForRequest( list : Map<String,Int>): List<String> {

        val result = mutableListOf<String>()

        list.forEach {
                result.add(it.key)
        }
        return result.toList()
    }

    /*private suspend fun getAchievesData(id: Int): AchievesResponse {
        var achieves: AchievesResponse? = null
        repository.getAchieves(id).collect{
            achieves = it
        }
        return achieves!!
    }*/
}

/*private fun AchievesResponse.convertToMap(): Map<String,Int>{

    val resultList = mutableListOf<Pair<String,Int>>()
    resultList.addAll(this.data.userAchievesData.achievements.asMap().toList())
    resultList.addAll(this.data.userAchievesData.frags.asMap().toList())
    resultList.addAll(this.data.userAchievesData.maxSeries.asMap().toList())
    return resultList.toMap()
}*/
