package com.sergio.examenfinal_damd_pmdm.data.network

import com.sergio.examenfinal_damd_pmdm.data.network.model.TaskResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TaskService {
    @GET("task/{departamento}")
    suspend fun getTask(
        @Header("Authorization") userName: String,
        @Path("departamento") departamento: String
    ): Response<List<TaskResponse>>
}