package com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

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
    fun fromOption(list: List<Option>?): String? = list?.toString()

    @TypeConverter
    fun fromString(data: String?): List<Option>? {
        data?.let {
            val moshi = Moshi.Builder()
                .build()
            val arrayType = Types.newParameterizedType(List::class.java, Option::class.java)
            val adapter: JsonAdapter<List<Option>> = moshi.adapter(arrayType)
            return adapter.fromJson(data)
        }

        return null
    }
}