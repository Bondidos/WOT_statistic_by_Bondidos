package com.bondidos.wotstatisticbybondidos.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.wotstatisticbybondidos.domain.useCase.UseCaseSaveUser
import com.bondidos.wotstatisticbybondidos.domain.other.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebViewViewModel@Inject constructor(private val saveUser: UseCaseSaveUser) : ViewModel() {

    private val _isSaved = MutableStateFlow<Event<Boolean?>>(Event(null))
    val isSaved : StateFlow<Event<Boolean?>> = _isSaved.asStateFlow()

    fun saveUser (url: String){
        viewModelScope.launch {
            _isSaved.value = saveUser.execute(url)
        }
    }
}

