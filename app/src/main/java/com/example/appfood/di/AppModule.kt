package com.example.appfood.di

import com.example.appfood.ui.category.CategoryViewModel
import com.example.core.domain.usecase.CategoryInteractor
import com.example.core.domain.usecase.CategoryUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CategoryUseCase> { CategoryInteractor(get()) }
}

val viewModelModule = module {
    viewModel { CategoryViewModel(get()) }
}