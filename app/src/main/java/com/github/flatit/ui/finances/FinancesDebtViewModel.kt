package com.github.flatit.ui.finances

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.flatit.data.model.BillingDebtItem

class FinancesDebtViewModel: ViewModel() {
    val fakeData = listOf(
        BillingDebtItem(flatMate = "Johannes", debt = 34.56),
        BillingDebtItem(flatMate = "Moritz", debt = -56.8),
        BillingDebtItem(flatMate = "Justin", debt = 65.56)
    )

    val items: MutableLiveData<List<BillingDebtItem>> = MutableLiveData(fakeData)
}