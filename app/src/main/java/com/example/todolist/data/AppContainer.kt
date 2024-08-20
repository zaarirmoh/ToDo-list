package com.example.todolist.data

import android.content.Context

interface AppContainer{
    val itemsRepository: ItemsRepository
    val categorysRepository: CategorysRepository
}

class AppDataContainer(private val context: Context) : AppContainer{

    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(ToDoDatabase.getDatabase(context).itemDao())
    }

    override val categorysRepository: CategorysRepository by lazy {
        OfflineCategorysRepository(ToDoDatabase.getDatabase(context).categoryDao())
    }

}