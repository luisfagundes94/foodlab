package com.luisfagundes.framework.base.app

import com.luisfagundes.library.framework.base.app.AppInitializer
import com.luisfagundes.library.framework.base.app.CoreApplication
import com.luisfagundes.library.framework.base.app.FirebaseCrashlyticsReportTree
import timber.log.Timber

class TimberInitializer(private val isDev: Boolean) : AppInitializer {
    override fun init(application: CoreApplication) {
        if (isDev) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(FirebaseCrashlyticsReportTree())
        }
    }
}
