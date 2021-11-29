package com.bondidos.wotstatisticbybondidos.domain.other.extensions

import com.bondidos.wotstatisticbybondidos.data.entiyes.Data
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.google.gson.GsonBuilder
import org.json.JSONObject

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
    return this.map { User(it.nickname,it.accountId, null, null) }
}