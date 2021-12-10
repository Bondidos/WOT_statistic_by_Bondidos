package com.bondidos.wotstatisticbybondidos.domain.useCase

import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseGetData @Inject constructor(
    private val repository: Repository,
    private val utils: Utils
) {

    suspend fun execute(): Resource<List<MultiViewModel>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.success(getAndMapData())
            } catch (e: Exception) {
                Resource.error("Can't retrieve data. Check connections", null)
            }
        }
    }

    private suspend fun getAndMapData(): List<MultiViewModel> {
        val user = repository.getUser()
        var result: List<MultiViewModel>? = null
        user?.let { it ->
            if (utils.isUserValid(it)) {
                val apiData = repository.getApiDataResponse(it)
                val apiClan = repository.getApiClanResponse(
                    it,
                    apiData.data["${user.account_id}"]?.clanId ?: 0
                )
                val bestTanks = utils.getBestTanksId(apiData, user)
                val bestTanksImages = repository.getBestTanksImages(bestTanks)
                val images = utils.tankImageToImage(bestTanksImages, bestTanks)
                result = utils.createMultiViewModelList(apiData, apiClan, it, images)
            }
        }
        return result!!
    }
}
