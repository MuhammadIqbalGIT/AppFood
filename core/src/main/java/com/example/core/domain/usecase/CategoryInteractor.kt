package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Category
import com.example.core.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow



class CategoryInteractor(private val categoryRepository: ICategoryRepository): CategoryUseCase {

    override fun getAllCategory(
    ): Flow<Resource<List<Category>>> =
        categoryRepository.getAllCategory()
}