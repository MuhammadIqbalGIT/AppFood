package com.example.appfood.ui.filter_category

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
class CategoryFilterViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase

) : ViewModel() {
    private val _mealsByCategory = MutableStateFlow<Resource<List<Meal>>>(Resource.Loading())
    val category: Flow<Resource<List<Meal>>>
        get() = _mealsByCategory


    fun getMealsByCategory(category : String){
        viewModelScope.launch {
            _mealsByCategory.value = Resource.Loading()
            categoryUseCase.getMealsByCategory(category).collect{
                _mealsByCategory.value = it
            }
        }
    }
}