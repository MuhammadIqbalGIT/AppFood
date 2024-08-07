package com.example.core.data.repository

import com.example.core.data.source.OnlyNetworkBoundResource
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.datasource.CategoryDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.Category
import com.example.core.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    private val categoryDataSource: CategoryDataSource
) : ICategoryRepository{

    override fun getAllCategory(): Flow<Resource<List<Category>>> =
        object : OnlyNetworkBoundResource<List<Category>>(){
            override suspend fun createCall(): Flow<ApiResponse<List<Category>>> {
                return categoryDataSource.getAllCategory()
            }
        }.asFlow()
}