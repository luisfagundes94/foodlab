import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.ksp)
}

val apiKeyPropertiesFile: File = rootProject.file("apikey.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

fun getApiKey() = apiKeyProperties["API_KEY"]

android {
    namespace = "com.luisfagundes.data"
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
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

androidComponents.beforeVariants { variant ->
    android.sourceSets.maybeCreate(variant.name).apply {
        java.srcDir(buildDir.resolve("generated/source/proto/${variant.name}/java"))
        kotlin.srcDir(buildDir.resolve("generated/source/proto/${variant.name}/kotlin"))
    }
}


dependencies {
    // Core
    implementation(libs.core.ktx)

    // Modules
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.framework)

    // Storage
    implementation(libs.datastore)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.room)
    ksp(libs.room.compiler)
    implementation(libs.gson)


    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttp.mockwebserver)

    // Dependency Injection
    implementation(libs.hilt.library)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.coroutines.android)

    // Paging
    implementation(libs.paging.runtime)

    // Testing
    // Testing
    implementation(projects.commons.testing)
}