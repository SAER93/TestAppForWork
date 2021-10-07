object Dependencies {

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
    }

    object Hilt {
        const val version = "2.38.1"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

//    object Room {
//        private const val version = "2.3.0"
//        const val ktx = "androidx.room:room-ktx:$version"
//        const val runtime = "androidx.room:room-runtime:$version"
//        const val paging = "androidx.room:room-paging:2.4.0-alpha04"
//        const val compiler = "androidx.room:room-compiler:$version"
//    }

    object Navigation {
        const val version = "2.4.0-alpha10"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val material = "com.google.android.material:material:1.4.0"
    }

    object Lifecycle {
        const val version = "2.4.0-alpha01"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
    }

    object Firebase {
        const val version = "20.0.0"
        const val auth = "com.google.firebase:firebase-auth:$version"
        const val database = "com.google.firebase:firebase-database-ktx:$version"
        const val storage = "com.google.firebase:firebase-storage-ktx:$version"
        const val ui = "com.firebaseui:firebase-ui-database:8.0.0"
    }

    object Retrofit {
        const val retrofit_version = "2.9.0"
        const val okHttp_version = "4.8.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
        const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:$retrofit_version"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttp_version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttp_version"
    }

    object RxJava {
        const val version = "2.1.1"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$version"
        const val rxJava = "io.reactivex.rxjava2:rxjava:$version"
    }

    object Image {
        const val cropper = "com.github.CanHub:Android-Image-Cropper:3.2.2"
        const val circleImage = "de.hdodenhof:circleimageview:3.1.0"

        const val versionGlide = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$versionGlide"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$versionGlide"
    }

    object Utils {
        const val viewBindingDelegate = "com.github.kirich1409:viewbindingpropertydelegate:1.4.7"
    }

    object Test {
        const val jUnit = "junit:junit:4.+"
        const val androidJUnit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
    }
}