package com.bondidos.wotstatisticbybondidos.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import com.bondidos.wotstatisticbybondidos.data.constatnts.Constants.BASE_URL
import com.bondidos.wotstatisticbybondidos.data.repository.RepositoryImpl
import com.bondidos.wotstatisticbybondidos.data.repository.WotApi
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSearch
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModelFactory
import java.lang.IllegalArgumentException

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): WotApi = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WotApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(wotApi: WotApi) : Repository = RepositoryImpl(wotApi)

    @Provides
    fun provideUseCaseLogin(repository: Repository) : UseCaseLogin = UseCaseLogin(repository)

    @Provides
    fun provideUseCaseSearch(repository: Repository) : UseCaseSearch = UseCaseSearch(repository)

    @Singleton
    @Provides
    fun provideLoginViewModel(
        login: UseCaseLogin,
        search: UseCaseSearch
    ): ViewModel = LoginViewModelFactory(login,search).create(LoginViewModel::class.java)
}