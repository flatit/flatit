package com.github.flatit.data.model

import com.google.firebase.Timestamp

data class ShoppingListItem(
    val id: String,
    val text: String,
    val checked: Boolean,
    val amount: Long,
    val createdAt: Timestamp
)