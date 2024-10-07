plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
}

android {
    namespace = "org.openedx.foundation"
    compileSdk = 34

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "org.openedx"
            artifactId = "foundation"
            version = "0.1"

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

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}