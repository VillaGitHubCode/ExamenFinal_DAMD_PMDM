package com.sergio.examenfinal_damd_pmdm.ui.task

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.examenfinal_damd_pmdm.data.network.TaskAPI
import com.sergio.examenfinal_damd_pmdm.data.storage.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {


    private var _department: MutableStateFlow<String?> = MutableStateFlow(null)
    val department: StateFlow<String?> = _department

    private var _mainState: MutableStateFlow<MainUIState> = MutableStateFlow(MainUIState())
    val mainState: StateFlow<MainUIState> get() = _mainState

    fun getDepartment(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreManager.getDepartment(context).collect{ department ->
                if (department != "No hay datos") {
                    _department.update {department}
                }
            }
        }
    }
    fun getTask(department: String) {
        _mainState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val response = TaskAPI.service.getTask("Sergio", department)
            if (response.isSuccessful) {
                _mainState.update{it.copy(response = response.body(), isLoading = false)}
            } else {
                _mainState.update{it.copy(isError = true, isLoading = false)}
            }
        }
    }
}