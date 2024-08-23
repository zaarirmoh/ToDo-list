package com.example.todolist.ui.screens.mainScreens.listDetailsScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Item
import com.example.todolist.data.ItemsRepository
import com.example.todolist.ui.screens.mainScreens.homeScreen.HomeViewModel
import com.example.todolist.ui.screens.mainScreens.homeScreen.HomeViewModel.Companion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ToDosUiState(
    val todosList: List<Item> = listOf()
)
class ToDosViewModel (
    private val itemsRepository: ItemsRepository,
): ViewModel() {


    fun getCategoryItems(categoryId: Int): StateFlow<ToDosUiState> {
        return itemsRepository.getCategoryItemsStream(categoryId)
            .map { ToDosUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ToDosUiState()
            )
    }

    fun addTodo(item: Item){
        viewModelScope.launch {
            itemsRepository.insertItem(item)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}