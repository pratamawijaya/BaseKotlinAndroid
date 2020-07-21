object AndroidConfig {
    const val compileSdkVersion = 29
    const val buildToolsVersion = "30.0.0"
    const val minSdkVersion = 16
    const val targetSdkVersion = 29
}

object Versions {
    const val kotlin = "1.3.72"
    const val gradle = "4.0.0"
    const val junit = "4.12"
    const val core_ktx = "1.3.0"
    const val androidx_appcompat = "1.1.0"
    const val androidx_constraintlayout = "1.1.3"
    const val routing_navigator = "1.0.0"
    const val coroutines = "1.3.7"
    const val retrofit = "2.9.0"
    const val retrofit_moshi = "2.6.2"
    const val logging_interceptor = "4.8.0"
    const val hilt = "2.28-alpha"
    const val groupie = "2.8.0"
}


object AndroidLib {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val androidx_core = "androidx.core:core-ktx:${Versions.core_ktx}"
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    val routing_navigator = "com.github.florent37:navigator:${Versions.routing_navigator}"
    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val retrofit_android = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_moshi}"
    val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_processor_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    val groupie = "com.xwray:groupie:${Versions.groupie}"
    val groupie_kotline_ext = "com.xwray:groupie-kotlin-android-extensions:${Versions.groupie}"

}

object AndroidTestLib {
    val junit_lib = "junit:junit:${Versions.junit}"
    val android_test_junit = "androidx.test.ext:junit:1.1.1"
    val android_test_espresso_core = "androidx.test.espresso:espresso-core:3.2.0"
}