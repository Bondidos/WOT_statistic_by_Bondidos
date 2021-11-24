package com.bondidos.wotstatisticbybondidos.data.room_repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bondidos.wotstatisticbybondidos.domain.entityes.User

@Dao
interface RoomRepositoryDao {

    @Insert(onConflict = REPLACE)
    fun saveUser(user: User)

    @Query("select * from user where id = :id")
    fun getUserIfExist(id: Long): User?

    @Delete
    fun delete(user: User)
}