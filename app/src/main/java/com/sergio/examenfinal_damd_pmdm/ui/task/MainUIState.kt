package com.sergio.examenfinal_damd_pmdm.ui.task

import com.sergio.examenfinal_damd_pmdm.data.network.model.TaskResponse

data class MainUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val response: List<TaskResponse>? = null
)