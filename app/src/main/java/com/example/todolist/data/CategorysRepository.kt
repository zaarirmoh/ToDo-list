package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

interface CategorysRepository {

    fun getAllCategorysStream(): Flow<List<Category>>

    suspend fun insertCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

}