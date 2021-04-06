plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(Deps.Kotlin.stdLib)
    implementation(Deps.Android.androidx.coreKtx)
    implementation(Deps.Android.androidx.appcompat)
    implementation(Deps.Android.androidx.lifecycleExtension)
}