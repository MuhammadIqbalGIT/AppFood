package com.example.core.domain.usecase

import CategoryAll
import Meals
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Category
import com.example.core.data.source.remote.response.Meal
import com.example.core.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CategoryInteractor @Inject constructor(private val categoryRepository: ICategoryRepository): CategoryUseCase {

    override fun getAllCategory(
    ): Flow<Resource<List<Category>>> =
        categoryRepository.getAllCategory()

    override fun getMealsByCategory(category : String
    ): Flow<Resource<List<Meal>>> =
        categoryRepository.getMealsByCategory(category)

    override fun getMealsDetailsById(idMeal : String
    ): Flow<Resource<List<Meals>>> =
        categoryRepository.getMealsDetailsById(idMeal)

    override fun getMealByName(nameMeal : String
    ): Flow<Resource<List<Meals>>> =
        categoryRepository.getMealByName(nameMeal)

    override fun getListCategory(
    ): Flow<Resource<List<CategoryAll>>> =
        categoryRepository.getListCategory()
}