plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {

    implementation(Deps.Kotlin.stdLib)
    implementation(Deps.Android.androidx.coreKtx)
    implementation(Deps.Android.androidx.appcompat)
    implementation(Deps.Android.googleMaterial)
    implementation(Deps.Android.sdp)

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}