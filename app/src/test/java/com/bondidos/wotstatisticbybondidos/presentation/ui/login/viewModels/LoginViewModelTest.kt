package com.bondidos.wotstatisticbybondidos.presentation.ui.login.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bondidos.wotstatisticbybondidos.domain.useCase.CreateAchievesDBIfNotExist
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogout
import com.bondidos.wotstatisticbybondidos.presentation.ui.login.login_fragment.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testCoroutineScope = TestCoroutineScope(coroutineDispatcher)

    @Inject
    lateinit var viewModel: LoginViewModel



    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {

        Dispatchers.setMain(coroutineDispatcher)

    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun databaseCreated() {
        testCoroutineScope.launch {
            viewModel.isDatabaseCreated
        }

    }

    @Test
    fun isExistSavedUser() {
    }

    @Test
    fun getNavigation() {
    }

    @Test
    fun logInWithWgOpenId() {
    }

    @Test
    fun continueAsSavedUser() {
    }
}