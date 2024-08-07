package com.example.appfood.di

import com.example.core.domain.repository.ICategoryRepository
import com.example.core.domain.usecase.CategoryInteractor
import com.example.core.domain.usecase.CategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideCategoryUseCase(repository: ICategoryRepository): CategoryUseCase {
        return CategoryInteractor(repository)
    }
}