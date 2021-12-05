package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.NAVIGATE_CONTINUE
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.NAVIGATE_TO_LOGIN
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.CreateAchievesDBIfNotExist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val login: UseCaseLogin,
    private val createAchievesDBIfNotExist: CreateAchievesDBIfNotExist
) : ViewModel() {

    private val _isDatabaseCreated = MutableStateFlow<Resource<String>>(Resource.initialized(null))
    val isDatabaseCreated: StateFlow<Resource<String>> = _isDatabaseCreated.asStateFlow()

    private val _isExistSavedUser = MutableStateFlow<Resource<User>>(Resource.initialized(null))
    val isExistSavedUser: StateFlow<Resource<User>> = _isExistSavedUser.asStateFlow()

    private val _navigation = MutableStateFlow<Event<String>>(Event(""))
    val navigation: StateFlow<Event<String>> = _navigation.asStateFlow()

    init {
        viewModelScope.launch {

            _isDatabaseCreated.value = Resource.loading(null)
            _isDatabaseCreated.value =createAchievesDBIfNotExist.execute()

            _isExistSavedUser.value = login.execute()


            /*_isDatabaseCreated.value = LoginUiState.Loading
            try {
                //createAchievesDBIfNotExist.execute()
                _isDatabaseCreated.value = LoginUiState.Success(null)
            }
            catch (e: Exception) {
                _isDatabaseCreated.value = LoginUiState.Error(
                    "Error while Initializing Database. Please restart Application"
                )
            }

            _isExistSavedUser.value = LoginUiState.Loading
            try {
                login.execute().let { user ->
                    _isExistSavedUser.value = LoginUiState.Success(user)
                }
            }   catch (e: Exception){
                _isExistSavedUser.value = LoginUiState.Error("Can't find user's data")
            }*/
        }
    }


    fun logInWithWgOpenId() {
        _navigation.value = Event(NAVIGATE_TO_LOGIN)
    }

    fun continueAsSavedUser() {
        _navigation.value = Event(NAVIGATE_CONTINUE)
    }

/*    sealed class LoginUiState {
        data class Success(val data: User?) : LoginUiState()
        data class Error(val message: String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }

    sealed class NavigateEvent {
        object Empty : NavigateEvent()
        object ToUserAchieves : NavigateEvent()
        object ToWebView : NavigateEvent()
    }*/
}
