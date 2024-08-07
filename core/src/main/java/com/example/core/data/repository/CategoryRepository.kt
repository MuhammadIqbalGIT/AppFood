package com.example.core.data.repository

import Meals
import com.example.core.data.source.OnlyNetworkBoundResource
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.datasource.CategoryDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.Category
import com.example.core.data.source.remote.response.Meal
import com.example.core.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val apiService: ApiService,
    private val categoryDataSource: CategoryDataSource
) : ICategoryRepository{

    override fun getAllCategory(): Flow<Resource<List<Category>>> =
        object : OnlyNetworkBoundResource<List<Category>>(){
            override suspend fun createCall(): Flow<ApiResponse<List<Category>>> {
                return categoryDataSource.getAllCategory()
            }
        }.asFlow()

    override fun getMealsByCategory(category: String): Flow<Resource<List<Meal>>> =
        object : OnlyNetworkBoundResource<List<Meal>>(){
            override suspend fun createCall(): Flow<ApiResponse<List<Meal>>> {
                return categoryDataSource.getMealsByCategory(category)
            }
        }.asFlow()

    override fun getMealsDetailsById(idMeal: String): Flow<Resource<List<Meals>>> =
        object : OnlyNetworkBoundResource<List<Meals>>(){
            override suspend fun createCall(): Flow<ApiResponse<List<Meals>>> {
                return categoryDataSource.getMealsDetailsById(idMeal)
            }
        }.asFlow()
}