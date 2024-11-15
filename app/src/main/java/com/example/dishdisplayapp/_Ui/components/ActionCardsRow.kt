package com.example.dishdisplayapp._Ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun ActionCardsRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        item {
            ActionCard(
                image = "https://i.fbcd.co/products/resized/resized-750-500/2f40bb531c400c95e40953351a533a937bd2dde8f271affd6f62086035428bbb.webp",
                text = "Explore all dishes"
            )
        }
        item {
            ActionCard(
                image = "https://wallpapers.com/images/high/best-food-background-0neqcd9ozlv3js9y.webp",
                text = "Confused what to cook?"
            )
        }
    }
}

@Composable
fun ActionCard(
    image: String,
    text: String
) {
    Card(
        modifier = Modifier
            .width(450.dp)
            .height(60.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 8.dp
    ) {
        Box {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Overlapping Text
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.Transparent)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}