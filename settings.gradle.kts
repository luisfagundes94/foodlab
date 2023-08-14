pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "foodlab"
include(":app")
include(":core")
include(":domain")
include(":data:local")
include(":data:remote")
include(":framework")
include(":features:home")
include(":features:search")
include(":features:saved")
include(":features:pantry")
include(":commons:resources")
include(":commons:components")
include(":features:recipes")
