package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Category
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    fun getAllCategory(): Flow<Resource<List<Category>>>
}