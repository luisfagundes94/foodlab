package com.luisfagundes.local.repository

import com.luisfagundes.domain.enums.DarkThemeConfig
import com.luisfagundes.domain.models.UserData
import com.luisfagundes.domain.repositories.UserDataRepository
import com.luisfagundes.local.datasource.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) : UserDataRepository {
    override val userData: Flow<UserData> = preferencesDataSource.userData

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        preferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }
}
