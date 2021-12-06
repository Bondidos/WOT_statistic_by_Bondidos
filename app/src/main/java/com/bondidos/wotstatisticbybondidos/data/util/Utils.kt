package com.bondidos.wotstatisticbybondidos.data.util

import android.content.Context
import android.net.UrlQuerySanitizer
import com.bondidos.wotstatisticbybondidos.data.entityes.achieves.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class Utils @Inject constructor(private val context: Context) {

    fun jsonToAchievesList():  List<AchievesDBItem>{
        val moshi = Moshi.Builder()
            .build()
        val arrayType = Types.newParameterizedType(List::class.java, AchievesDBItem::class.java)
        val adapter: JsonAdapter<List<AchievesDBItem>> = moshi.adapter(arrayType)

        val file = "achieves.json"
        val myJson = context.assets.open(file).bufferedReader().use { it.readText() }

        return adapter.fromJson(myJson) ?: emptyList()
    }
    //todo remove magic numbers
    fun isUserValid(user:User): Boolean{
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
        return if(sanitizer.getValue("status") == "ok")
            User(
                nickname = sanitizer.getValue("nickname"),
                account_id = sanitizer.getValue("account_id").toInt(),
                access_token = sanitizer.getValue("access_token"),
                expires_at = sanitizer.getValue("expires_at").toLong()
            )
        else null
    }
    
    fun convertToMultiViewModelList(): List<MultiViewModel>{
        return emptyList()
    }

}