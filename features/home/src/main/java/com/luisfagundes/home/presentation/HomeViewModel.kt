package com.luisfagundes.feature.home.presentation

import com.luisfagundes.domain.usecases.GetFlowRecipeList
import com.luisfagundes.framework.base.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFlowRecipeList: GetFlowRecipeList
): MvvmViewModel() {
}
