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
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkService: WotApi,
    private val roomStorage: RoomRepositoryDao,
    private val prefStore: PrefStoreImpl,
    private val utils: Utils
) : Repository {

    //todo delete dispatcher. room and retrofit has yours IO functionality
    override suspend fun createAchievesDB() {
        withContext(Dispatchers.IO) {
            val list = utils.jsonToAchievesList()
            roomStorage.createAchievesDB(list)
        }
    }

    override suspend fun isAchievesDataBaseExist(): Boolean =
        withContext(Dispatchers.IO) { roomStorage.isAchievesDBExist() == ACHIEVES_COUNT }

    override suspend fun saveUser(url: String): Boolean {

        val user = withContext(Dispatchers.IO) { utils.getUserFromUrl(url) }
        user?.let {
            return withContext(Dispatchers.IO) { prefStore.saveUser(user) }
        }
        return false
    }


    override suspend fun getUser(): User? {
        val user = withContext(Dispatchers.IO) { prefStore.getUser() }
        return if (utils.isUserValid(user)) user else null
    }

    override suspend fun fetchData(): List<MultiViewModel> {
        val user = withContext(Dispatchers.IO) { prefStore.getUser() }
        val apiData = withContext(Dispatchers.IO) {
            networkService.getUserData(
                APPLICATION_ID,
                user.account_id,
                user.access_token,
                FIELDS_DATA
            )
        }
        val apiClan = withContext(Dispatchers.IO){networkService.getUserClanImage(
            APPLICATION_ID,
            apiData.data["${user.account_id}"]?.clanId ?: 0
        )}

        /*Log.d("Repository", user.toString())
        Log.d("Repository", apiClan.toString())
        Log.d("Repository", apiData.data.toString())*/

        return withContext(Dispatchers.IO) {
            utils.createMultiViewModelList(
                apiData,
                apiClan,
                user
            )
        }
    }

    override suspend fun fetchAchieves(): List<MultiViewModel> {
        val user = withContext(Dispatchers.IO) { prefStore.getUser() }
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

        val result = utils.generateSortedMultiViewModelList(
            achievesByNameFromDB,
            apiAchievesResponse.data["${user.account_id}"]?.achievements ?: emptyMap()
        )

        Log.d("Repository",result.size.toString())
        Log.d("Repository",result.toString())
        //Log.d("Repository",achievesByNameFromDB.size.toString())
        //Log.d("Repository",achievesNamesList.toString())
        /*Log.d("Repository","achievesNamesList.size = ${achievesNamesList.size}," +
                "apiAchievesResponse.data.size= " +
                "${apiAchievesResponse.data["${user.account_id}"]?.achievements?.size}")*/
        //Log.d("Repository",apiAchievesResponse.toString())
        return emptyList()
    }
//nickname=LegitimateKiller,
// account_id=560508396,
// access_token=9172617952f3348333b2cef4641a923e6b74ab42,
// expires_at=1639935775
}


