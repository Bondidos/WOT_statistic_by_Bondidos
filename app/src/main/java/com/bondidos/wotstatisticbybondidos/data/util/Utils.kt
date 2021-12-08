package com.bondidos.wotstatisticbybondidos.data.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.UrlQuerySanitizer
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.data.entityes.userClanApi.ApiClanResponse
import com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi.ApiDataResponse
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.sql.Timestamp
import java.text.SimpleDateFormat
import javax.inject.Inject

class Utils @Inject constructor(private val context: Context) {

    fun jsonToAchievesList(): List<AchievesDBItem> {
        val moshi = Moshi.Builder()
            .build()
        val arrayType = Types.newParameterizedType(List::class.java, AchievesDBItem::class.java)
        val adapter: JsonAdapter<List<AchievesDBItem>> = moshi.adapter(arrayType)

        val file = "achieves.json"
        val myJson = context.assets.open(file).bufferedReader().use { it.readText() }

        return adapter.fromJson(myJson) ?: emptyList()
    }

    //todo remove magic numbers
    fun isUserValid(user: User): Boolean {
        return user.account_id != -1 &&
                user.nickname != "" &&
                user.access_token != "" &&
                isExpired(user.expires_at)
    }

    private fun isExpired(expiresAt: Long): Boolean =
        System.currentTimeMillis() <= (expiresAt + System.currentTimeMillis())

    fun getUserFromUrl(url: String): User? {
        val sanitizer = UrlQuerySanitizer().apply {
            allowUnregisteredParamaters = true
            parseUrl(url)
        }
        return if (sanitizer.getValue("status") == "ok")
            User(
                nickname = sanitizer.getValue("nickname"),
                account_id = sanitizer.getValue("account_id").toInt(),
                access_token = sanitizer.getValue("access_token"),
                expires_at = sanitizer.getValue("expires_at").toLong()
            )
        else null
    }

    fun createMultiViewModelList(
        apiData: ApiDataResponse,
        apiClan: ApiClanResponse,
        user: User
    ): List<MultiViewModel> {

        val result = mutableListOf<MultiViewModel>()
        val privateData = apiData.data["${user.account_id}"]?.private
        val commonData = apiData.data["${user.account_id}"]
        val clanId = apiData.data["${user.account_id}"]?.clanId.toString()

        result.add(
            MultiViewModel.Banner(
                commonData?.nickname ?: "",
                apiClan.data[clanId]?.emblems?.x256?.wowp ?: ""
            )
        )

        result.add(
            MultiViewModel.CardWithText(
                "Created at:",
                commonData?.createdAt?.toLong()?.toDataFormat() ?: ""

            )
        )

        result.add(
            MultiViewModel.CardWithText(
                "Last Battle:",
                commonData?.lastBattleTime?.toLong()?.toDataFormat() ?: ""
            )
        )

        result.add(
            MultiViewModel.Banner(
                "Global Rating /n${commonData?.globalRating}",
                R.drawable.wot_logo
            )
        )

        result.add(
            MultiViewModel.Banner(
                "Wealth",
                R.drawable.wealth
            )
        )
        result.add(
            MultiViewModel.Banner(
                "Expire at: ${
                    (privateData?.get("premium_expires_at") as Double).toLong().toDataFormat()
                }",
                R.drawable.premium
            )
        )
        result.add(
            MultiViewModel.CardWithImage(
                "Gold: ${(privateData["gold"] as Double).toLong()}",
                R.drawable.gold
            )
        )
        result.add(
            MultiViewModel.CardWithImage(
                "Credits: ${(privateData["credits"] as Double).toInt()}",
                R.drawable.credits
            )
        )
        result.add(
            MultiViewModel.CardWithImage(
                "Free exp.: ${(privateData["free_xp"] as Double).toInt()}",
                R.drawable.free_exp
            )
        )
        result.add(
            MultiViewModel.CardWithImage(
                "Bonds: ${(privateData["bonds"] as Double).toInt()}",
                R.drawable.bonds
            )
        )

        return result.toList()
    }

    @SuppressLint("SimpleDateFormat")
    private fun Long.toDataFormat(): String {
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(Timestamp(this * 1000)) // need ms, but have sec
    }

    fun getAchievesNamesFromResponse(achievesMap: Map<String, Int>): List<String> {
        val result = mutableListOf<String>()
        achievesMap.forEach {
            result.add(it.key)
        }
        return result
    }

    fun generateSortedMultiViewModelList(
        db: List<AchievesDBItem>,
        api: Map<String, Int>
    ): List<MultiViewModel> {

        val resultSet = mutableListOf<MultiViewModel>()

        val listOfSortedAchievesDB = listOf(
            db.filter { it.section == "epic" },
            db.filter { it.section == "action" },
            db.filter { it.section == "special" },
            db.filter { it.section == "memorial" },
            db.filter { it.section == "group" },
            db.filter { it.section == "class" }
        )

        listOfSortedAchievesDB.forEach { list ->
            when (listOfSortedAchievesDB.indexOf(list)) {
                0 -> resultSet.add(MultiViewModel.BannerWithoutImage("Epic"))
                1 -> resultSet.add(MultiViewModel.BannerWithoutImage("Action"))
                2 -> resultSet.add(MultiViewModel.BannerWithoutImage("Special"))
                3 -> resultSet.add(MultiViewModel.BannerWithoutImage("Memorial"))
                4 -> resultSet.add(MultiViewModel.BannerWithoutImage("Group"))
                5 -> resultSet.add(MultiViewModel.BannerWithoutImage("Class"))

            }
            list.forEach {
                resultSet.add(
                    MultiViewModel.AchieveCard(
                        scored = api[it.name].toString(),
                        image = it.imageBig?.replace("http","https") ?: // http do not work
                        it.image?.replace("http","https")  ?: ""        // replaced with https
                    )
                )
            }
        }
        return resultSet.toList()
    }
}