package com.example.appfood.ui.filter_category.sheet

import CategoryAll
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
class AllCategoryViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase

) : ViewModel() {
    private val _listCategory = MutableStateFlow<Resource<List<CategoryAll>>>(Resource.Loading())
    val allCategory: Flow<Resource<List<CategoryAll>>>
        get() = _listCategory


    fun getListCategory() {
        viewModelScope.launch {
            _listCategory.value = Resource.Loading()
            categoryUseCase.getListCategory().collect {
                _listCategory.value = it
            }
        }
    }
}