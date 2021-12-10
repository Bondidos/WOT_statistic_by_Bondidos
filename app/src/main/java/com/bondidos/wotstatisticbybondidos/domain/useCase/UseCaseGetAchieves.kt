package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UseCaseGetAchieves @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {

    suspend fun execute(): Resource<List<MultiViewModel>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.success(getAndMapData())
            } catch (e: Exception) {
                Resource.error("Can't fetch achieves. Check connections", null)
            }
        }
    }

    suspend fun getAndMapData(): List<MultiViewModel> {
        val user = repository.getUser()
        var result: List<MultiViewModel>? = null
        user?.let { it ->
            if (utils.isUserValid(it)) {
                val apiAchievesResponse = repository.getApiAchievesResponse(it)
                val achievesNamesList = utils.getAchievesNamesFromResponse(
                    apiAchievesResponse.data["${user.account_id}"]?.achievements ?: emptyMap()
                )
                val achievesByNameFromDB = repository.getAchievesFromDatabase(achievesNamesList)
                result = utils.generateSortedMultiViewModelList(
                    achievesByNameFromDB,
                    apiAchievesResponse.data["${user.account_id}"]?.achievements ?: emptyMap()
                )
            }
        }
        return result!!
    }
}
