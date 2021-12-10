package com.bondidos.wotstatisticbybondidos.presentation.ui.login.login_fragment

import androidx.lifecycle.*
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.NAVIGATE_CONTINUE
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.NAVIGATE_TO_LOGIN
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.CreateAchievesDBIfNotExist
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogout
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val login: UseCaseLogin,
    private val createAchievesDBIfNotExist: CreateAchievesDBIfNotExist,
    private val logout: UseCaseLogout
) : ViewModel() {

    private val _isDatabaseCreated = MutableStateFlow<Resource<String>>(Resource.initialized(null))
    val isDatabaseCreated: StateFlow<Resource<String>> = _isDatabaseCreated.asStateFlow()

    private val _isExistSavedUser = MutableStateFlow<Resource<User>>(Resource.initialized(null))
    val isExistSavedUser: StateFlow<Resource<User>> = _isExistSavedUser.asStateFlow()

    private val _navigation = MutableStateFlow(Event(""))
    val navigation: StateFlow<Event<String>> = _navigation.asStateFlow()

    init {
        viewModelScope.launch {
            _isDatabaseCreated.value = Resource.loading(null)
            _isDatabaseCreated.value = createAchievesDBIfNotExist.execute()
            _isExistSavedUser.value = login.execute()
        }
    }


    fun logInWithWgOpenId() {
        _navigation.value = Event(NAVIGATE_TO_LOGIN)
    }

    fun continueAsSavedUser() {
        _navigation.value = Event(NAVIGATE_CONTINUE)
    }

    fun logout() {
        viewModelScope.launch {
            _isExistSavedUser.value = Resource.loading(null)
            _isExistSavedUser.value = logout.execute()
        }
    }
}
