package com.github.flatit.ui.shoppinglist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.flatit.data.model.ShoppingListItem

class ShoppingListViewModel : ViewModel() {

    val fakeData = listOf(
        ShoppingListItem(text = "Tomaten", checked = false, 3),
        ShoppingListItem(text = "Brot", checked = false, 1),
        ShoppingListItem(text = "Nudeln", checked = false, 1),
        ShoppingListItem(text = "Toilettenpapier", checked = false, 1),
    )

    val items: MutableLiveData<List<ShoppingListItem>> = MutableLiveData(fakeData)
}