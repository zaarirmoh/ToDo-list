package com.example.todolist.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolist.ToDoApplication
import com.example.todolist.ui.screens.mainScreens.homeScreen.HomeViewModel
import com.example.todolist.ui.screens.mainScreens.listDetailsScreen.ToDosViewModel

object AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(todoApplication().container.categorysRepository)
        }
        initializer {
            ToDosViewModel(todoApplication().container.itemsRepository)
        }
    }
}

fun CreationExtras.todoApplication(): ToDoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ToDoApplication)