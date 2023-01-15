package com.github.flatit.ui.finances

import androidx.lifecycle.ViewModel
import com.github.flatit.data.FinancesRepository

class FinancesDebtViewModel (
    private val financesRepository: FinancesRepository
) : ViewModel() {

    val items = financesRepository.getDebtItems()
}