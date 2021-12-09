package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.achieves

import com.bondidos.wotstatisticbybondidos.TestCoroutineRule
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseGetAchieves
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AchievesViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var fetchAchieves: UseCaseGetAchieves
    lateinit var achieves: List<MultiViewModel>

    @Before
    fun setUp() {
        fetchAchieves = mockk()
        achieves = mockk()
    }

    @Test
    fun fetchUserAchieves(){
        coEvery { fetchAchieves.execute() } returns Resource.success(achieves)
        val viewModel = AchievesViewModel(fetchAchieves)

        testCoroutineRule.runBlockingTest {
            val job = launch {

                viewModel.listUserAchieves.collect {
                    assert(it.data == achieves)
                }
            }
            job.cancel()
        }
    }
}