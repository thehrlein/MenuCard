/**
 * Created by tobias.hehrlein on 2019-07-16.
 */
object Dependencies {

    object Versions {

        const val adapterDelegateVersion = "4.0.0"
        const val androidxCoreVersion = "1.2.0-alpha02"
        const val androidxAppCompatVersion = "1.1.0-beta01"
        const val aviLoadingIndicatorVersion = "2.1.3"
        const val circularImageViewVersion = "3.0.0"
        const val constraintLayoutVersion = "1.1.3"
        const val daggerVersion = "2.21"
        const val expandableLayoutVersion = "2.9.2"
        const val fabVersion = "1.6.4"
        const val firebaseAuthVersion = "17.0.0"
        const val firebaseFirestoreVersion = "19.0.2"
        const val frescoVersion = "0.13.0"
        const val glideVersion = "4.9.0"
        const val gsonVersion = "2.8.5"
        const val imageViewerVersion = "0.5.0"
        const val kotlinVersion = "1.3.41"
        const val konfettiVersion = "1.1.3"
        const val lifecycleVersion = "2.0.0"
        const val materialVersion = "1.1.0-alpha07"
        const val okHttpClientVersion = "3.12.1"
        const val recyclerViewVersion = "1.1.0-alpha06"
        const val retrofitVersion = "2.5.0"
        const val rxBinding2Version = "2.2.0"
        const val rxBindingKotlinVersion = "0.4.0"
        const val rxJavaVersion = "2.1.13"
        const val rxKotlinVersion = "2.2.0"
        const val stepperTouchVersion = "1.0.1"
        const val timberVersion = "4.7.1"
    }

    // Basics
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompatVersion}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCoreVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"

    // RxJava & RxBinding
    const val rxbinding2 = "com.jakewharton.rxbinding2:rxbinding:${Versions.rxBinding2Version}"
    const val rxbindingKotlin = "com.jakewharton.rxbinding:rxbinding-kotlin:${Versions.rxBindingKotlinVersion}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"

    // Dagger2
    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"

    // ViewModel
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"

    // Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

    // OkHttpClient
    const val okHttpClient = "com.squareup.okhttp3:okhttp:${Versions.okHttpClientVersion}"
    const val okHttpClientInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpClientVersion}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitConvertGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofitAdapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"

    // RecyclerView
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"

    // Adapter Delegate
    const val adapterDelegate = "com.hannesdorfmann:adapterdelegates4:${Versions.adapterDelegateVersion}"

    // Glide & ImageViewer & Fresco
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val fresco = "com.facebook.fresco:fresco:${Versions.frescoVersion}"
    const val frescoImageViewer = "com.github.stfalcon:frescoimageviewer:${Versions.imageViewerVersion}"
    const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circularImageViewVersion}"

    // Loading Animation Indicator
    const val aviLoadingIndicator = "com.wang.avi:library:${Versions.aviLoadingIndicatorVersion}"

    // Floating Action Button - Menu
    const val floatingActionButton = "com.github.clans:fab:${Versions.fabVersion}"

    // Firebase
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuthVersion}"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore:${Versions.firebaseFirestoreVersion}"

    // Stepper Touch
    const val stepperTouch = "com.github.DanielMartinus:Stepper-Touch:${Versions.stepperTouchVersion}"

    // Konfetti
    const val konfetti = "nl.dionsegijn:konfetti:${Versions.konfettiVersion}"

    // Expandable Layout
    const val expandableLayout = "net.cachapa.expandablelayout:expandablelayout:${Versions.expandableLayoutVersion}"


}

object TestLibraries {

    object Versions {

        const val espressoVersion = "3.1.1"
        const val junitVersion = "4.12"
        const val testRunnerVersion = "1.1.1"
    }

    // Testing
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val expressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}

object AndroidSdkTools {

    const val minSdk = 24
    const val targetSdk = 28
    const val compileSdk = 28
    const val version_code = 1
    const val version_name = "1.0"
    const val application_id = "com.tobiapplications.menu"
}

object BuildPlugins {

    object Versions {

        const val buildToolsVersion = "3.4.2"
        const val googleServices = "4.2.0"
    }

    const val androidGradlePluginClassPath = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePluginClassPath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Versions.kotlinVersion}"
    const val googleServicesClassPath = "com.google.gms:google-services:${Versions.googleServices}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val googleServices = "com.google.gms.google-services"
}