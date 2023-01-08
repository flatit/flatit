package com.github.flatit.ui.billing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.flatit.data.model.BillingDeptItem

class BillingDebtViewModel: ViewModel() {
    val fakeData = listOf(
        BillingDeptItem(flatMate = "Johannes", dept = 34.56),
        BillingDeptItem(flatMate = "Moritz", dept = -56.8),
        BillingDeptItem(flatMate = "Justin", dept = 65.56)
    )

    val items: MutableLiveData<List<BillingDeptItem>> = MutableLiveData(fakeData)
}