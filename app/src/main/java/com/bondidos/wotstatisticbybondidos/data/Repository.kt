package com.bondidos.wotstatisticbybondidos.data

import android.util.Log
import com.bondidos.wotstatisticbybondidos.data.sources.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.sources.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.data.sources.sharedPrefs.PrefStoreImpl
import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.ACHIEVES_COUNT
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.APPLICATION_ID
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

    //todo delete dispatcher. room has yours IO functionality
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

    override suspend fun fetchData() {
        val user = withContext(Dispatchers.IO) { prefStore.getUser() }
        val apiData = withContext(Dispatchers.IO) {
            networkService.getUserData(
                APPLICATION_ID,
                user.account_id,
                user.access_token,
                "-statistics"
            )
        }
        Log.d("Repository", user.toString())
        Log.d("Repository", apiData.data.toString())

    }//nickname=LegitimateKiller,
// account_id=560508396,
// access_token=9172617952f3348333b2cef4641a923e6b74ab42,
// expires_at=1639935775


/*override suspend fun searchUser(search: String): List<User> {
        val apiData = networkService.searchUser()
        return if(apiData.status == "ok") apiData.data.mapToUserList() else emptyList()
}

override suspend fun saveUserToCash(user: User): Long = roomStorage.saveUserToCache(user)

override suspend fun getUserFromCache(): User = roomStorage.getUserFromCache()

override suspend fun deleteUserFromCache(user: User): Int = roomStorage.deleteUserFromCache(user)

override suspend fun isAchievesDataBaseExist(): Int = roomStorage.isAchievesDataBaseExist()

override suspend fun createAchieveDataBase(achieves: List<Achieve>): List<Long> =
    roomStorage.createAchieveDataBase(achieves)

override suspend fun getAchievesData(achievesList: List<String>): List<Achieve> =
    roomStorage.getAchievesData(achievesList)

override fun getAchieves(id: Int): Flow<AchievesResponse> {
    return flow{
        val i =networkService.getUserAchieves(APPLICATION_ID,id)
        emit(i)
    }.flowOn(Dispatchers.IO)
}*/


}


