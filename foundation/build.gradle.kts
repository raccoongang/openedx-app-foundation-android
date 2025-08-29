import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
    `maven-publish`
}

android {
    namespace = "org.openedx.foundation"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.openedx"
            artifactId = "openedx-app-foundation-android"
            version = "1.0.2"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.viewpager2)
    api(libs.androidx.window)
    api(libs.androidx.work.runtime.ktx)

    api(libs.kotlin.stdlib)
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)

    //Compose
    api(libs.androidx.runtime)
    api(libs.androidx.runtime.livedata)
    api(libs.androidx.ui)
    api(libs.androidx.material)
    api(libs.androidx.foundation)
    debugApi(libs.androidx.ui.tooling)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material.icons.extended)
    debugApi(libs.androidx.customview)
    debugApi(libs.androidx.customview.poolingcontainer)

    //Networking
    api(libs.retrofit)
    api(libs.gson)
    api(libs.logging.interceptor)

    // Koin DI
    api(libs.koin)
    api(libs.koin.android)
    api(libs.koin.androidx.compose)

    // Coil
    api(libs.coil.compose)
    api(libs.coil.gif)

    //Android Jetpack
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)

    //Room
    api(libs.androidx.room.runtime)
    api(libs.androidx.room.ktx)

    //Webkit
    api(libs.androidx.webkit)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
    api(libs.kotlinx.coroutines.test)
}

detekt {
    basePath = rootProject.projectDir.absolutePath
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/../config/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml")
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
    reports {
        sarif.required.set(true)
    }
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}
