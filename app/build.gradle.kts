plugins {
    id("com.android.application")
    id("kotlin-android")
}

dependencies {

    implementation(Library.NAV_FRAGMENT)
    implementation(Library.NAV_UI)

    implementation(project(":feature:account"))
    implementation(project(":feature:authentication"))
    implementation(project(":feature:home"))
    implementation(project(":feature:onboard"))


    implementation(project(":library:core_android"))
    implementation(project(":library:logger"))
    implementation(project(":library:preferences"))

    implementation(Deps.Kotlin.stdLib)
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}