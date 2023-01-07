package com.github.flatit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.ShoppingListItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface ShoppingListRemoteDataSource {
    fun getShoppingListItems() : LiveData<List<ShoppingListItem>>
    fun addShoppingListItems(item: ShoppingListItem)
}

class ShoppingListFirebaseDataSource : ShoppingListRemoteDataSource {

    private val TAG = "FirebaseDataSource";
    private val COLLECTION_NAME = "shoppinglist";

    private val db = Firebase.firestore

    override fun getShoppingListItems(): LiveData<List<ShoppingListItem>> {
        val items = MutableLiveData<List<ShoppingListItem>>()

        db.collection(COLLECTION_NAME).addSnapshotListener { snapshot, _ ->
            items.value = snapshot?.mapNotNull { i ->
                ShoppingListItem(text = i.getString("text").orEmpty(), checked = i.getBoolean("checked") ?: false, amount = i.getLong("amount") ?: 1)
            }.orEmpty()
        }

        return items
    }

    override fun addShoppingListItems(item: ShoppingListItem) {
        db.collection(COLLECTION_NAME)
            .add(item)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}
