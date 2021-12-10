package com.bondidos.wotstatisticbybondidos.data

import com.bondidos.wotstatisticbybondidos.data.entityes.achievesApi.ApiAchievesResponse
import com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.data.entityes.tankImageApi.TankImage
import com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi.ApiDataResponse
import com.bondidos.wotstatisticbybondidos.data.sources.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.sources.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.data.sources.sharedPrefs.PrefStoreImpl
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.APPLICATION_ID
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.FIELDS_ACHIEVES
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.FIELDS_DATA
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.FIELDS_TANKS
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkService: WotApi,
    private val roomStorage: RoomRepositoryDao,
    private val prefStore: PrefStoreImpl
) : Repository {

    override suspend fun createAchievesDB(list: List<AchievesDBItem>) = roomStorage.createAchievesDB(list)

    override suspend fun isAchievesDataBaseExist() = roomStorage.isAchievesDBExist()

    override suspend fun saveUser(user: User): Boolean = prefStore.saveUser(user)

    override suspend fun getUser(): User = prefStore.getUser()

    override suspend fun getApiDataResponse(user: User): ApiDataResponse {
        return networkService.getUserData(
            APPLICATION_ID,
            user.account_id,
            user.access_token,
            FIELDS_DATA
        )
    }

    override suspend fun getApiClanResponse(user: User, clanId: Int) =
        networkService.getUserClanImage(
            APPLICATION_ID,
            clanId
        )

    override suspend fun getBestTanksImages(list:  List<Number?>): TankImage {
        return networkService.getBestTanksImage(
            APPLICATION_ID,
            "${list[0]},${list[1]}",
            FIELDS_TANKS
        )
    }

    override suspend fun getApiAchievesResponse(user: User): ApiAchievesResponse {
        return networkService.getUserAchieves(
            APPLICATION_ID,
            user.account_id,
            FIELDS_ACHIEVES
        )
    }

    override suspend fun getAchievesFromDatabase(achievesNamesList: List<String>): List<AchievesDBItem> {
        return roomStorage.getAchieves(achievesNamesList)
    }

    override suspend fun logout(user: User): Boolean {
        networkService.logout(APPLICATION_ID, user.access_token)
        prefStore.logout()
        return true
    }
}


