package com.github.flatit.data.model

import com.google.firebase.Timestamp

data class TodosListItem(
    val id: String,
    val title: String,
    val description: String,
    val checked: Boolean,
    val createdAt: Timestamp
)