package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.personal_data

import com.bondidos.wotstatisticbybondidos.TestCoroutineRule
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseGetData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDataViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var fetchData: UseCaseGetData
    private lateinit var userData: List<MultiViewModel>

    @Before
    fun setUp() {
        fetchData = mockk()
        userData = mockk()
    }

    @Test
    fun getListUserData() {
        coEvery { fetchData.execute() } returns Resource.success(userData)
        val viewModel = UserDataViewModel(fetchData)

        testCoroutineRule.runBlockingTest {
            val job = launch {

                viewModel.listUserData.collect {
                    assert(it.data == userData)
                }
            }
            job.cancel()
        }
    }
}