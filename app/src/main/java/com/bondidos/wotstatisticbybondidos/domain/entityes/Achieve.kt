package com.bondidos.wotstatisticbybondidos.domain.entityes


import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "Achieves")
@TypeConverters(OptionConverter::class)
data class Achieve(

    @SerializedName("condition")
    val condition: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("hero_info")
    val heroInfo: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("image_big")
    val imageBig: String?,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("name")
    val name: String,

    @SerializedName("name_i18n")
    val nameI18n: String?,

    @SerializedName("options")
    val options: List<Option>?,

    @SerializedName("order")
    val order: Int?,

    @SerializedName("outdated")
    val outdated: Boolean?,

    @SerializedName("section")
    val section: String?,

    @SerializedName("section_order")
    val sectionOrder: Int?,

    @SerializedName("type")
    val type: String?
)
data class Option(
    //@SerializedName("description")
    val description: String?,
    //@SerializedName("image")
    val image: String?,
   // @SerializedName("image_big")
    val imageBig: String?,
   // @SerializedName("name_i18n")
    val nameI18n: String?,
   // @SerializedName("nation_images")
    val nationImages: String?
)

class OptionConverter{

   /* @TypeConverter
    fun toList(value: String): List<Option>{
        val listType = object: TypeToken<List<Option>>() {}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun toString(list: List<Option>): String{
        val gson = Gson()
        return gson.toJson(list)
    }*/

    @TypeConverter
    fun toList(value: String?): List<Option>?{
        val listType = object: TypeToken<List<Option>>() {}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun toString(list: List<Option>?): String?{
        val gson = Gson()
        return gson.toJson(list)
    }

}