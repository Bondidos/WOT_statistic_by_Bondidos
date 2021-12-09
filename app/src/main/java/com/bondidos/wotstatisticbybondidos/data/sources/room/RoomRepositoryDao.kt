package com.bondidos.wotstatisticbybondidos.data.sources.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB.AchievesDB
import com.bondidos.wotstatisticbybondidos.data.entityes.achievesDB.AchievesDBItem

@Dao
interface RoomRepositoryDao {

    //is AchievesDataBaseExist
    @Query("select count(id) from achievesdbitem")
    suspend fun isAchievesDBExist(): Int

    @Insert(onConflict = REPLACE)
    suspend fun createAchievesDB(list: List<AchievesDBItem>)

    @Query("select * from achievesdbitem where AchievesDBItem.name in (:list) order by section")
    suspend fun getAchieves(list: List<String>): List<AchievesDBItem>
/*
    @Query("Delete from achievesdbitem")
    suspend fun clearDatabase()*/
}