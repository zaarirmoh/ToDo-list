package com.example.todolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todolist.R
import com.example.todolist.ui.screens.mainScreens.homeScreen.HomeScreen
import com.example.todolist.ui.screens.onBoardingScreens.OnBoardingScreen
import kotlin.reflect.KClass

@Composable
fun NavigationSystem(
    navController: NavHostController = rememberNavController(),
    //startDestination: KClass<T>,
){
    val startDestination = OnBoardingScreenN()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<OnBoardingScreenN> {
            val args = it.toRoute<OnBoardingScreenN>()
            OnBoardingScreen(
                id = args.id,
                title = args.title,
                description = args.description,
                onNextClicked = {
                    val nextDestination: Any =
                        if(args.id == R.drawable.onboarding1)
                            OnBoardingScreenN(
                                id = R.drawable.onboarding2,
                                title = "Create daily routine",
                                description = "In ToDo  you can create your personalized routine to stay productive"
                            )
                        else if(args.id == R.drawable.onboarding2)
                            OnBoardingScreenN(
                                id = R.drawable.onboarding3,
                                title = "Organize your tasks",
                                description = "You can organize your daily tasks by adding your tasks into separate categories"
                            )
                        else
                            HomeScreenN
                    navController.navigate(nextDestination)

                }
            )
        }
        composable<HomeScreenN> {
            HomeScreen()
        }
    }
}