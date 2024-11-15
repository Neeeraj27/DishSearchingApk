package com.example.dishdisplayapp._Ui.components




import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dishdisplayapp.data.model.Dish

@Composable
fun DishItem(dish: Dish, isSelected: Boolean, onClick: () -> Unit) {
    // Use modifier to make the Card responsive to the screen width
    val backgroundColor = if (isSelected) Color(0xFF1E88E5) else Color.White
    val textColor = if (isSelected) Color.White else Color.Black



    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f) // Take up 40% of the screen width, you can adjust this value
            .fillMaxHeight(0.9f)
            .clickable {
                onClick()
            },// Content will wrap based on the height of its children
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                // Gray box with rounded corners, aspect ratio maintained for responsive layout
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(0.8f) // Make the width responsive
                        .fillMaxHeight(0.6f) // Make the width responsive
                        .aspectRatio(1f) // Keep the aspect ratio square
                        .background(
                            if (isSelected) Color.White else Color(0xFFE0E0E0),
                            RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Circular image inside the gray box
                    AsyncImage(
                        model = dish.imageUrl,
                        contentDescription = "Food Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(18.dp)// Makes it adjust to the content size
                            .clip(CircleShape)
                    )

                    // Small square with red border
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-6).dp, y = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(Color.White, RoundedCornerShape(2.dp))
                                .border(1.dp, Color.Red, RoundedCornerShape(2.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .background(Color.Red, CircleShape)
                            )
                        }
                    }

                    // Star rating badge positioned at the bottom center
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 12.dp)
                            .padding(bottom = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFFA726))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = "⭐ 4.2",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dish name and details
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    androidx.compose.material3.Text(
                        text = dish.dishName,
                        color = if (isSelected) Color.White else Color(0xFF1E88E5),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    // Center the icon and text
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 6.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Dining, contentDescription = "Cooking", modifier = Modifier.size(17.dp), tint = textColor)
                        Spacer(modifier = Modifier.width(3.dp))
                        androidx.compose.material3.Text(
                            text = "30 min •",
                            fontSize = 10.sp,
                            color = textColor
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        androidx.compose.material3.Text(
                            text = "Medium prep",
                            fontSize = 10.sp,
                            color = textColor
                        )

                    }
                }
            }
        }
    }
}

