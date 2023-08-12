@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.luisfagundes.foodlab.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luisfagundes.foodlab"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            pickFirsts += "META-INF/LICENSE.md"
            pickFirsts += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    // Modules
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.data.remote)
    implementation(projects.data.local)
    implementation(projects.commons.resources)
    implementation(projects.commons.components)
    implementation(projects.framework)
    implementation(projects.features.home)
    implementation(projects.features.search)
    implementation(projects.features.saved)
    implementation(projects.features.pantry)

    // Activity
    implementation(libs.activity)

    // Compose
    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.navigation)
    implementation(libs.compose.runtime.tracing)
    implementation(libs.compose.material3.windowSizeClass)
    implementation(libs.compose.material.extended.icons)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)

    // Lifecycle
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    // Dependency Injection
    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    // Other
    implementation(libs.androidx.tracing.ktx)
}
