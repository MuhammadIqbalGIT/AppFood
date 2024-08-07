package com.example.core.domain.repository

import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Category
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository{
    fun getAllCategory(): Flow<Resource<List<Category>>>
}