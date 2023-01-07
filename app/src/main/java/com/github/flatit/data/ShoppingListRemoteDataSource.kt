package com.github.flatit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.ShoppingListItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface ShoppingListRemoteDataSource {
    fun getShoppingListItems() : LiveData<List<ShoppingListItem>>
}

class ShoppingListFirebaseDataSource : ShoppingListRemoteDataSource {

    private val db = Firebase.firestore

    override fun getShoppingListItems(): LiveData<List<ShoppingListItem>> {
        val items = MutableLiveData<List<ShoppingListItem>>()

        db.collection("shoppinglist").addSnapshotListener { snapshot, _ ->
            items.value = snapshot?.mapNotNull { i ->
                ShoppingListItem(text = i.getString("text").orEmpty(), checked = i.getBoolean("checked") ?: false, amount = i.getLong("amount") ?: 1)
            }.orEmpty()
        }

        return items
    }
}
