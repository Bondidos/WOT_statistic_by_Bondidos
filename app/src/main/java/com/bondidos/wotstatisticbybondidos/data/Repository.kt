package com.bondidos.wotstatisticbybondidos.data

import android.util.Log
import com.bondidos.wotstatisticbybondidos.data.sources.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.sources.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.data.sources.sharedPrefs.PrefStoreImpl
import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.ACHIEVES_COUNT
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.APPLICATION_ID
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.FIELDS_ACHIEVES
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.FIELDS_DATA
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.FIELDS_TANKS
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkService: WotApi,
    private val roomStorage: RoomRepositoryDao,
    private val prefStore: PrefStoreImpl,
    private val utils: Utils
) : Repository {

    override suspend fun createAchievesDB() {
        val list = utils.jsonToAchievesList()
        roomStorage.createAchievesDB(list)
    }

    override suspend fun isAchievesDataBaseExist(): Boolean {
        return roomStorage.isAchievesDBExist() == ACHIEVES_COUNT
    }

    override suspend fun saveUser(url: String): Boolean {

        val user = utils.getUserFromUrl(url)
        user?.let {
            return prefStore.saveUser(user)
        }
        return false
    }

    override suspend fun getUser(): User? {
        val user = prefStore.getUser()
        return if (utils.isUserValid(user)) user else null
    }

    override suspend fun fetchData(): List<MultiViewModel> {
        val user = prefStore.getUser()
        val apiData = networkService.getUserData(
            APPLICATION_ID,
            user.account_id,
            user.access_token,
            FIELDS_DATA
        )
        // make shorter this method
        val bestTanks = listOf(
            apiData.data["${user.account_id}"]?.statistics?.all?.get("max_damage_tank_id"),
            apiData.data["${user.account_id}"]?.statistics?.all?.get("max_frags_tank_id")
        )
        val bestTanksImages = networkService.getBestTanksImage(
            APPLICATION_ID,
            "${bestTanks[0]},${bestTanks[1]}",
            FIELDS_TANKS
        )
        Log.d("Repository",bestTanksImages.toString())
        val apiClan = networkService.getUserClanImage(
            APPLICATION_ID,
            apiData.data["${user.account_id}"]?.clanId ?: 0
        )

        return utils.createMultiViewModelList(
            apiData,
            apiClan,
            user,
            bestTanksImages
        )
    }

    override suspend fun fetchAchieves(): List<MultiViewModel> {
        val user = prefStore.getUser()
        val apiAchievesResponse = networkService.getUserAchieves(
            APPLICATION_ID,
            user.account_id,
            FIELDS_ACHIEVES
        )
        val achievesNamesList =
            utils.getAchievesNamesFromResponse(
                apiAchievesResponse.data["${user.account_id}"]?.achievements ?: emptyMap()
            )

        val achievesByNameFromDB = roomStorage.getAchieves(achievesNamesList)

        return utils.generateSortedMultiViewModelList(
            achievesByNameFromDB,
            apiAchievesResponse.data["${user.account_id}"]?.achievements ?: emptyMap()
        )
    }

    override suspend fun logout(): Boolean {
        val user = prefStore.getUser()
        networkService.logout(APPLICATION_ID, user.access_token)
        prefStore.logout()
        return true
    }
}


