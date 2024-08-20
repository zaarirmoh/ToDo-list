package com.example.todolist.ui.screens.mainScreens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Category
import com.example.todolist.data.CategorysRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val categoryList: List<Category> = listOf()
)
class HomeViewModel(
    private val categorysRepository: CategorysRepository
): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        categorysRepository.getAllCategorysStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun addCategory(category: Category){
        viewModelScope.launch {
            categorysRepository.insertCategory(category)
        }
    }

    fun deleteCategory(category: Category){
        viewModelScope.launch {
            categorysRepository.deleteCategory(category)
        }
    }

    fun updateCategory(category: Category){
        viewModelScope.launch {
            categorysRepository.updateCategory(category)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}