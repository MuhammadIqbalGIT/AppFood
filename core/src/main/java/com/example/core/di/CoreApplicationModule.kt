package com.example.core.di

import com.example.core.BuildConfig
import com.example.core.data.repository.CategoryRepository
import com.example.core.data.source.remote.datasource.CategoryDataSource
import com.example.core.data.source.remote.network.ApiInterceptor
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.repository.ICategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreApplicationModule {

    @Singleton
    @Provides
    fun provideApiService(apiInterceptor: ApiInterceptor): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(loggingInterceptor)
        }
        client.addInterceptor(apiInterceptor)
        client.connectTimeout(1L, TimeUnit.MINUTES)
        client.readTimeout(5L, TimeUnit.MINUTES)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryDataSource(
        apiService: ApiService,
    ): CategoryDataSource = CategoryDataSource(apiService)

    @Singleton
    @Provides
    fun provideCategoryRepository(
        apiService: ApiService,
        source: CategoryDataSource
    ): ICategoryRepository = CategoryRepository(apiService, source)
}