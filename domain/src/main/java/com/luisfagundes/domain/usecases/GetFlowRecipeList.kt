package com.luisfagundes.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.luisfagundes.framework.usecase.FlowPagingUseCase
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetFlowRecipeList @Inject constructor(
    private val repository: RecipeRepository
) : FlowPagingUseCase<GetFlowRecipeList.Params, Recipe>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val sort: String,
    )

    override fun execute(params: Params): Flow<PagingData<Recipe>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { repository.getFlowRecipeList(params) }
        ).flow
    }
}
