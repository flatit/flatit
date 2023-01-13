package com.github.flatit.ui.finances

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.flatit.data.model.BillingExpenseItem
import java.time.LocalDateTime

class FinancesExpenseViewModel: ViewModel() {
    val fakeData = listOf(
        BillingExpenseItem(title = "Einkauf Konsum", description = "viel zu teuer", person = "Moritz", expense = 34.78, timestamp = LocalDateTime.now()),
        BillingExpenseItem(title = "Einkauf Obi", description = "Bohrer, Schrauben und Klebeband", person = "Johannes", expense = 23.67, timestamp = LocalDateTime.now()),
        BillingExpenseItem(title = "Bar", description = "Ich trinke nie wieder alkohol", person = "Justin", expense = 12.4, timestamp = LocalDateTime.now()),
        BillingExpenseItem(title = "Moritz gönnt abendbrot", description = "Moritz stinkt", person = "Moritz", expense = 96.8, timestamp = LocalDateTime.now())
        )

    val items: MutableLiveData<List<BillingExpenseItem>> = MutableLiveData(fakeData)
}