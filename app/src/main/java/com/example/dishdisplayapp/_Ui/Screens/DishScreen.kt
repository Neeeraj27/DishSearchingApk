package com.example.dishdisplayapp._Ui.Screens



import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dishdisplayapp._Ui.components.ActionCardsRow
import com.example.dishdisplayapp._Ui.components.CategoryCard
import com.example.dishdisplayapp._Ui.components.DishItem
import com.example.dishdisplayapp._Ui.components.SearchBar
import com.example.dishdisplayapp._Ui.components.dummyCategories
import com.example.dishdisplayapp.data.model.ApiState
import com.example.dishdisplayapp.data.model.Dish
import com.example.dishdisplayapp.data.model.additionalDishes
import com.example.dishdisplayapp.viewmodel.DishViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DishScreen(
    viewModel: DishViewModel,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    selectedDishesMap: Map<String, Dish>,
    onDishSelected: (Dish) -> Unit
) {
    val state by viewModel.dishState.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar at the top
        SearchBar(query = query, viewModel, onQueryChange = { query = it })

        Spacer(modifier = Modifier.height(16.dp))

        // Horizontal list of categories
        androidx.compose.material3.Text(
            text = "What's on your mind?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E88E5),
            modifier = Modifier.padding(bottom = 8.dp, start = 10.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(dummyCategories) { category ->
                CategoryCard(category)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title above the LazyVerticalGrid
        androidx.compose.material3.Text(
            text = "Recommendations",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E88E5),
            modifier = Modifier.padding(bottom = 8.dp, start = 10.dp)
        )

        when (state) {
            is ApiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is ApiState.Success -> {
                val dishes = (state as ApiState.Success).data

                // Filter dishes based on the search query

                val allDishes = (dishes + additionalDishes).distinctBy { it.dishId }

                val filteredDishes = allDishes.filter {
                    it.dishName.contains(query, ignoreCase = true)
                }
                val configuration = LocalConfiguration.current
                val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

                if (isPortrait) {
                    // Use LazyVerticalGrid for portrait mode
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredDishes) { dish ->
                            val isSelected = selectedDishesMap.containsKey(dish.dishId)

                            DishItem(dish, isSelected, onClick = {
                                onDishSelected(dish)
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            })
                        }
                    }
                } else {
                    // Use LazyRow for landscape mode

                    LazyRow(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f),
                        contentPadding = PaddingValues(horizontal = 48.dp, vertical = 30.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {

                        items(filteredDishes) { dish ->
                            val isSelected = selectedDishesMap.containsKey(dish.dishId)
                            DishItem(dish, isSelected, onClick = {
                                onDishSelected(dish)
                                coroutineScope.launch { sheetState.show() }
                            })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Adding ActionCardsRow below the dishes
                ActionCardsRow()
            }
            is ApiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    androidx.compose.material3.Text(text = "Error: ${(state as ApiState.Error).message}")
                }
            }
        }
    }
}
