package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.personal_data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseGetData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDataViewModel @Inject constructor(
    private val fetchData: UseCaseGetData
) : ViewModel() {

    private val _listUserData = MutableStateFlow<Resource<List<MultiViewModel>>>(Resource.initialized(null))
    val listUserData: StateFlow<Resource<List<MultiViewModel>>> = _listUserData.asStateFlow()

    init {

        viewModelScope.launch {
            _listUserData.value = Resource.loading(null)
             _listUserData.value = fetchData.execute()
        }
    }

  /*  sealed class AchievesUiState {
        data class Success(val data: String?*//*List<Achieve>*//*) : AchievesUiState()
        data class Error(val message: String) : AchievesUiState()
        object Loading : AchievesUiState()
        object Empty : AchievesUiState()
    }*/
}
