package com.luisfagundes.domain.models

import javax.annotation.concurrent.Immutable

@Immutable
data class PantryItem(
    val id: String,
    val name: String,
    val imageUrl: String,
)
