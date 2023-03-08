package com.github.flatit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.data.model.FinancesExpenseItem
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface FinancesRemoteDataSource {
    fun getExpenseItems() : LiveData<List<FinancesExpenseItem>>
    fun addExpenseItem(item: FinancesExpenseItem)
    fun updateExpenseItem(item: FinancesExpenseItem)
    fun deleteExpenseItem(item: FinancesExpenseItem)

    fun getDebts() : LiveData<List<FinancesDebtItem>>
}

class FinancesFirebaseDataSource : FinancesRemoteDataSource {

    private val TAG = "FinancesFirebaseDataSource";
    private val COLLECTION_NAME = "finances";

    private val db = Firebase.firestore
    private val expenseItems = MutableLiveData<List<FinancesExpenseItem>>()
    private val debtsList = MutableLiveData<List<FinancesDebtItem>>();

    override fun getExpenseItems(): LiveData<List<FinancesExpenseItem>> {

        db.collection(COLLECTION_NAME).addSnapshotListener { snapshot, _ ->
            expenseItems.value = snapshot?.mapNotNull { item ->
                FinancesExpenseItem(
                    id = item.id,
                    title = item.getString("title").orEmpty(),
                    description = item.getString("description").orEmpty(),
                    person = item.getString("person").orEmpty(),
                    expense = item.getDouble("expense") ?: 0.0,
                    timestamp = item.getTimestamp("timestamp") ?: Timestamp.now())
            }.orEmpty()
            calculateDebts()
        }

        return expenseItems
    }

    override fun addExpenseItem(item: FinancesExpenseItem) {
        db.collection(COLLECTION_NAME).document(item.id)
            .set(item)
            .addOnSuccessListener { _ ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${item.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    override fun updateExpenseItem(item: FinancesExpenseItem) {
        db.collection(COLLECTION_NAME).document(item.id).set(item)
    }

    override fun deleteExpenseItem(item: FinancesExpenseItem) {
        db.collection(COLLECTION_NAME).document(item.id).delete()
    }

    override fun getDebts(): LiveData<List<FinancesDebtItem>> {
        getExpenseItems() // load expenses so that debts are recalculated

        return debtsList
    }

    private fun calculateDebts() {
        val debtsFlatMateMap = HashMap<String, Double>()
        val newdebtsList = mutableListOf<FinancesDebtItem>()
        var allExpenses = 0.0

        expenseItems.value?.forEach {
            debtsFlatMateMap[it.person] = it.expense.plus(debtsFlatMateMap[it.person] ?: 0.0)
            allExpenses += it.expense
        }

        val averageShare = allExpenses / debtsFlatMateMap.keys.size

        debtsFlatMateMap.forEach { k, v ->
            newdebtsList.add(FinancesDebtItem(flatMate = k, debt = averageShare - v))
        }

        debtsList.value = newdebtsList
    }

}