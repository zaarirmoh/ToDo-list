package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao): ItemsRepository {

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItemByIdStream(id: Int): Flow<Item?> = itemDao.getItemById(id)

    override fun getCategoryItemsStream(categoryId: Int): Flow<List<Item>> = itemDao.getCategoryItems(categoryId)

    override suspend fun updateItem(item: Item) = itemDao.updateItem(item)

    override suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)

    override suspend fun insertItem(item: Item) = itemDao.insertItem(item)

}