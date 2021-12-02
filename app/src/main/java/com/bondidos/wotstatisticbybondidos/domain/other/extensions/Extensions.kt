package com.bondidos.wotstatisticbybondidos.domain.other.extensions

import com.bondidos.wotstatisticbybondidos.data.response_entiyes.searchUser.Data
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.google.gson.GsonBuilder
import org.json.JSONObject
import kotlin.reflect.full.memberProperties

fun JSONObject.toAchievesList(): List<Achieve> {
    val list: MutableList<Achieve> = mutableListOf()
    val gson = GsonBuilder().create()

    this.keys().forEach { key ->
        when(val value =this[key]){
            is JSONObject -> list.add(
                gson.fromJson(value.toString(),Achieve::class.java)
            )
        }
    }
    return list
}

fun List<Data>.mapToUserList() : List<User>{
    return this.map { User(
        nickname = it.nickname,
        account_id = it.accountId,
        access_token = null,
        expires_at = null
        )
    }
}

inline fun <reified T : Any> T.asMap() : Map<String, Int> {
    val props = T::class.memberProperties.associateBy { it.name }
    return props.keys.associateWith { props[it]?.get(this) as Int}
}