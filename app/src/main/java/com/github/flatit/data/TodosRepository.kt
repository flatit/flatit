package com.github.flatit.data

import androidx.lifecycle.LiveData
import com.github.flatit.data.model.TodosListItem

interface TodosRepository {
    fun getItems(): LiveData<List<TodosListItem>>
    fun getLastNItems(n: Long): LiveData<List<TodosListItem>>
    fun addItem(item: TodosListItem)
    fun updateItem(item: TodosListItem)
    fun deleteItem(item: TodosListItem)
}

class TodosRepositoryImpl(
    private val dataSource: TodosRemoteDataSource
) : TodosRepository {
    override fun getItems(): LiveData<List<TodosListItem>> {
        return dataSource.getItems()
    }

    override fun getLastNItems(n: Long): LiveData<List<TodosListItem>> {
        return dataSource.getLastNItems(n)
    }

    override fun addItem(item: TodosListItem) {
        dataSource.addItem(item)
    }

    override fun updateItem(item: TodosListItem) {
        dataSource.updateItem(item)
    }

    override fun deleteItem(item: TodosListItem) {
        dataSource.deleteItem(item)
    }

}
