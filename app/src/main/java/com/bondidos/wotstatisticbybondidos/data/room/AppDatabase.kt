package com.bondidos.wotstatisticbybondidos.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bondidos.wotstatisticbybondidos.data.entityes.achieves.AchievesDBItem
import com.bondidos.wotstatisticbybondidos.domain.entityes.User


@Database(entities = [AchievesDBItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDataBase() : RoomRepositoryDao
}
