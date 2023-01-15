package com.github.flatit.data.model

import com.google.firebase.Timestamp

data class FinancesExpenseItem(
    val id: String,
    val title: String,
    val description: String,
    val person: String,
    val expense: Double,
    val timestamp: Timestamp
)
