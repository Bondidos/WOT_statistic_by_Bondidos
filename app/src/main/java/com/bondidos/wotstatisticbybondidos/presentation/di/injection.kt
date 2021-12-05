package com.bondidos.wotstatisticbybondidos.presentation.di

import android.content.Context
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
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.useCase.*
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideApi(): WotApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(WotApi::class.java)

    @Provides
    fun provideRoomDatabaseDao(@ApplicationContext context: Context): RoomRepositoryDao =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).build().userDataBase()

    @Provides
    fun provideRepository(
        wotApi: WotApi,
        room: RoomRepositoryDao
    ): Repository = RepositoryImpl(wotApi, room)

}

@Module
@InstallIn(ActivityComponent::class)
object PresentationModule {


    @ActivityScoped
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context

}
