package com.example.appfood.ui.search_meal

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
class SearchMealViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase

) : ViewModel() {
    private val _mealByName = MutableStateFlow<Resource<List<Meals>>>(Resource.Loading())
    val mealByName: Flow<Resource<List<Meals>>>
        get() = _mealByName


    fun getMealByName(nameMeal : String){
        viewModelScope.launch {
            _mealByName.value = Resource.Loading()
            categoryUseCase.getMealByName(nameMeal).collect{
                _mealByName.value = it
            }
        }
    }
}