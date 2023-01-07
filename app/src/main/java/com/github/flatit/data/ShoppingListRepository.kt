package com.github.flatit.data

import com.github.flatit.data.model.ShoppingListItem

interface ShoppingListRepository {
    suspend fun getShoppingListItems() : List<ShoppingListItem>
}

class ShoppingListRepositoryImpl(
    private val dataSource: ShoppingListRemoteDataSource
) : ShoppingListRepository {

    override suspend fun getShoppingListItems(): List<ShoppingListItem> {
        return dataSource.getShoppingListItems()
    }
}