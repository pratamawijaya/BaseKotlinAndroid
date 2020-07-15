object AndroidConfig {
    val compileSdkVersion = 29
    val buildToolsVersion = "30.0.0"
    val minSdkVersion = 16
    val targetSdkVersion = 29
}

object Versions {
    val kotlin = "1.3.72"
    val gradle = "4.0.0"
    val junit = "4.12"
    val core_ktx = "1.3.0"
    val androidx_appcompat = "1.1.0"
    val androidx_constraintlayout = "1.1.3"
    val routing_navigator = "1.0.0"
}


object AndroidLib {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val androidx_core = "androidx.core:core-ktx:${Versions.core_ktx}"
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    val routing_navigator = "com.github.florent37:navigator:${Versions.routing_navigator}"
}

object AndroidTestLib {
    val junit_lib = "junit:junit:${Versions.junit}"
    val android_test_junit = "androidx.test.ext:junit:1.1.1"
    val android_test_espresso_core = "androidx.test.espresso:espresso-core:3.2.0"
}