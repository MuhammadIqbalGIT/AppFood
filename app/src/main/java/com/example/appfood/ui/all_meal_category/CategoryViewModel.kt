package com.example.appfood.ui.all_meal_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Category
import com.example.core.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase

) : ViewModel() {
    private val _category = MutableStateFlow<Resource<List<Category>>>(Resource.Loading())
    val category: Flow<Resource<List<Category>>>
        get() = _category


    fun getAllCategory(){
        viewModelScope.launch {
            _category.value = Resource.Loading()
            categoryUseCase.getAllCategory().collect{
                _category.value = it
            }
        }
    }
}