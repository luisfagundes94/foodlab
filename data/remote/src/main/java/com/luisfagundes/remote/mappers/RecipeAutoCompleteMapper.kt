package com.luisfagundes.remote.mappers

import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.recipe.data.models.RecipeAutoCompleteResponse

object RecipeAutoCompleteMapper {
    fun List<RecipeAutoCompleteResponse>.toDomainModel() =
        this.map { it.toDomain() }

    private fun RecipeAutoCompleteResponse.toDomain() = RecipeAutoComplete(
        id = this.id,
        title = this.title
    )
}
