package com.bondidos.wotstatisticbybondidos.domain

import com.bondidos.wotstatisticbybondidos.data.entityes.achievesApi.ApiAchievesResponse
import com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.data.entityes.tankImageApi.TankImage
import com.bondidos.wotstatisticbybondidos.data.entityes.userClanApi.ApiClanResponse
import com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi.ApiDataResponse
import com.bondidos.wotstatisticbybondidos.domain.entityes.User


interface Repository {

    suspend fun createAchievesDB(list: List<AchievesDBItem>)

    suspend fun isAchievesDataBaseExist(): Int

    suspend fun saveUser(user: User): Boolean

    suspend fun getUser(): User?

    suspend fun getApiDataResponse(user: User): ApiDataResponse

    suspend fun getApiClanResponse(user: User, clanId: Int): ApiClanResponse

    suspend fun getBestTanksImages(list:  List<Number?>): TankImage

    suspend fun getApiAchievesResponse(user: User): ApiAchievesResponse

    suspend fun getAchievesFromDatabase(achievesNamesList: List<String>): List<AchievesDBItem>

    suspend fun logout(user: User): Boolean
}