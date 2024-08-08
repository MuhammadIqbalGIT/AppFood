package com.example.core.domain.usecase

import CategoryAll
import Meals
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Category
import com.example.core.data.source.remote.response.Meal
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    fun getAllCategory(): Flow<Resource<List<Category>>>
    fun getMealsByCategory(category: String): Flow<Resource<List<Meal>>>
    fun getMealsDetailsById(idMeal: String): Flow<Resource<List<Meals>>>
    fun getMealByName(nameMeal: String): Flow<Resource<List<Meals>>>
    fun getListCategory(): Flow<Resource<List<CategoryAll>>>
}