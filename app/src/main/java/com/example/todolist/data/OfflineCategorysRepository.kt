package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class OfflineCategorysRepository(private val categoryDao: CategoryDao): CategorysRepository {

    override fun getAllCategorysStream(): Flow<List<Category>> = categoryDao.getAllCategorys()

    override suspend fun insertCategory(category: Category) = categoryDao.insertCategory(category)

    override suspend fun updateCategory(category: Category) = categoryDao.updateCategory(category)

    override suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)

}