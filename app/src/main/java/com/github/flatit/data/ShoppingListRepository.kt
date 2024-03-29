package com.github.flatit.data

import androidx.lifecycle.LiveData
import com.github.flatit.data.model.ShoppingListItem

interface ShoppingListRepository {
    fun getItems(): LiveData<List<ShoppingListItem>>
    fun getLastNItems(n: Long): LiveData<List<ShoppingListItem>>
    fun addItem(item: ShoppingListItem)
    fun updateItem(item: ShoppingListItem)
    fun deleteItem(item: ShoppingListItem)
    fun deleteSelectedItems()
}

class ShoppingListRepositoryImpl(
    private val dataSource: ShoppingListRemoteDataSource
) : ShoppingListRepository {

    override fun getItems(): LiveData<List<ShoppingListItem>> {
        return dataSource.getItems()
    }

    override fun getLastNItems(n: Long): LiveData<List<ShoppingListItem>> {
        return dataSource.getLastNItems(n)
    }

    override fun addItem(item: ShoppingListItem) {
        return dataSource.addItem(item)
    }

    override fun updateItem(item: ShoppingListItem) {
        return dataSource.updateItem(item)
    }

    override fun deleteItem(item: ShoppingListItem) {
        return dataSource.deleteItem(item)
    }

    override fun deleteSelectedItems() {
        return dataSource.deleteSelectedItems()
    }
}