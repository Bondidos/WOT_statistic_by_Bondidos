package com.bondidos.wotstatisticbybondidos.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.wotstatisticbybondidos.data.entityes.achieves.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.domain.entityes.User

@Dao
interface RoomRepositoryDao {

   /* // User Table
    @Insert(onConflict = REPLACE)
    suspend fun saveUserToCache(user: User): Long

    @Query("select * from user where id = 1")
    suspend fun getUserFromCache(): User

    @Delete
    suspend fun deleteUserFromCache(user: User): Int


    // Achieves table
    @Query("select count(name) from achieves")
    suspend fun isAchievesDataBaseExist(): Int

    @Insert(onConflict = IGNORE)
    suspend fun createAchieveDataBase(achieves: List<Achieve>): List<Long> //todo migrate flow?

    @Query("select * from achieves where name in (:achievesList) order by name ASC")
    suspend fun getAchievesData(achievesList: List<String>): List<Achieve>*/

    //is AchievesDataBaseExist
    @Query("select count(id) from achievesdbitem")
    suspend fun isAchievesDBExist(): Int

    @Insert(onConflict = REPLACE)
    suspend fun createAchievesDB(list: List<AchievesDBItem>)

}