package com.github.flatit.data

import androidx.lifecycle.LiveData
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.data.model.FinancesExpenseItem

interface FinancesRepository {
    fun getExpenseItems() : LiveData<List<FinancesExpenseItem>>
    fun getDebtItems() : LiveData<List<FinancesDebtItem>>
    fun addExpenseItem(item: FinancesExpenseItem)
    fun addDebtItem(item: FinancesDebtItem)
    fun updateExpenseItem(item: FinancesExpenseItem)
    fun updateDebtItem(item: FinancesDebtItem)
    fun deleteExpenseItem(item: FinancesExpenseItem)
    fun deleteDebtItem(item: FinancesDebtItem)
}

class FinancesRepositoryImpl (
    private val dataSource: FinancesRemoteDataSource
    ): FinancesRepository {
    override fun getExpenseItems(): LiveData<List<FinancesExpenseItem>> {
        return dataSource.getExpenseItems()
    }

    override fun getDebtItems(): LiveData<List<FinancesDebtItem>> {
        return dataSource.getDebtItems()
    }

    override fun addExpenseItem(item: FinancesExpenseItem) {
        dataSource.addExpenseItem(item)
    }

    override fun addDebtItem(item: FinancesDebtItem) {
        dataSource.addDebtItem(item)
    }

    override fun updateExpenseItem(item: FinancesExpenseItem) {
        dataSource.updateExpenseItem(item)
    }

    override fun updateDebtItem(item: FinancesDebtItem) {
        dataSource.updateDebtItem(item)
    }

    override fun deleteExpenseItem(item: FinancesExpenseItem) {
        dataSource.deleteExpenseItem(item)
    }

    override fun deleteDebtItem(item: FinancesDebtItem) {
        dataSource.deleteDebtItem(item)
    }
}