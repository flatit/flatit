package com.github.flatit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.Flatmate
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface FlatmateRemoteDataSource {
    fun getFlatmates() : LiveData<List<Flatmate>>
    fun addFlatmate(item: Flatmate)
    fun updateFlatmate(item: Flatmate)
    fun deleteFlatmate(item: Flatmate)
}

class FlatmateFirebaseDataSource : FlatmateRemoteDataSource {
    private val TAG = "FlatmateFirebaseDataSource";
    private val COLLECTION_NAME = "flatmates";

    private val db = Firebase.firestore

    override fun getFlatmates(): LiveData<List<Flatmate>> {
        val items = MutableLiveData<List<Flatmate>>()

        db.collection(COLLECTION_NAME).addSnapshotListener { snapshot, _ ->
            items.value = snapshot?.mapNotNull { item ->
                Flatmate(
                    id = item.id,
                    name = item.getString("name").orEmpty())
            }.orEmpty()
        }

        return items
    }

    override fun addFlatmate(item: Flatmate) {
        db.collection(COLLECTION_NAME).document(item.id)
            .set(item)
            .addOnSuccessListener { _ ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${item.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
        }
    }

    override fun updateFlatmate(item: Flatmate) {
        db.collection(COLLECTION_NAME).document(item.id).set(item)
    }

    override fun deleteFlatmate(item: Flatmate) {
        db.collection(COLLECTION_NAME).document(item.id).delete()
    }
}