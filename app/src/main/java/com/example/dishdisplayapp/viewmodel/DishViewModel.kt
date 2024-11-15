package com.example.dishdisplayapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dishdisplayapp.data.model.ApiState
import com.example.dishdisplayapp.data.model.Dish
import com.example.dishdisplayapp.data.repository.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DishViewModel @Inject constructor(
    private val repository: DishRepository
) : ViewModel() {

    // State to manage dish list fetching
    private val _dishState = MutableStateFlow<ApiState>(ApiState.Loading)
    val dishState = _dishState.asStateFlow()

    // Timer-related states
    var isTimerActive by mutableStateOf(false)
        private set

    var scheduledTime by mutableStateOf("12:00 PM")
        private set

    var selectedDish: Dish? = null
        private set

    // Start the timer with a given dish and time
    fun startTimer(dish: Dish, hour: Int, minute: Int, period: String) {
        selectedDish = dish
        scheduledTime = formatTime(hour, minute, period)
        isTimerActive = true
    }

    // Stop the timer and clear the dish
    fun stopTimer() {
        isTimerActive = false
        selectedDish = null
    }

    init {
        fetchDishes()
    }

    // Fetch the list of dishes from the repository

    private fun fetchDishes() {
        viewModelScope.launch {
            _dishState.value = ApiState.Loading
            try {
                val dishes = repository.fetchDishes()
                if (dishes != null) {
                    _dishState.value = ApiState.Success(dishes)
                } else {
                    _dishState.value = ApiState.Error("Failed to fetch dishes")
                }
            } catch (e: Exception) {
                Log.e("FetchDishesError", "Error fetching dishes: ${e.localizedMessage}", e)
                _dishState.value = ApiState.Error(e.localizedMessage ?: "Unknown Error")
            }
        }
    }


    // Utility function to format time as "HH:MM AM/PM"
    private fun formatTime(hour: Int, minute: Int, period: String): String {
        val formattedMinute = minute.toString().padStart(2, '0')
        return "$hour:$formattedMinute $period"
    }
}
