package com.sergio.examenfinal_damd_pmdm.ui.initial

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.examenfinal_damd_pmdm.data.storage.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitialViewModel: ViewModel() {

    fun saveDepartment(context: Context, username: String){
        viewModelScope.launch(Dispatchers.IO){
            DataStoreManager.saveDepartment(context, username)
        }
    }
}