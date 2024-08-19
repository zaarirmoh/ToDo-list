package com.example.todolist

import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ToDoViewModel(
    private val context: Context
): ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    private var showWelcomeScreen by mutableStateOf(false)

    init {
        viewModelScope.launch {
            val preferences = context.dataStore.data.first()
            showWelcomeScreen = preferences[SHOW_WELCOME_SCREEN] ?: false
            _isReady.value = true
        }
    }

    fun getStartingScreen(): String {
        return if (!showWelcomeScreen)
            "GetStartedScreen" else "HomeScreen"
    }

    fun changeStartingScreen() {
        viewModelScope.launch {
            context.dataStore.edit { settings ->
                settings[SHOW_WELCOME_SCREEN] = true
            }
        }

    }

    fun installSplashScreen(activity: MainActivity){
        activity.installSplashScreen().apply {
            setKeepOnScreenCondition {
                !isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }
    }

}