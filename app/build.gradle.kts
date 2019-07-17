plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.googleServices)
}

android {
    compileSdkVersion(AndroidSdkTools.compileSdk)
    defaultConfig {
        applicationId = AndroidSdkTools.application_id
        minSdkVersion(AndroidSdkTools.minSdk)
        targetSdkVersion(AndroidSdkTools.targetSdk)
        versionCode = AndroidSdkTools.version_code
        versionName = AndroidSdkTools.version_name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        setSourceCompatibility(1.8)
        setTargetCompatibility(1.8)
    }
}

dependencies {

    // Basics
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.androidxAppCompat)
    implementation(Dependencies.androidxCore)
    implementation(Dependencies.constraintlayout)
    implementation(Dependencies.material)

    // RxJava & RxBinding
    implementation(Dependencies.rxbinding2)
    implementation(Dependencies.rxbindingKotlin)
    implementation(Dependencies.rxJava)
    implementation(Dependencies.rxKotlin)

    // Dagger2
    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerSupport)
    kapt(Dependencies.daggerProcessor)
    kapt(Dependencies.daggerCompiler)

    // ViewModel
    implementation(Dependencies.lifecycleExtensions)
    kapt(Dependencies.lifecycleCompiler)

    // Logging
    implementation(Dependencies.timber)

    // Gson
    implementation(Dependencies.gson)

    // OkHttpClient
    implementation(Dependencies.okHttpClient)
    implementation(Dependencies.okHttpClientInterceptor)

    // Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConvertGson)
    implementation(Dependencies.retrofitAdapterRxJava2)

    // RecyclerView
    implementation(Dependencies.recyclerView)

    // Adapter Delegate
    implementation(Dependencies.adapterDelegate)

    // Glide & ImageViewer & Fresco
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)
    implementation(Dependencies.fresco)
    implementation(Dependencies.frescoImageViewer)
    implementation(Dependencies.circleImageView)

    // Loading Animation Indicator
    implementation(Dependencies.aviLoadingIndicator)

    // Floating Action Button - Menu
    implementation(Dependencies.floatingActionButton)

    // Firebase
    implementation(Dependencies.firebaseAuth)
    implementation(Dependencies.firebaseFirestore)

    // Stepper Touch
    implementation(Dependencies.stepperTouch)

    // Konfetti
    implementation(Dependencies.konfetti)

    // Expandable Layout
    implementation(Dependencies.expandableLayout)

    // Testing
    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.expressoCore)
}
