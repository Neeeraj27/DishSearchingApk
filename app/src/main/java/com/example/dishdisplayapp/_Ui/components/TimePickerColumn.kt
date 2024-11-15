package com.example.dishdisplayapp._Ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimePickerColumn(
    range: IntRange,
    modifier: Modifier = Modifier,
    initialValue: Int,
    onValueChange: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    var selectedValue by remember { mutableStateOf(initialValue) }

    // Scroll to the initial value and center it
    LaunchedEffect(initialValue) {
        listState.scrollToItem(initialValue)
    }

    Box(
        modifier = modifier
            .height(180.dp) // Increased height for centering
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            flingBehavior = rememberSnapFlingBehavior(listState)
        ) {
            items(range.toList()) { value ->
                val isSelected = value == selectedValue

                Text(
                    text = value.toString().padStart(2, '0'),
                    fontSize = if (isSelected) 36.sp else 24.sp,
                    color = if (isSelected) Color(0xFF233F97) else Color.Gray,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            selectedValue = value
                            onValueChange(value)
                        },
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    // Automatically update the selected value when scrolling stops
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }.collect { index ->
            val middleIndex = index + 1 // Center item is offset by one
            val value = range.elementAtOrNull(middleIndex) ?: initialValue
            if (value != selectedValue) {
                selectedValue = value
                onValueChange(value)
            }
        }
    }
}


@Composable
fun AMPMButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color(0xFF233F97) else Color(0xFFE0E0E0))
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color(0xFF233F97),
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
