package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.*
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSearch
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.useCase.CreateAchievesDBIfNotExist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val login: UseCaseLogin,
    private val searchUser: UseCaseSearch,
    private val createAchievesDBIfNotExist: CreateAchievesDBIfNotExist
    ) : ViewModel() {

    init{
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                createAchievesDBIfNotExist.execute()
            }
        }

    }
    private val _list = MutableStateFlow<Resource<List<User>>>(Resource.success(null))
    val list : StateFlow<Resource<List<User>>> = _list.asStateFlow()

    private val _navigateToWebViewFragment = MutableStateFlow<Event<Boolean>>(Event(false))
    val navigateToWebViewFragment: StateFlow<Event<Boolean>> = _navigateToWebViewFragment.asStateFlow()

    fun logIn(){
        _navigateToWebViewFragment.value = Event(true)
        //viewModelScope.launch(Dispatchers.IO) { login.execute() }
        //navController.navigate(R.id.webViewFragment)
    }

    fun search(search: String){
        _list.value = Resource.loading(null)
        viewModelScope.launch {
            val data = searchUser.execute(search = search)
            _list.value = Resource.success(data)
        }
    }
}

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
}