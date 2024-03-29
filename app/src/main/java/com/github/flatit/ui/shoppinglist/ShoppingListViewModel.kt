package com.github.flatit.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.github.flatit.data.ShoppingListRepository

class ShoppingListViewModel(
    shoppingListRepository: ShoppingListRepository
) : ViewModel() {

    val items = shoppingListRepository.getItems()
}