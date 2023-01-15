package com.github.flatit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.data.model.FinancesExpenseItem
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface FinancesRemoteDataSource {
    fun getExpenseItems() : LiveData<List<FinancesExpenseItem>>
    fun getDebtItems() : LiveData<List<FinancesDebtItem>>
    fun addExpenseItem(item: FinancesExpenseItem)
    fun addDebtItem(item: FinancesDebtItem)
    fun updateExpenseItem(item: FinancesExpenseItem)
    fun updateDebtItem(item: FinancesDebtItem)
    fun deleteExpenseItem(item: FinancesExpenseItem)
    fun deleteDebtItem(item: FinancesDebtItem)
}

class FinancesFirebaseDataSource : FinancesRemoteDataSource {

    private val TAG = "FinancesFirebaseDataSource";
    private val COLLECTION_NAME = "finances";

    private val db = Firebase.firestore

    override fun getExpenseItems(): LiveData<List<FinancesExpenseItem>> {
        val items = MutableLiveData<List<FinancesExpenseItem>>()

        db.collection(COLLECTION_NAME).document("expenses").collection("Johannes").addSnapshotListener { snapshot, _ ->
            items.value = snapshot?.mapNotNull { item ->
                FinancesExpenseItem(
                    id = item.id,
                    title = item.getString("title").orEmpty(),
                    description = item.getString("description").orEmpty(),
                    person = item.getString("person").orEmpty(),
                    expense = item.getDouble("expense") ?: 0.0,
                    timestamp = item.getTimestamp("timestamp") ?: Timestamp.now())
            }.orEmpty()
        }

        return items
    }

    override fun getDebtItems(): LiveData<List<FinancesDebtItem>> {
        val items = MutableLiveData<List<FinancesDebtItem>>()

        db.collection(COLLECTION_NAME).document("debts").collection("Moritz").addSnapshotListener { snapshot, _ ->
            items.value = snapshot?.mapNotNull { item ->
                FinancesDebtItem(
                    id = item.id,
                    flatMate = item.getString("flatmate").orEmpty(),
                    debt = item.getDouble("debt") ?: 0.0)
            }.orEmpty()
        }

        return items
    }

    override fun addExpenseItem(item: FinancesExpenseItem) {
        TODO("Not yet implemented")
    }

    override fun addDebtItem(item: FinancesDebtItem) {
        TODO("Not yet implemented")
    }

    override fun updateExpenseItem(item: FinancesExpenseItem) {
        TODO("Not yet implemented")
    }

    override fun updateDebtItem(item: FinancesDebtItem) {
        TODO("Not yet implemented")
    }

    override fun deleteExpenseItem(item: FinancesExpenseItem) {
        TODO("Not yet implemented")
    }

    override fun deleteDebtItem(item: FinancesDebtItem) {
        TODO("Not yet implemented")
    }
}