package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.wotstatisticbybondidos.domain.entityes.Achieve
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseGetAchieves
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AchievesViewModel @Inject constructor(
    private val getAchieves: UseCaseGetAchieves
) : ViewModel() {

    private val _listOfAchieves = MutableStateFlow<AchievesUiState>(AchievesUiState.Empty)
    val listOfAchieves: StateFlow<AchievesUiState> = _listOfAchieves.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO) {

            _listOfAchieves.value = AchievesUiState.Loading
            getAchieves.execute().let { flow ->
                flow.collect { list ->
                    when(list != null){
                        /*true -> _listOfAchieves.value = AchievesUiState.Success(list)
                        false -> _listOfAchieves.value = AchievesUiState.Error("Can't retrieve list")*/
                    }
                }
            }
        }
    }


    sealed class AchievesUiState {
        data class Success(val data: List<Achieve>) : AchievesUiState()
        data class Error(val message: String) : AchievesUiState()
        object Loading : AchievesUiState()
        object Empty : AchievesUiState()
    }
}