plugins {
    id("com.android.application")
//    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.30"
    kotlin("android")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)

    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.liveData)

    // Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.converterGson)
    implementation(Dependencies.Retrofit.adapterRxJava)
    implementation(Dependencies.Retrofit.okHttp)
    implementation(Dependencies.Retrofit.loggingInterceptor)

    // RxJava
    implementation(Dependencies.RxJava.rxAndroid)
    implementation(Dependencies.RxJava.rxJava)

    // Navigation
    implementation(Dependencies.Navigation.fragment)
    implementation(Dependencies.Navigation.ui)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)

    implementation(Dependencies.Utils.viewBindingDelegate)

    implementation(Dependencies.Image.glide)
    annotationProcessor(Dependencies.Image.glideCompiler)
}