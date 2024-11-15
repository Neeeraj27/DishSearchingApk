package com.example.dishdisplayapp._Ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dishdisplayapp.data.model.Dish
import com.example.dishdisplayapp.viewmodel.DishViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun BottomSheetContent(
    dish: Dish,
    viewModel: DishViewModel,
    onCookNow: (Dish) -> Unit,
    onDelete: (Dish) -> Unit,
    onClose: () -> Unit
) {
    var selectedPeriod by remember { mutableStateOf("AM") }
    var selectedHour by remember { mutableStateOf(6) }
    var selectedMinute by remember { mutableStateOf(30) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(24.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar with title and close button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Schedule cooking time",
                style = MaterialTheme.typography.h6,
                color = Color(0xFF233F97),
                modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Time picker card with AM/PM selection
        // Time picker card with AM/PM selection
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            // Time Picker Columns
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimePickerColumn(
                    range = 1..12,
                    modifier = Modifier.weight(1f),
                    initialValue = selectedHour,
                    onValueChange = { selectedHour = it }
                )
                Text(text = ":", fontSize = 32.sp, color = Color.Gray)
                TimePickerColumn(
                    range = 0..59,
                    modifier = Modifier.weight(1f),
                    initialValue = selectedMinute,
                    onValueChange = { selectedMinute = it }
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // AM/PM Selection
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AMPMButton("AM", isSelected = selectedPeriod == "AM") {
                    selectedPeriod = "AM"
                }
                Spacer(modifier = Modifier.height(8.dp))
                AMPMButton("PM", isSelected = selectedPeriod == "PM") {
                    selectedPeriod = "PM"
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Spacer(modifier = Modifier.height(24.dp))

        // Bottom buttons: Delete, Re-schedule, Cook Now
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Delete button
            TextButton(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.stopTimer()
                        onDelete(dish)
                    }
                }
            ) {
                Text(text = "Delete", color = Color.Red)
            }

            OutlinedButton(
                onClick = { /* Handle Re-schedule */ },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFFFA500))
            ) {
                Text(text = "Re-schedule", color = Color(0xFFFFA500))
            }

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.startTimer(dish, selectedHour, selectedMinute, selectedPeriod)

                        onCookNow(dish)
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA500)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Cook Now", color = Color.White)
            }
        }
    }
}