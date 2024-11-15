package com.example.dishdisplayapp.data.model

data class Dish(
    val dishName: String,
    val dishId: String,
    val imageUrl: String,
    val isPublished: Boolean
)



//Additional Hardcoded data
val additionalDishes = listOf(
    Dish(dishName = "Pasta Primavera", dishId = "6", imageUrl = "https://i.pinimg.com/736x/28/05/12/2805128475d46c58db09fd62befbb633.jpg", isPublished = true),
    Dish(dishName = "Spicy Tacos", dishId = "7", imageUrl = "https://i.pinimg.com/736x/2b/7d/99/2b7d99908a31683f7b73fb4947a7e2b1.jpg", isPublished = true),
    Dish(dishName = "Sushi Platter", dishId = "8", imageUrl = "https://i.pinimg.com/736x/b3/b1/a9/b3b1a987f14dd2ca7c6a4d394fcbd52a.jpg", isPublished = true),
    Dish(dishName = "Cheeseburger", dishId = "9", imageUrl = "https://i.pinimg.com/736x/3b/6c/c6/3b6cc651e4cd1cbc7fa7d559a5b81810.jpg", isPublished = true),
    Dish(dishName = "Pani Puri", dishId = "10", imageUrl = "https://i.pinimg.com/736x/71/f7/98/71f798c008402030d479ee6d0a3c7795.jpg", isPublished = true),
    Dish(dishName = "Pizza", dishId = "10", imageUrl = "https://i.pinimg.com/736x/f2/8f/cc/f28fcc4e0b34bab096d0df3d528a23a4.jpg", isPublished = true),
    Dish(dishName = "MoMo", dishId = "10", imageUrl = "https://i.pinimg.com/736x/0b/5e/b4/0b5eb439a0fe2c71aa7b427d3402e1ea.jpg", isPublished = true),
)
sealed class ApiState {
    object Loading : ApiState()
    data class Success(val data: List<Dish>) : ApiState()
    data class Error(val message: String) : ApiState()
}