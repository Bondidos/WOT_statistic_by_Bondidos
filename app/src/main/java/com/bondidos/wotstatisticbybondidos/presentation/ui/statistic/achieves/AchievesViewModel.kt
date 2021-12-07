package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.achieves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseGetAchieves
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AchievesViewModel @Inject constructor(private val fetchAchieves: UseCaseGetAchieves) : ViewModel() {

    private val _listUserAchieves = MutableStateFlow<Resource<List<MultiViewModel>>>(Resource.initialized(null))
    val listUserAchieves: StateFlow<Resource<List<MultiViewModel>>> = _listUserAchieves.asStateFlow()

    init {

        viewModelScope.launch {
            _listUserAchieves.value = Resource.loading(null)
            _listUserAchieves.value = fetchAchieves.execute()
        }
    }
}