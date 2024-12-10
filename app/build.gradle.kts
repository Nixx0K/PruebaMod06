plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.ambientesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ambientesapp"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create ("qa") {
            keyAlias = "qaKeyAlias"
            keyPassword = "admin123"
            storeFile = file("firmas/qaKeyAlias.jks")
            storePassword = "admin123"
        }
        create ("prod") {
            keyAlias = "prodKeyAlias"
            keyPassword = "admin123"
            storeFile = file("firmas/keys.jks")
            storePassword = "admin123"
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("prod")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("qa")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".dev"
            buildConfigField("String", "APP_ENV", "\"dev\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        buildConfig = true
    }
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "APP_ENV", "\"dev\"")
            resValue("color", "main_background", "#FFFFFF")
        }
        create("qa") {
            dimension = "environment"
            buildConfigField ("String", "APP_ENV", "\"qa\"")
            resValue ("color", "main_background", "#FFA500")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField ("String", "APP_ENV", "\"prod\"")
            resValue ("color", "main_background", "#000000")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.5.10")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.support.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.firebase:firebase-crashlytics-ktx:19.3.0")
    implementation("com.google.firebase:firebase-analytics-ktx:22.1.2")
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
}