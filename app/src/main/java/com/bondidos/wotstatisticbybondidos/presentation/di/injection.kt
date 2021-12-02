package com.bondidos.wotstatisticbybondidos.presentation.di

import android.content.Context
import androidx.room.Room
import coil.Coil
import coil.ImageLoader
import com.bondidos.wotstatisticbybondidos.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.BASE_URL
import com.bondidos.wotstatisticbybondidos.data.repository.RepositoryImpl
import com.bondidos.wotstatisticbybondidos.data.repository.api.WotApi
import com.bondidos.wotstatisticbybondidos.data.repository.room.AppDatabase
import com.bondidos.wotstatisticbybondidos.data.repository.room.RoomRepositoryDao
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.useCase.*
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideApi(): WotApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
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
    ): Repository = RepositoryImpl(wotApi,room)

}
/*
@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideUseCaseLogin(repository: Repository): UseCaseLogin = UseCaseLogin(repository)

    @ViewModelScoped
    @Provides
    fun provideUseCaseSearch(repository: Repository): UseCaseSearch = UseCaseSearch(repository)

    @ViewModelScoped
    @Provides
    fun provideUseCaseSaveUser(repository: Repository): UseCaseSaveUser = UseCaseSaveUser(repository)

    @ViewModelScoped
    @Provides
    fun provideCreateDataBaseUseCAse(context: Context, repository: Repository):CreateAchievesDBIfNotExist =
        CreateAchievesDBIfNotExist(context.applicationContext,repository)

    @ViewModelScoped
    @Provides
    fun provideUseCaseGetAchieves(repository: Repository): UseCaseGetAchieves =
        UseCaseGetAchieves(repository)

}
*/
@Module
@InstallIn(ActivityComponent::class)
object PresentationModule {

   /* @ActivityScoped
    @Provides
    fun provideLoginViewModel(
        login: UseCaseLogin,
        search: UseCaseSearch,
        createDB: CreateAchievesDBIfNo           tExist
    ): ViewModel = LoginViewModelFactory(login, search,createDB).create(LoginViewModel::class.java)
*/

    @ActivityScoped
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    /*@ActivityScoped
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ): RequestManager = Glide.with(context.applicationContext).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_baseline_leaderboard_24)
            .error(R.drawable.ic_baseline_leaderboard_24)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )*/

}

//TODO SET INIT BLOCK INTO THIS OBJECTS AND LOOK FOR RECREATING