package com.github.flatit.ui.overview

import androidx.lifecycle.ViewModel
import com.github.flatit.data.FinancesRepository
import com.github.flatit.data.ShoppingListRepository
import com.github.flatit.data.TodosRepository

class OverviewViewModel(
    shoppingListRepository: ShoppingListRepository,
    financesRepository: FinancesRepository,
    todosRepository: TodosRepository
) : ViewModel() {

    val shoppingListItems = shoppingListRepository.getLastNItems(6)
    val debtItems = financesRepository.getDebts()
    val todosItems = todosRepository.getLastNItems(6)
}