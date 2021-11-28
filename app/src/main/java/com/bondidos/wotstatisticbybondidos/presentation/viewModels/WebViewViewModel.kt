package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseLogin
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSaveUser
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSearch
import com.bondidos.wotstatisticbybondidos.presentation.other.Event
import com.bondidos.wotstatisticbybondidos.presentation.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
import javax.inject.Inject

class WebViewViewModel@Inject constructor(private val saveUser: UseCaseSaveUser) : ViewModel() {

    private val _isSaved = MutableStateFlow<Event<String>>(Event("null"))
    val isSaved : StateFlow<Event<String>> = _isSaved.asStateFlow()

    fun saveUser (url: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isSuccess = saveUser.execute(url)
                _isSaved.value = Event(isSuccess.toString())
            }
        }

    }
}

class WebViewViewModelFactory (
    private val saveUser: UseCaseSaveUser
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(WebViewViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WebViewViewModel(saveUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}