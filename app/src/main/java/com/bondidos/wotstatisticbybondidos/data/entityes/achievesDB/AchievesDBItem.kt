package com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type


@Entity
@TypeConverters(OptionsConverter::class)
data class AchievesDBItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @Json(name = "condition")
    val condition: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "hero_info")
    val heroInfo: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "image_big")
    val imageBig: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "name_i18n")
    val nameI18n: String?,
    @Json(name = "options")
    val options: List<Option>?,
    @Json(name = "order")
    val order: Int,
    @Json(name = "outdated")
    val outdated: Boolean,
    @Json(name = "section")
    val section: String,
    @Json(name = "section_order")
    val sectionOrder: Int,
    @Json(name = "type")
    val type: String
)

class OptionsConverter{

    @TypeConverter
    fun fromCountryLangList(optionString: List<Option?>?): String? {
        if (optionString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Option?>?>() {}.type
        return gson.toJson(optionString, type)
    }

    @TypeConverter
    fun toCountryLangList(optionList: String?): List<Option>? {
        if (optionList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Option?>?>() {}.type
        return gson.fromJson<List<Option>>(optionList, type)
    }
}