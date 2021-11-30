package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.*
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.CreateAchievesDBIfNotExist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val login: UseCaseLogin,
    private val createAchievesDBIfNotExist: CreateAchievesDBIfNotExist
) : ViewModel() {

    private val _isDatabaseCreated = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val isDatabaseCreated: StateFlow<LoginUiState> = _isDatabaseCreated.asStateFlow()

    private val _isExistSavedUser = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val isExistSavedUser: StateFlow<LoginUiState> = _isExistSavedUser.asStateFlow()

    private val _navigation = MutableStateFlow<NavigateEvent>(NavigateEvent.Empty)
    val navigation: StateFlow<NavigateEvent> = _navigation.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isDatabaseCreated.value = LoginUiState.Loading
            createAchievesDBIfNotExist.execute().let { it ->
                when (it) {
                    true -> _isDatabaseCreated.value = LoginUiState.Success(null)
                    false -> _isDatabaseCreated.value = LoginUiState.Error(
                        "Error while Initializing Database. Please restart Application"
                    )
                }
            }

            _isExistSavedUser.value = LoginUiState.Loading
            login.execute().let { list ->

                when (list.isNotEmpty()) {
                    false -> _isExistSavedUser.value =
                        LoginUiState.Error("Can't retrieve data about user")
                    true -> if (list.isNotEmpty()) {
                        _isExistSavedUser.value = LoginUiState.Success(list.first())
                    }
                }

            }
        }
    }

    fun logInWithWgOpenId() {
        _navigation.value = NavigateEvent.ToWebView
    }

    fun continueAsSavedUser() {
        _navigation.value = NavigateEvent.ToUserAchieves
    }

    sealed class LoginUiState {
        data class Success(val data: User?) : LoginUiState()
        data class Error(val message: String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }

    sealed class NavigateEvent {
        object Empty : NavigateEvent()
        object ToUserAchieves : NavigateEvent()
        object ToWebView : NavigateEvent()
    }
}
