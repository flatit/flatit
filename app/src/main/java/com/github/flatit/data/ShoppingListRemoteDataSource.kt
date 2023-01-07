package com.github.flatit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.ShoppingListItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface ShoppingListRemoteDataSource {
    suspend fun getShoppingListItems() : List<ShoppingListItem>
}

class ShoppingListFirebaseDataSource : ShoppingListRemoteDataSource {

    private val fakeData = listOf(
        ShoppingListItem(text = "Tomaten", checked = false, 3),
        ShoppingListItem(text = "Brot", checked = false, 1),
        ShoppingListItem(text = "Nudeln", checked = false, 1),
        ShoppingListItem(text = "Toilettenpapier", checked = false, 1),
    )

    private val db = Firebase.firestore

    override suspend fun getShoppingListItems(): List<ShoppingListItem> {
        val items = mutableListOf<ShoppingListItem>()

        db.collection("shoppinglist").get().addOnSuccessListener { result ->
            for (document in result) {
                items.add(
                    ShoppingListItem(
                        text = document.getString("text").orEmpty(),
                        checked = document.getBoolean("checked") ?: false,
                        amount = document.getLong("amount") ?: 0))
            }
        }

        return fakeData
    }
}
