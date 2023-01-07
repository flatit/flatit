package com.github.flatit.data

import androidx.lifecycle.LiveData
import com.github.flatit.data.model.ShoppingListItem

interface ShoppingListRepository {
    fun getShoppingListItems() : LiveData<List<ShoppingListItem>>
}

class ShoppingListRepositoryImpl(
    private val dataSource: ShoppingListRemoteDataSource
) : ShoppingListRepository {

    override fun getShoppingListItems(): LiveData<List<ShoppingListItem>> {
        return dataSource.getShoppingListItems()
    }
}