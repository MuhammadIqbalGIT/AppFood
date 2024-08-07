package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getAllCategory(
    ) : Response<CategoryResponse>
}