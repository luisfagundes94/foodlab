import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

val apiKeyPropertiesFile: File = rootProject.file("apikey.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

fun getApiKey() = apiKeyProperties["API_KEY"]

android {
    namespace = "com.luisfagundes.data.remote"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        buildConfigField("String", "API_KEY", "\"${getApiKey()}\"")
        buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        buildConfig = true
    }
}

dependencies {
    // Modules
    implementation(projects.domain)
    implementation(projects.framework)
    //implementation(projects.common.resources)

    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    // Paging
    implementation(libs.paging.common)

    // DI
    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttp.mockwebserver)
}
