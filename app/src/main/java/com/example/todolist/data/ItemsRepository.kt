package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getAllItemsStream(): Flow<List<Item>>

    fun getItemByIdStream(id: Int): Flow<Item?>

    fun getCategoryItemsStream(categoryId: Int): Flow<List<Item>>

    suspend fun updateItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun insertItem(item: Item)

}