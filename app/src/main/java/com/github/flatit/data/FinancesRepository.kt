package com.github.flatit.data

import androidx.lifecycle.LiveData
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.data.model.FinancesExpenseItem

interface FinancesRepository {
    fun getExpenseItems() : LiveData<List<FinancesExpenseItem>>
    fun addExpenseItem(item: FinancesExpenseItem)
    fun updateExpenseItem(item: FinancesExpenseItem)
    fun deleteExpenseItem(item: FinancesExpenseItem)

    fun getDebts() : LiveData<List<FinancesDebtItem>>
}

class FinancesRepositoryImpl (
    private val dataSource: FinancesRemoteDataSource
    ): FinancesRepository {
    override fun getExpenseItems(): LiveData<List<FinancesExpenseItem>> {
        return dataSource.getExpenseItems()
    }

    override fun addExpenseItem(item: FinancesExpenseItem) {
        dataSource.addExpenseItem(item)
    }

    override fun updateExpenseItem(item: FinancesExpenseItem) {
        dataSource.updateExpenseItem(item)
    }

    override fun deleteExpenseItem(item: FinancesExpenseItem) {
        dataSource.deleteExpenseItem(item)
    }

    override fun getDebts(): LiveData<List<FinancesDebtItem>> {
        return dataSource.getDebts()
    }
}