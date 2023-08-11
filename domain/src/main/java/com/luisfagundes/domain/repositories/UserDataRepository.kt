package com.luisfagundes.domain.repositories

import com.luisfagundes.domain.enums.DarkThemeConfig
import com.luisfagundes.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}
