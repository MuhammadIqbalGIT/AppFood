package com.example.core.data.source.remote.datasource

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoryDataSource(private val api: ApiService){

    suspend fun getAllCategory(): Flow<ApiResponse<List<Category>>> =
        flow {
            try {
                val response = api.getAllCategory()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        if (body.categories.isNotEmpty()) emit(ApiResponse.Success(body.categories)) else emit(ApiResponse.Empty)
                    } else emit(ApiResponse.Error("Terjadi Kesalahan"))
                } else emit(ApiResponse.Error("Terjadi Kesalahan"))
            } catch (e: Exception) {
                emit(ApiResponse.Error("Terjadi Kesalahan"))
            }
        }.flowOn(Dispatchers.IO)
}