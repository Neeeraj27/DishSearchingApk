package com.example.dishdisplayapp._Ui.Screens


import android.app.Activity
import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.example.dishdisplayapp._Ui.components.BottomNavTab
import com.example.dishdisplayapp._Ui.components.BottomNavigationBar
import com.example.dishdisplayapp._Ui.components.BottomSheetContent
import com.example.dishdisplayapp._Ui.components.SideNavigationBar
import com.example.dishdisplayapp.data.model.Dish
import com.example.dishdisplayapp.viewmodel.DishViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(viewModel: DishViewModel) {
    val context = LocalContext.current
    var backPressedOnce by remember { mutableStateOf(false) }

    BackHandler {
        if (backPressedOnce) {
            (context as? Activity)?.finish()
        } else {
            backPressedOnce = true
        }
    }

    LaunchedEffect(backPressedOnce) {
        if (backPressedOnce) {
            Toast.makeText(context, "Please press back again to exit", Toast.LENGTH_SHORT).show()
            delay(2000)
            backPressedOnce = false
        }
    }
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    var selectedTab by remember { mutableStateOf(BottomNavTab.Cook) }
    var selectedDishesMap by remember { mutableStateOf<Map<String, Dish>>(emptyMap()) }

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var selectedDish by remember { mutableStateOf<Dish?>(null) }

    val closeBottomSheet = {
        coroutineScope.launch {
            sheetState.hide()
        }
        selectedTab = BottomNavTab.Cook
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        modifier = Modifier.navigationBarsPadding(),
        sheetContent = {
            if (selectedDish != null) {
                BottomSheetContent(
                    dish = selectedDish!!,
                    viewModel,
                    onCookNow = {
                        selectedDishesMap = selectedDishesMap.toMutableMap().apply {
                            put(it.dishId, it.copy(isPublished = true))
                        }
                        coroutineScope.launch { sheetState.hide() }
                    },
                    onDelete = {
                        selectedDishesMap = selectedDishesMap.toMutableMap().apply {
                            remove(it.dishId)
                        }
                        coroutineScope.launch { sheetState.hide() }
                    },
                    onClose = { closeBottomSheet() }
                )
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                // Showing BottomAppBar only if in portrait mode
                if (isPortrait) {
                    BottomNavigationBar(selectedTab, onTabSelected = { selectedTab = it })
                }
            }
        ) { paddingValues ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Showing side navigation bar if in landscape mode
                if (!isPortrait) {
                    SideNavigationBar(selectedTab, onTabSelected = { selectedTab = it })
                }

                // Main content area
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    when (selectedTab) {
                        BottomNavTab.Cook -> DishScreen(
                            viewModel,
                            sheetState,
                            coroutineScope,
                            selectedDishesMap,
                            onDishSelected = { selectedDish = it }
                        )
                        BottomNavTab.Favourite -> FavouriteScreen()
                        BottomNavTab.Manual -> ManualScreen()
                        BottomNavTab.Device -> DeviceScreen()
                        BottomNavTab.Preferences -> PreferencesScreen()
                        BottomNavTab.Settings -> SettingScreen()
                    }
                }
            }
        }
    }
}