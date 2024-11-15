package com.example.dishdisplayapp.data.repository

import com.example.dishdisplayapp.data.api.DishApiService
import com.example.dishdisplayapp.data.model.Dish
import javax.inject.Inject

class DishRepository @Inject constructor(
    private val apiService: DishApiService
) {
    suspend fun fetchDishes(): List<Dish>? {
        val response = apiService.getDishes()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}