# Foodlab
An Android application for browsing recipes.

Features:
- Search recipes by ingredients
- Save recipes for later
- Search recipes by meal
- Watch recipe preparation videos

## Architecture
Architecture that is meticulously planned is paramount for an application's scalability, and all architectural designs share a universal aim - to handle the complexity of your application. This might not be a concern for smaller-scale apps, but it can be incredibly advantageous when working on apps with an extended development timeline and a bigger team.

Robert C. Martin introduced the concept of Clean Architecture in 2012 via the Clean Code Blog, and it abides by the SOLID principle.

<img src="https://miro.medium.com/v2/resize:fit:772/1*wOmAHDN_zKZJns9YDjtrMw.jpeg" width="500" />

This app uses [_**MVVM (Model-View-ViewModel)**_]([https://proandroiddev.com/mvi-architecture-with-kotlin-flows-and-channels-d36820b2028d](https://developer.android.com/topic/architecture)) architecture design.
 
<img src="https://github.com/MuhammadKhoshnaw/BasicMVIApp/blob/master/.github/res/ComponentDiagram.svg" width=500 />

# Tech Stacks
This project uses many of the popular libraries, plugins and tools of the android ecosystem.

### Compose
- [Material Design 3](https://developer.android.com/jetpack/androidx/releases/compose-material3) - Build Jetpack Compose UIs with ready to use Material Design Components.
- [Foundation](https://developer.android.com/jetpack/androidx/releases/compose-foundation) - Write Jetpack Compose applications with ready to use building blocks and extend foundation to build your own design system pieces.
- [UI](https://developer.android.com/jetpack/androidx/releases/compose-ui) - Fundamental components of compose UI needed to interact with the device, including layout, drawing, and input.
- [Lifecycle-ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
- [HiltViewModel](https://dagger.dev/hilt/view-model.html) - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [Lottie](https://github.com/airbnb/lottie/blob/master/android-compose.md) - Lottie is a mobile library for Android and iOS that parses Adobe After Effects animations exported as json with Bodymovin and renders them natively on mobile!

### Accompanist
- [SwipeRefresh](https://google.github.io/accompanist/swiperefresh/) - A library which provides a layout which provides the swipe-to-refresh UX pattern, similar to Android's SwipeRefreshLayout.
- [Navigation](https://google.github.io/accompanist/navigation-material/) - A library which provides Compose Material support for Jetpack Navigation Compose. This features composable bottom sheet destinations.

### Jetpack
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
- [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
- [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
- [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite used for offline data caching.

### Dependency Injection
- [Dagger Hilt](https://dagger.dev/hilt/) - Dependency Injection library.

### Network
- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client and supports coroutines out of the box.
- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.

### Core
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.

### Logging
- [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API which provides utility on top of Android's normal Log class.

### Testing
- [JUnit4](https://junit.org/junit4/) - JUnit is a simple framework to write repeatable tests.
- [Mockk](https://mockk.io/) - A modern Mockk library for UnitTest.
- [Coroutine-Test](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test) - Provides testing utilities for effectively testing coroutines.

### Plugin
- [GradleVersionCatalog](https://docs.gradle.org/current/userguide/platforms.html) - Gradle's support for declaring and using dependencies.
- [Kapt](https://kotlinlang.org/docs/kapt.html) - Kapt compiler plugin
