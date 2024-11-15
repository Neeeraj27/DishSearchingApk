package com.example.dishdisplayapp.di

import com.example.dishdisplayapp.data.api.DishApiService
import com.example.dishdisplayapp.data.repository.DishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDishApiService(retrofit: Retrofit): DishApiService {
        return retrofit.create(DishApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDishRepository(apiService: DishApiService): DishRepository {
        return DishRepository(apiService)
    }
}