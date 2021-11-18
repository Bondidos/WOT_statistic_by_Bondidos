package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.*
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val login: UseCaseLogin,
    private val searchUser: UseCaseSearch
    ) : ViewModel() {

    private val _list = MutableLiveData<List<User>>()
    val list : LiveData<List<User>> get() = _list

    fun logIn(){
        viewModelScope.launch(Dispatchers.IO) { login.execute() }
    }

    fun search(search: String){
        viewModelScope.launch(Dispatchers.IO) { _list.value = searchUser.execute(search = search) }
    }
}

class LoginViewModelFactory (
    private val login: UseCaseLogin,
    private val search: UseCaseSearch
    ): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(login,search) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}