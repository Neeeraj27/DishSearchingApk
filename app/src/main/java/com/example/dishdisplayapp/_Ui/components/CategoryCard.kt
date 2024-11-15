package com.example.dishdisplayapp._Ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun CategoryCard(category: Category) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        backgroundColor = Color(0xFFF5F5F5),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .wrapContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            // Circular image
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Category name
            Text(
                text = category.name,
                fontSize = 14.sp,
                color = Color(0xFF1E88E5),
                fontWeight = FontWeight.Medium
            )
        }
    }
}


val dummyCategories = listOf(
    Category("Rice items", "https://i.pinimg.com/736x/85/0e/d3/850ed34ff7bcbe716ac65f55ea1cc3a7.jpg"),
    Category("Indian", "https://i.pinimg.com/736x/16/92/b3/1692b3193ce168983c05e8f42b2997c6.jpg"),
    Category("Curries", "https://i.pinimg.com/736x/0d/a8/92/0da8922b83852e4fb9076aec7ab37352.jpg"),
    Category("Soups", "https://i.pinimg.com/736x/1d/96/70/1d96705f50429ef107e990b511f3c165.jpg"),
    Category("Desserts", "https://i.pinimg.com/736x/ea/0a/01/ea0a01db819a9e1e66c9ccd8138e7428.jpg"),
    Category("Snacks", "https://i.pinimg.com/736x/e7/ad/34/e7ad343c1060bad6359f1eab5ce42bb5.jpg"),
    Category("Chinese", "https://i.pinimg.com/736x/e0/dd/c0/e0ddc0615de172b35cc1d5b838772c4e.jpg"),
    Category("Chicken", "https://i.pinimg.com/736x/52/1a/01/521a01d28f8bc09a8042ee20a0f6451c.jpg"),
    Category("Aloo Paratha", "https://i.pinimg.com/736x/91/f9/86/91f986daf7b7b0122246ce90a6172421.jpg"),
)

// Data class for category
data class Category(val name: String, val imageUrl: String)
