package com.bondidos.wotstatisticbybondidos.presentation.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.bondidos.wotstatisticbybondidos.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.BASE_URL
import com.bondidos.wotstatisticbybondidos.data.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.room.AppDatabase
import com.bondidos.wotstatisticbybondidos.data.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.data.sharedPrefs.PrefStoreImpl
import com.bondidos.wotstatisticbybondidos.data.util.Utils
import com.bondidos.wotstatisticbybondidos.domain.Repository
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    fun provideUtilsClass(@ApplicationContext context: Context): Utils = Utils(context)

    @Provides
    fun providePrefStore(preferences: SharedPreferences): PrefStoreImpl =
        PrefStoreImpl(preferences)

    @Singleton
    @Provides
    fun provideApi(): WotApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(WotApi::class.java)

    @Singleton
    @Provides
    fun provideRoomDatabaseDao(@ApplicationContext context: Context): RoomRepositoryDao =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).build().userDataBase()

    @Singleton
    @Provides
    fun provideRepository(
        wotApi: WotApi,
        room: RoomRepositoryDao,
        prefStore: PrefStoreImpl,
        utils: Utils
    ): Repository = RepositoryImpl(wotApi, room,prefStore,utils)

}

@Module
@InstallIn(ActivityComponent::class)
object PresentationModule {


    @ActivityScoped
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context




}
