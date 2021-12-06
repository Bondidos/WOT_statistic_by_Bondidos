package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.personal_data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseGetData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDataViewModel @Inject constructor(
    private val fetchData: UseCaseGetData
) : ViewModel() {

    private val _listOfAchieves = MutableStateFlow<AchievesUiState>(AchievesUiState.Empty)
    val listOfAchieves: StateFlow<AchievesUiState> = _listOfAchieves.asStateFlow()

    init {

        viewModelScope.launch {
             fetchData.execute()
            /*_listOfAchieves.value = AchievesUiState.Loading
            try {
                getAchieves.execute().collect {
                    _listOfAchieves.value = AchievesUiState.Success(it)
                }
            } catch (e: Exception) {
                _listOfAchieves.value = AchievesUiState.Error("Can't retrieve list")
            }*/
        }
    }

    sealed class AchievesUiState {
        data class Success(val data: String?/*List<Achieve>*/) : AchievesUiState()
        data class Error(val message: String) : AchievesUiState()
        object Loading : AchievesUiState()
        object Empty : AchievesUiState()
    }
}
