package com.sergio.examenfinal_damd_pmdm.ui.task

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.examenfinal_damd_pmdm.data.network.TaskAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var _mainState: MutableStateFlow<MainUIState> = MutableStateFlow(MainUIState())
    val mainState: StateFlow<MainUIState> get() = _mainState


    fun getTask() {
        _mainState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val response = TaskAPI.service.getTask("Sergio", "Departamento")
            if (response.isSuccessful) {
                _mainState.update{it.copy(response = response.body(), isLoading = false)}
            } else {
                _mainState.update{it.copy(isError = true, isLoading = false)}
            }
        }
    }
}