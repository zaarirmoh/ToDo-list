package com.example.todolist

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.ui.navigation.NavigationSystem
import com.example.todolist.ui.theme.ToDoListTheme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val SHOW_WELCOME_SCREEN = booleanPreferencesKey("show_welcome_screen")

class MainActivity : ComponentActivity() {

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(context = applicationContext) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    private val viewModel by viewModels<MainViewModel>{ viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.installSplashScreen(this)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {
                val startDestination = viewModel.getStartingScreen()
                viewModel.changeStartingScreen()
                NavigationSystem(
                    startDestination = startDestination
                )
            }
        }
    }
}