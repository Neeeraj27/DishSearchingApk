package com.example.dishdisplayapp.data.api

import com.example.dishdisplayapp.data.model.Dish
import retrofit2.Response
import retrofit2.http.GET

interface DishApiService {
    @GET("dev/nosh-assignment")
    suspend fun getDishes(): Response<List<Dish>>
}

