package com.github.flatit.ui.finances

import androidx.lifecycle.ViewModel
import com.github.flatit.data.FlatmateRepository

class AddExpenseDialogViewModel (
    private val flatmateRepository: FlatmateRepository
) : ViewModel() {

    val items = flatmateRepository.getFlatmates()
}