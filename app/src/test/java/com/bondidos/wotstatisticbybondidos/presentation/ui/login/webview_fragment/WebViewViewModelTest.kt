package com.bondidos.wotstatisticbybondidos.presentation.ui.login.webview_fragment

import com.bondidos.wotstatisticbybondidos.TestCoroutineRule
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSaveUser
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WebViewViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var saveUser: UseCaseSaveUser
    private lateinit var user: User

    @Before
    fun setUp() {
        saveUser= mockk()
        user = mockk()
    }

    @Test
    fun saveUser(){
        coEvery { saveUser.execute(any())} returns Event(true)
        val viewModel = WebViewViewModel(saveUser)
        viewModel.saveUser("save")
        testCoroutineRule.runBlockingTest {
            val job = launch {
                viewModel.isSaved.collect { it ->
                    it.getContentIfNotHandled()?.let {
                        assert(it)
                    }
                }
            }
            job.cancel()
        }
    }
}