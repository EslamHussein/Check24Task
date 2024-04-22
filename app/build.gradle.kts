plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.kotlin.parcelize)


}

android {
    namespace = "com.elektrobit.check24task"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.elektrobit.check24task"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
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
        }
    }
}

dependencies {

    implementation(libs.bundles.core)
    implementation(libs.bundles.ui)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.retrofit)

    testImplementation(libs.bundles.testing)
    testImplementation(libs.bundles.koin.testing)
    testImplementation(libs.bundles.compose.testing)

    androidTestImplementation(libs.bundles.android.testing)

    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.room)
    implementation(libs.room.paging)
    annotationProcessor(libs.roomprocessor)
    ksp(libs.roomprocessor)

    debugImplementation(libs.bundles.ui.debug)

}
