package com.github.flatit.ui.shoppinglist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.flatit.data.ShoppingListRepository
import com.github.flatit.data.model.ShoppingListItem
import kotlinx.coroutines.launch

class ShoppingListViewModel(
    private val shoppingListRepository : ShoppingListRepository
) : ViewModel() {

    val items: MutableLiveData<List<ShoppingListItem>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            val shoppingListItems = shoppingListRepository.getShoppingListItems()
            items.value = shoppingListItems
        }
    }
}