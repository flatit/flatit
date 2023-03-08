package com.github.flatit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.TodosListItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface TodosRemoteDataSource {
    fun getItems(): LiveData<List<TodosListItem>>
    fun getLastNItems(n: Long): LiveData<List<TodosListItem>>
    fun addItem(item: TodosListItem)
    fun updateItem(item: TodosListItem)
    fun deleteItem(item: TodosListItem)
}

class TodosFirebaseDataSource : TodosRemoteDataSource {

    private val TAG = "TodosFirebaseDataSource";
    private val COLLECTION_NAME = "todos";

    private val db = Firebase.firestore

    override fun getItems(): LiveData<List<TodosListItem>> {
        val items = MutableLiveData<List<TodosListItem>>()

        db.collection(COLLECTION_NAME).orderBy("checked").addSnapshotListener { snapshot, _ ->
            items.value = snapshot?.mapNotNull { item ->
                TodosListItem(
                    id = item.id,
                    title = item.getString("title").orEmpty(),
                    description = item.getString("description").orEmpty(),
                    checked = item.getBoolean("checked") ?: false
                )
            }.orEmpty()
        }

        return items
    }

    override fun getLastNItems(n: Long): LiveData<List<TodosListItem>> {
        val items = MutableLiveData<List<TodosListItem>>()

        db.collection(COLLECTION_NAME).orderBy("title").limit(n)
            .addSnapshotListener { snapshot, _ ->
                items.value = snapshot?.mapNotNull { item ->
                    TodosListItem(
                        id = item.id,
                        title = item.getString("title").orEmpty(),
                        description = item.getString("description").orEmpty(),
                        checked = item.getBoolean("checked") ?: false
                    )
                }.orEmpty()
            }

        return items
    }

    override fun addItem(item: TodosListItem) {
        db.collection(COLLECTION_NAME).document(item.id)
            .set(item)
            .addOnSuccessListener { _ ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${item.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    override fun updateItem(item: TodosListItem) {
        db.collection(COLLECTION_NAME).document(item.id).set(item)
    }

    override fun deleteItem(item: TodosListItem) {
        db.collection(COLLECTION_NAME).document(item.id).delete()
    }
}
