package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.*
import androidx.navigation.NavController
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSearch
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
    private val searchUser: UseCaseSearch
    ) : ViewModel() {

    private val _list = MutableStateFlow<List<User>>(emptyList())
    val list : StateFlow<List<User>> = _list.asStateFlow()

    fun logIn(){
        //viewModelScope.launch(Dispatchers.IO) { login.execute() }
        //navController.navigate(R.id.webViewFragment)
    }

    fun search(search: String){
        viewModelScope.launch{ _list.value = searchUser.execute(search = search) }
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