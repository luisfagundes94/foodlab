package com.luisfagundes.domain.models

data class PantryCategory(
    val id: String,
    val name: String,
    val items: List<PantryItem>,
)
