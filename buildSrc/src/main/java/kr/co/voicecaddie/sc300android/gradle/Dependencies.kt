package kr.co.voicecaddie.sc300android.gradle

object Versions {
    const val gradle_version = "7.2.2"
    const val android_maven_gradle_plugin_version = "2.1"

    const val android_core_ktx = "1.7.0"
    const val android_appcompat = "1.5.0"
    const val android_material = "1.8.0-rc01"
    const val android_constraintlayout = "2.1.3"
    const val android_recyclerview = "1.2.1"
    const val android_espresso_core = "3.4.0"
    const val android_junit_test_impl = "4.12"
    const val android_junit = "1.1.3"
    const val android_legacy_support_version = "1.0.0"
    const val android_annotations = "1.4.0"

    const val kotlin_version = "1.6.10"
    const val nav_version = "2.5.3"
    const val dagger_hilt_version = "2.41"
    const val dagger_hilt_navigation = "1.0.0"
    const val dagger_hilt_testing_version = "2.44"
    const val lifecycle_version = "2.4.0"
    const val view_version = "1.5.0"
    const val coroutines_version = "1.4.1"
    const val room_version = "2.4.3"
    const val multidex_version = "2.0.1"

    // Bluetooth
    const val dfu_version = "1.12.0"
    // Network
    const val okhttp_version = "4.9.0"
    const val retrofit_version = "2.9.0"
    const val gson_version = "2.8.9"
    const val moshi_version = "1.13.0"
    const val moshi_converter = "2.9.0"

    // Images
    const val lottie_version = "4.0.0"
    const val coil_version = "2.2.0"
    const val glide_version = "4.11.0"
    const val image_cropper_version = "2.8.0"


    // Google
    const val google_services = "4.3.13"

    // Android 12(SDK 31)+ Splash Screen
    const val splash_screen = "1.0.0"

}

object Dependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val androidMavenGradlePlugin = "com.github.dcendents:android-maven-gradle-plugin:${Versions.android_maven_gradle_plugin_version}"
    const val googleServicesPlugin = "com.google.gms:google-services:${Versions.google_services}"
    const val androidxAnnotations = "androidx.annotation:annotation:${Versions.android_annotations}"
    const val androidxActivityKtx = "androidx.activity:activity-ktx:${Versions.view_version}"
    const val androidxFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.view_version}"
    const val androidxFragmentTest = "androidx.fragment:fragment-testing:${Versions.view_version}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.android_appcompat}"
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.android_core_ktx}"
    const val materialComponents = "com.google.android.material:material:${Versions.android_material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.android_constraintlayout}"
    const val androidxRecyclerview = "androidx.recyclerview:recyclerview:${Versions.android_recyclerview}"
    const val androidLegacySupport = "androidx.legacy:legacy-support-v4:${Versions.android_legacy_support_version}"
    const val androidxTestJunit = "androidx.test.ext:junit:${Versions.android_junit}"
    const val testImplJunit = "junit:junit:${Versions.android_junit_test_impl}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.android_espresso_core}"
    const val androidSplashScreen = "androidx.core:core-splashscreen:${Versions.splash_screen}"

    const val navigationSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}"
    const val navigationFragmentKTX = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationRuntimeKTX = "androidx.navigation:navigation-runtime-ktx:${Versions.nav_version}"
    const val navigationTest = "androidx.navigation:navigation-testing:${Versions.nav_version}"
    const val navigationUIKTX = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val navigationDynamicFeaturesFragment = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_version}"

    const val multiDex = "androidx.multidex:multidex:${Versions.multidex_version}"

    const val androidxLifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val androidxLifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val androidxLifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    const val androidxLifecycleViewModelSavedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"
    const val androidxLifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_version}"
    const val androidxLifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"

    const val daggerHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger_hilt_version}"
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger_hilt_version}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt_version}"
    const val daggerHiltNavGraph = "androidx.hilt:hilt-navigation-fragment:${Versions.dagger_hilt_navigation}"
    const val daggerHiltTest = "com.google.dagger:hilt-android-testing:${Versions.dagger_hilt_testing_version}"
    const val daggerHiltTestKtx = "com.google.dagger:hilt-android-compiler${Versions.dagger_hilt_testing_version}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshi_converter}"
    const val retrofitScalarsConverter = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit_version}"

    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi_version}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_version}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie_version}"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room_version}"
    const val roomTesting = "androidx.room:room-testing:${Versions.room_version}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"

    const val coil = "io.coil-kt:coil:${Versions.coil_version}"
    const val coilVideo = "io.coil-kt:coil-video:${Versions.coil_version}"

    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"

}