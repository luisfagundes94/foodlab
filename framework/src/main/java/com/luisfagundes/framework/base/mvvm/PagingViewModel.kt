package com.luisfagundes.framework.base.mvvm

import androidx.paging.PagingConfig

abstract class PagingViewModel: MvvmViewModel() {
    open fun createPagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE,
        prefetchDistance = PREFETCH_DISTANCE
    )

    private companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 1
    }
}