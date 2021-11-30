package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.AchievesResponse
import com.bondidos.wotstatisticbybondidos.data.response_entiyes.achievments.UserAchievesData
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class UseCaseGetAchieves @Inject constructor(private val repository: Repository) {


    suspend fun execute(): Flow<Map<Int,Achieve>?> {
        val user: User = repository.getUserFromCache().first()!!
        val achieves: AchievesResponse = getAchievesData(user.account_id)
        val listOfAchieves: List<String> = getListOfAchieves(achieves.data.userAchievesData)
//todo stopped here-----------------------
       // repository.getAchievesData(listOfAchieves)
        return flow {  }
    }

    private suspend fun getAchievesData(id: Int): AchievesResponse {
        var achieves: AchievesResponse? = null
        repository.getAchieves(id).collect{
            achieves = it
        }
        return achieves!!
    }
}

fun getListOfAchieves(achieves: UserAchievesData): List<String>{
    return emptyList<String>()
}