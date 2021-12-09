package com.bondidos.wotstatisticbybondidos.presentation.ui.login.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bondidos.wotstatisticbybondidos.TestCoroutineRule
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.CreateAchievesDBIfNotExist
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogout
import com.bondidos.wotstatisticbybondidos.presentation.ui.login.login_fragment.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    //  needed to test code with LiveData
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var login: UseCaseLogin
    private lateinit var createAchievesDBIfNotExist: CreateAchievesDBIfNotExist
    private lateinit var logout: UseCaseLogout
    private lateinit var user: User

    @Before
    fun setUp() {
        login = mockk()
        createAchievesDBIfNotExist = mockk()
        logout = mockk()
        user = mockk(relaxed = true)

    }

    @Test
    fun isDatabaseExist() {
        coEvery { createAchievesDBIfNotExist.execute() } returns Resource.success("created")
        coEvery { login.execute() } returns Resource.success(user)
        val viewModel = LoginViewModel(
            login,
            createAchievesDBIfNotExist,
            logout
        )
        testCoroutineRule.runBlockingTest {
            val job = launch {

                viewModel.isDatabaseCreated.collect {
                    assert(it.data == "created")
                }
            }
            job.cancel()
        }
    }

    @Test
    fun isExistSavedUser() {
        coEvery { createAchievesDBIfNotExist.execute() } returns Resource.success("created")
        coEvery { login.execute() } returns Resource.success(user)
        val viewModel = LoginViewModel(
            login,
            createAchievesDBIfNotExist,
            logout
        )
        testCoroutineRule.runBlockingTest {
            val job = launch {
                viewModel.isExistSavedUser.collect { resource ->
                    assert(resource.data == user)
                }
            }
            job.cancel()
        }
    }

    @Test
    fun isNavigationTriggers() {
        coEvery { createAchievesDBIfNotExist.execute() } returns Resource.success("created")
        coEvery { login.execute() } returns Resource.success(user)
        val viewModel = LoginViewModel(
            login,
            createAchievesDBIfNotExist,
            logout
        )
        viewModel.logInWithWgOpenId()
        testCoroutineRule.runBlockingTest {
            val job = launch {
                viewModel.navigation.collect { event ->
                    assert(event.peekContent() == Constants.NAVIGATE_TO_LOGIN)
                }
            }
            job.cancel()
        }
        viewModel.continueAsSavedUser()
        testCoroutineRule.runBlockingTest {
            val job = launch {
                viewModel.navigation.collect { event ->
                    assert(event.peekContent() == Constants.NAVIGATE_CONTINUE)
                }
            }
            job.cancel()
        }
    }

    @Test
    fun logOut(){
        coEvery { createAchievesDBIfNotExist.execute() } returns Resource.success("created")
        coEvery { logout.execute() } returns Resource.success(null)
        val viewModel = LoginViewModel(
            login,
            createAchievesDBIfNotExist,
            logout
        )
        viewModel.logout()
        testCoroutineRule.runBlockingTest {
            val job = launch {
                viewModel.isExistSavedUser.collect { resource ->
                    assert(resource == Resource.success(null))
                }
            }
            job.cancel()
        }
    }
}