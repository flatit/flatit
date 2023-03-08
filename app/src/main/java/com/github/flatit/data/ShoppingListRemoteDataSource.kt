package com.github.flatit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.ShoppingListItem
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface ShoppingListRemoteDataSource {
    fun getItems(): LiveData<List<ShoppingListItem>>
    fun getLastNItems(n: Long): LiveData<List<ShoppingListItem>>
    fun addItem(item: ShoppingListItem)
    fun updateItem(item: ShoppingListItem)
    fun deleteItem(item: ShoppingListItem)
    fun deleteSelectedItems()
}

class ShoppingListFirebaseDataSource : ShoppingListRemoteDataSource {

    private val TAG = "ShoppingListFirebaseDataSource";
    private val COLLECTION_NAME = "shoppinglist";

    private val db = Firebase.firestore

    override fun getItems(): LiveData<List<ShoppingListItem>> {
        val items = MutableLiveData<List<ShoppingListItem>>()

        db.collection(COLLECTION_NAME).orderBy("checked").addSnapshotListener { snapshot, _ ->
            items.value = snapshot?.mapNotNull { item ->
                ShoppingListItem(
                    id = item.id,
                    text = item.getString("text").orEmpty(),
                    checked = item.getBoolean("checked") ?: false,
                    amount = item.getLong("amount") ?: 1,
                    createdAt = item.getTimestamp("createdAt") ?: Timestamp.now()
                )
            }.orEmpty()
        }

        return items
    }

    override fun getLastNItems(n: Long): LiveData<List<ShoppingListItem>> {
        val items = MutableLiveData<List<ShoppingListItem>>()

        db.collection(COLLECTION_NAME).orderBy("createdAt", Query.Direction.DESCENDING).limit(n)
            .addSnapshotListener { snapshot, _ ->
                items.value = snapshot?.mapNotNull { item ->
                    ShoppingListItem(
                        id = item.id,
                        text = item.getString("text").orEmpty(),
                        checked = item.getBoolean("checked") ?: false,
                        amount = item.getLong("amount") ?: 1,
                        createdAt = item.getTimestamp("createdAt") ?: Timestamp.now()
                    )
                }.orEmpty()
            }

        return items
    }

    override fun addItem(item: ShoppingListItem) {
        db.collection(COLLECTION_NAME).document(item.id)
            .set(item)
            .addOnSuccessListener { _ ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${item.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    override fun updateItem(item: ShoppingListItem) {
        db.collection(COLLECTION_NAME).document(item.id).set(item)
    }

    override fun deleteItem(item: ShoppingListItem) {
        db.collection(COLLECTION_NAME).document(item.id).delete()
    }

    override fun deleteSelectedItems() {
        db.collection(COLLECTION_NAME).whereEqualTo("checked", true).get()
            .addOnSuccessListener { items ->
                items.forEach {
                    it.reference.delete()
                }
            }
    }

}
