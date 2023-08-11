package com.luisfagundes.domain.models

import com.luisfagundes.domain.enums.DarkThemeConfig

data class UserData(
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
)
