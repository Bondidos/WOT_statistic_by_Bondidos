package com.bondidos.wotstatisticbybondidos.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.bondidos.wotstatisticbybondidos.data.constatnts.Constants.BASE_URL
import com.bondidos.wotstatisticbybondidos.data.repository.RepositoryImpl
import com.bondidos.wotstatisticbybondidos.data.repository.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.repository.room.AppDatabase
import com.bondidos.wotstatisticbybondidos.data.repository.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSaveUser
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSearch
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModelFactory
import dagger.hilt.android.qualifiers.ApplicationContext

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
        room: RoomRepositoryDao
    ): Repository = RepositoryImpl(wotApi,room)

}

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideUseCaseLogin(repository: Repository): UseCaseLogin = UseCaseLogin(repository)

    @Provides
    fun provideUseCaseSearch(repository: Repository): UseCaseSearch = UseCaseSearch(repository)

    @Provides
    fun provideUseCaseSaveUser(repository: Repository): UseCaseSaveUser = UseCaseSaveUser(repository)
}

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun provideLoginViewModel(
        login: UseCaseLogin,
        search: UseCaseSearch
    ): ViewModel = LoginViewModelFactory(login, search).create(LoginViewModel::class.java)

}