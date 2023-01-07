package com.github.flatit.data

import com.github.flatit.data.model.ShoppingListItem

interface ShoppingListRemoteDataSource {
    suspend fun getShoppingListItems() : List<ShoppingListItem>
}

class ShoppingListCloudFirestoreDataSource : ShoppingListRemoteDataSource {

    val fakeData = listOf(
        ShoppingListItem(text = "Tomaten", checked = false, 3),
        ShoppingListItem(text = "Brot", checked = false, 1),
        ShoppingListItem(text = "Nudeln", checked = false, 1),
        ShoppingListItem(text = "Toilettenpapier", checked = false, 1),
    )

    override suspend fun getShoppingListItems(): List<ShoppingListItem> {
        return fakeData
    }
}
