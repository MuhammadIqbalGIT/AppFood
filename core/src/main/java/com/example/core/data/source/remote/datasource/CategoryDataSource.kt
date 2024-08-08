package com.example.core.data.source.remote.datasource

import CategoryAll
import Meals
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.Category
import com.example.core.data.source.remote.response.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

    class CategoryDataSource @Inject constructor (private val api: ApiService){

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

    suspend fun getMealsByCategory(category : String): Flow<ApiResponse<List<Meal>>> =
        flow {
            try {
                val response = api.getMealsByCategory(category)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        if (body.meals.isNotEmpty()) emit(ApiResponse.Success(body.meals)) else emit(ApiResponse.Empty)
                    } else emit(ApiResponse.Error("Terjadi Kesalahan"))
                } else emit(ApiResponse.Error("Terjadi Kesalahan"))
            } catch (e: Exception) {
                emit(ApiResponse.Error("Terjadi Kesalahan"))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMealsDetailsById(idMeal : String): Flow<ApiResponse<List<Meals>>> =
        flow {
            try {
                val response = api.getMealsDetailsById(idMeal)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        if (body.meals.isNotEmpty()) emit(ApiResponse.Success(body.meals)) else emit(ApiResponse.Empty)
                    } else emit(ApiResponse.Error("Terjadi Kesalahan"))
                } else emit(ApiResponse.Error("Terjadi Kesalahan"))
            } catch (e: Exception) {
                emit(ApiResponse.Error("Terjadi Kesalahan"))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getMealByName(nameMeal : String): Flow<ApiResponse<List<Meals>>> =
        flow {
            try {
                val response = api.getMealByName(nameMeal)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        if (body.meals.isNotEmpty()) emit(ApiResponse.Success(body.meals)) else emit(ApiResponse.Empty)
                    } else emit(ApiResponse.Error("Data Tidak Ditemukan"))
                } else emit(ApiResponse.Error("Data Tidak Ditemukan"))
            } catch (e: Exception) {
                emit(ApiResponse.Error("Data Tidak Ditemukan"))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getListCategory(): Flow<ApiResponse<List<CategoryAll>>> =
        flow {
            try {
                val response = api.getListCategory()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        if (body.meals.isNotEmpty()) emit(ApiResponse.Success(body.meals)) else emit(ApiResponse.Empty)
                    } else emit(ApiResponse.Error("Terjadi Kesalahan"))
                } else emit(ApiResponse.Error("Terjadi Kesalahan"))
            } catch (e: Exception) {
                emit(ApiResponse.Error("Terjadi Kesalahan"))
            }
        }.flowOn(Dispatchers.IO)

}