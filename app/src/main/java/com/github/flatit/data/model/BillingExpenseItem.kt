package com.github.flatit.data.model

import java.time.LocalDateTime

data class BillingExpenseItem(
    val title: String,
    val description: String,
    val person: String,
    val expense: Double,
    val timestamp: LocalDateTime
)
