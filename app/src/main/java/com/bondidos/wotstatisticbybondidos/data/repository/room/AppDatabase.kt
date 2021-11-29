package com.bondidos.wotstatisticbybondidos.data.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.entityes.User


@Database(entities = [User::class, Achieve::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDataBase() : RoomRepositoryDao
}
