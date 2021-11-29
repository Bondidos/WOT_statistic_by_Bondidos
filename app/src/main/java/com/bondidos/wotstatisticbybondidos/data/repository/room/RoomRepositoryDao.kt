package com.bondidos.wotstatisticbybondidos.data.repository.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomRepositoryDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveUserToCache(user: User): Long

    @Query("select * from user where account_id = :id")
    fun getUserFromCache(id: Long): Flow<User>

    @Delete
    suspend fun deleteUserFromCache(user: User): Int



    @Query("select count(name) from achieves")
    suspend fun isAchievesDataBaseExist(): Int

    @Insert(onConflict = IGNORE)
    suspend fun createAchieveDataBase(achieves: List<Achieve>): List<Long>

    @Query("select * from achieves where name in (:achievesList) order by name ASC")
    fun getAchievesData(achievesList: List<String>): Flow<List<Achieve>>
}