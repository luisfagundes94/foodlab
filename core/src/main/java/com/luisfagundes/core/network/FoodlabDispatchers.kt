package com.luisfagundes.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val foodlabDispatchers: FoodlabDispatchers)

enum class FoodlabDispatchers {
    Default,
    IO,
}
