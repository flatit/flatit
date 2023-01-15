package com.github.flatit.ui.finances

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.flatit.data.FinancesRepository
import com.github.flatit.data.model.FinancesExpenseItem
import com.google.firebase.Timestamp
import java.time.LocalDateTime

class FinancesExpenseViewModel (
    private val financesRepository: FinancesRepository
) : ViewModel() {

    val items = financesRepository.getExpenseItems()
}