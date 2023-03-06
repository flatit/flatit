package com.github.flatit.ui.finances

import androidx.lifecycle.ViewModel
import com.github.flatit.data.FinancesRepository

class FinancesViewModel (
    private val financesRepository: FinancesRepository
) : ViewModel() {

    val items = financesRepository.getExpenseItems()

    val debts = financesRepository.getDebts()
}