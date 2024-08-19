package com.example.todolist.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationSystem(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = "ScreenA"
    ) {
        composable("ScreenA") {
            Scaffold {
                Column(
                    modifier = modifier.padding(it).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Screen A")
                    }
                }
            }
        }
    }
}