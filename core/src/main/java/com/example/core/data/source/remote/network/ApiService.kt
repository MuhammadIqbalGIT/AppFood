package com.example.core.data.source.remote.network

import MealResponseById
import com.example.core.data.source.remote.response.CategoryResponse
import com.example.core.data.source.remote.response.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/json/v1/1/categories.php")
    suspend fun getAllCategory(
    ): Response<CategoryResponse>


    @GET("api/json/v1/1/filter.php")
    suspend fun getMealsByCategory(
        @Query("c") c: String
    ): Response<MealResponse>

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealsDetailsById(
        @Query("i") i: String
    ): Response<MealResponseById>

}