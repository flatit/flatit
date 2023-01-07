package com.github.flatit.data

import androidx.lifecycle.LiveData
import com.github.flatit.data.model.ShoppingListItem

interface ShoppingListRepository {
    fun getShoppingListItems() : LiveData<List<ShoppingListItem>>
    fun addShoppingListItems(item: ShoppingListItem)
}

class ShoppingListRepositoryImpl(
    private val dataSource: ShoppingListRemoteDataSource
) : ShoppingListRepository {

    override fun getShoppingListItems(): LiveData<List<ShoppingListItem>> {
        return dataSource.getShoppingListItems()
    }

    override fun addShoppingListItems(item: ShoppingListItem) {
        return dataSource.addShoppingListItems(item)
    }
}