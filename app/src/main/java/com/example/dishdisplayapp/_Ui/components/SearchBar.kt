package com.example.dishdisplayapp._Ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dishdisplayapp.viewmodel.DishViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchBar(
    query: String,
    viewModel: DishViewModel,
    onQueryChange: (String) -> Unit
) {
    // List of placeholder suggestions
    val suggestions = listOf("Biryani", "Pizza", "Paneer", "Ice Cream", "Burger")
    var currentIndex by remember { mutableStateOf(0) }
    var placeholderText by remember { mutableStateOf(suggestions[currentIndex]) }

    val infiniteTransition = rememberInfiniteTransition()
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Coroutine to change the placeholder text periodically
    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            currentIndex = (currentIndex + 1) % suggestions.size
            placeholderText = suggestions[currentIndex]
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Bar with dynamic placeholder
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Search ", color = Color.Gray)
                    AnimatedContent(
                        targetState = placeholderText,
                        transitionSpec = {
                            slideInVertically { height -> height } + fadeIn() with
                                    slideOutVertically { height -> -height } + fadeOut()
                        }
                    ) { targetText ->
                        Text(text = targetText, color = Color.Gray)
                    }
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon", modifier = Modifier.padding(top = 3.dp))
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(2.dp, Color(0xFF2196F3), RoundedCornerShape(12.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { /* Handle search action */ })
        )

        Spacer(modifier = Modifier.width(8.dp))


        if (viewModel.isTimerActive)
            RotatingClockTimer(viewModel)

            // Notification Icon Button
            IconButton(
                onClick = { /* Handle Notification click */ },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFF2196F3)
                )
            }



        // Power Off Icon Button (Red)
        IconButton(
            onClick = { /* Handle Power Off click */ },
            modifier = Modifier.background(Color.Transparent)
        ) {
            Icon(
                imageVector = Icons.Default.PowerSettingsNew,
                contentDescription = "Power Off",
                tint = Color.Red // Red color
            )
        }
    }
}


@Composable
fun RotatingClockTimer(viewModel: DishViewModel) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Create a gradient brush for the rotating border
    val gradientBrush = Brush.sweepGradient(
        colors = listOf(Color.Magenta, Color.Cyan, Color.Yellow, Color.Magenta)
    )

    // Display the selected dish image
    val dish = viewModel.selectedDish

    Box(
        modifier = Modifier
            .size(30.dp) // Outer size for the rotating border
            .rotate(if (viewModel.isTimerActive) rotationAngle else 0f)
            .border(3.dp, gradientBrush, CircleShape)
            .padding(4.dp), // Padding to make space for the border
        contentAlignment = Alignment.Center
    ) {
        if (dish != null) {
            // Display the dish image inside the rotating border
            AsyncImage(
                model = dish.imageUrl,
                contentDescription = "Dish Image",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            // Fallback in case the dish is null
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Gray, CircleShape)
            )
        }
    }
}
