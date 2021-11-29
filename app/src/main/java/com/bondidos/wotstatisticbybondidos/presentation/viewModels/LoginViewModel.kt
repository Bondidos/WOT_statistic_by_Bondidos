package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.*
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.ACHIEVES_FRAGMENT
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.NULL
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.WEB_VIEW_FRAGMENT
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSearch
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.CreateAchievesDBIfNotExist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val login: UseCaseLogin,
    private val createAchievesDBIfNotExist: CreateAchievesDBIfNotExist
    ) : ViewModel() {

    private val _isDatabaseCreated = MutableStateFlow<Resource<Boolean>>(Resource.success(null))
    val isDatabaseCreated : StateFlow<Resource<Boolean>> = _isDatabaseCreated.asStateFlow()

    private val _isExistSavedUser = MutableStateFlow<Resource<User?>>(Resource.success(null))
    val isExistSavedUser : StateFlow<Resource<User?>> = _isExistSavedUser.asStateFlow()

    private val _navigation = MutableStateFlow(Event(NULL))
    val navigation: StateFlow<Event<String>> = _navigation.asStateFlow()

    init{
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                createAchievesDBIfNotExist.execute().let { it ->
                    _isDatabaseCreated.value = Resource.loading(null)
                    when (it){
                        true -> _isDatabaseCreated.value = Resource.success(it)
                        false -> _isDatabaseCreated.value = Resource.error("Error while Initializing Database",null)
                    }
                }
                login.execute().let { userFlow ->
                    _isExistSavedUser.value = Resource.loading(null)
                    userFlow.collect { listOfUser ->
                        when(listOfUser){
                            null -> _isExistSavedUser.value = Resource.error("Can't retrieve data about user ", null)
                            else -> if(listOfUser.isNotEmpty()) {
                                _isExistSavedUser.value = Resource.success(listOfUser.first())
                            } else _isExistSavedUser.value = Resource.error("Can't retrieve data about user ", null)
                        }
                    }
                }
            }
        }
    }

    fun logIn(){
        if(isExistSavedUser.value.data != null)
            _navigation.value = Event(ACHIEVES_FRAGMENT)
        else _navigation.value = Event(WEB_VIEW_FRAGMENT)
    }
}

/*
class LoginViewModelFactory (
    private val login: UseCaseLogin,
    private val search: UseCaseSearch,
    private val createAchievesDBIfNotExist: CreateAchievesDBIfNotExist
    ): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(login,search,createAchievesDBIfNotExist) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
