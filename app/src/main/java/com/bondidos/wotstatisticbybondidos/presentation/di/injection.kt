package com.bondidos.wotstatisticbybondidos.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.bondidos.wotstatisticbybondidos.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
import com.bondidos.wotstatisticbybondidos.presentation.MainActivity
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModelFactory
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
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
}

/*@Module
@InstallIn(ActivityComponent::class)
object PresentationModule {

    @ActivityScoped
    @Provides
    fun provideLoginViewModel(
        login: UseCaseLogin,
        search: UseCaseSearch
    ): ViewModel = LoginViewModelFactory(login, search).create(LoginViewModel::class.java)
}*/

//TODO SET INIT BLOCK INTO THIS OBJECTS AND LOOK FOR RECREATING