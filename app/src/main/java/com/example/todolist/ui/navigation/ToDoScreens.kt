package com.example.todolist.ui.navigation

import com.example.todolist.R
import kotlinx.serialization.Serializable

@Serializable
data class OnBoardingScreenN(
    val id: Int = R.drawable.onboarding1,
    val title: String = "Manage your tasks",
    val description: String = "You can easily manage all of your daily tasks in ToDo for free",
)

@Serializable
object HomeScreenN