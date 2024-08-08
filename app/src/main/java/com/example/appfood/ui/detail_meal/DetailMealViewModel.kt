package com.example.appfood.ui.detail_meal

import Meals
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Meal
import com.example.core.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class DetailMealViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase

) : ViewModel() {
    private val _mealsDetailsById = MutableStateFlow<Resource<List<Meals>>>(Resource.Loading())
    val mealsDetailsById: Flow<Resource<List<Meals>>>
        get() = _mealsDetailsById


    fun getMealsDetailsById(idMeal : String){
        viewModelScope.launch {
            _mealsDetailsById.value = Resource.Loading()
            categoryUseCase.getMealsDetailsById(idMeal).collect{
                _mealsDetailsById.value = it
            }
        }
    }
}