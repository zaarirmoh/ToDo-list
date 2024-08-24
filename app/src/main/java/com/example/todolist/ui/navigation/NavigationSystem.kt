package com.example.todolist.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todolist.R
import com.example.todolist.data.Category
import com.example.todolist.ui.AppViewModelProvider
import com.example.todolist.ui.screens.mainScreens.homeScreen.HomeScreen
import com.example.todolist.ui.screens.mainScreens.homeScreen.HomeViewModel
import com.example.todolist.ui.screens.mainScreens.listDetailsScreen.TodosScreen
import com.example.todolist.ui.screens.onBoardingScreens.OnBoardingScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationSystem(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
){
    NavHost(
        navController = navController,
        startDestination = if (startDestination == "HomeScreen") HomeScreenN else OnBoardingScreenN()
    ) {
        composable<OnBoardingScreenN> {
            val args = it.toRoute<OnBoardingScreenN>()
            OnBoardingScreen(
                id = args.id,
                title = args.title,
                description = args.description,
                onNextClicked = {
                    val nextDestination: Any =
                        when (args.id) {
                            R.drawable.onboarding1 -> OnBoardingScreenN(
                                id = R.drawable.onboarding2,
                                title = "Create daily routine",
                                description = "In ToDo  you can create your personalized routine to stay productive"
                            )
                            R.drawable.onboarding2 -> OnBoardingScreenN(
                                id = R.drawable.onboarding3,
                                title = "Organize your tasks",
                                description = "You can organize your daily tasks by adding your tasks into separate categories"
                            )
                            else -> HomeScreenN
                        }
                    navController.navigate(nextDestination){
                        if (nextDestination is HomeScreenN){
                            popUpTo(OnBoardingScreenN){
                                inclusive = true
                            }
                        }
                    }
                }
            )
            val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            val coroutineScope = rememberCoroutineScope()
            if (args.id == R.drawable.onboarding3){
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        viewModel.addCategory(
                            Category(
                                title = "Today",
                                todos = 0,
                                icon = R.drawable.icon_3d_today,
                                color = 0xFF000000,
                                photo = R.drawable.todoimage3
                            )
                        )
                        viewModel.addCategory(
                            Category(
                                title = "Scheduled",
                                todos = 0,
                                icon = R.drawable.icon_3d_scheduled,
                                color = 0xFF000000,
                                photo = R.drawable.todoimage8
                            )
                        )
                        viewModel.addCategory(
                            Category(
                                title = "All",
                                todos = 0,
                                icon = R.drawable.icon_3d_all,
                                color = 0xFF000000,
                                photo = R.drawable.todoimage12
                            )
                        )
                        viewModel.addCategory(
                            Category(
                                title = "Completed",
                                todos = 0,
                                icon = R.drawable.icon_3d_completed,
                                color = 0xFF000000,
                                photo = R.drawable.todoimage13
                            )
                        )
                    }
                }
            }
        }
        composable<HomeScreenN>(
            enterTransition = {
                slideInHorizontally(initialOffsetX = { -it }) + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
            }
        ) {
            HomeScreen(
                onCategoryClicked = { category ->
                    navController.navigate(
                        TodosScreenN(
                            categoryId = category.id,
                            color = category.color,
                            image = category.photo,
                            title = category.title,
                            icon = category.icon
                        )
                    )
                }
            )
        }
        composable<TodosScreenN>(
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }) + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
            }
        ){
            val args = it.toRoute<TodosScreenN>()
            TodosScreen(
                imageRes = args.image,
                title = args.title,
                color = args.color,
                icon = args.icon,
                categoryId = args.categoryId,
                onNavBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    }
}