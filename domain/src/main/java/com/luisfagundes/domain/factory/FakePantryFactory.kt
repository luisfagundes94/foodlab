package com.luisfagundes.domain.factory

import com.luisfagundes.domain.models.PantryCategory
import com.luisfagundes.domain.models.PantryItem

object FakePantryFactory {
    val categories =  listOf(
        PantryCategory(
            id = "1",
            name = "Fruits",
            items = listOf(
                PantryItem(
                    id = "1",
                    name = "Apple",
                    imageUrl = ""
                ),
                PantryItem(
                    id = "2",
                    name = "Banana",
                    imageUrl = ""
                ),
                PantryItem(
                    id = "3",
                    name = "Milk",
                    imageUrl = ""
                ),
                PantryItem(
                    id = "4",
                    name = "Eggs",
                    imageUrl = ""
                ),
            )
        )
    )
}