package com.bondidos.wotstatisticbybondidos.data.repository.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.wotstatisticbybondidos.domain.entityes.User

@Dao
interface RoomRepositoryDao {

    @Insert(onConflict = REPLACE)
    fun saveUser(user: User): Long

    @Query("select * from user where account_id = :id")
    fun getUserIfExist(id: Long): User?

    @Delete
    fun delete(user: User)
}