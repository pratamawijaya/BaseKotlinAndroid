import org.gradle.api.Plugin
import org.gradle.api.Project

class ClassLoaderPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // no-op
    }
}

object Deps {
    private const val kotlinVersion = "1.4.32"
    private const val coroutinesVersion = "1.3.4"
    private const val detektVersion = "1.9.1"


    object AndroidConfig {
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.3"
        const val minSdkVersion = 24
        const val targetSdkVersion = 30
    }

    object Kotlin {
        val stdLib = StdLib()

        class StdLib(
            private val name: String = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        ) : CharSequence by name {
            val jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
            override fun toString(): String = name
        }

        object Test {
            const val common = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
        }
    }

    object Kotlinx {
        val coroutines = Coroutines

        object Coroutines {
            val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
            val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
            val core = Core()

            class Core(
                private val name: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
            ) : CharSequence by name {
                val common =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion"
                val native =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutinesVersion"

                override fun toString() = name
            }
        }

        object Metadata {
            const val jvm = "org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.1.0"
        }
    }

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:4.1.3"
        const val googleMaterial = "com.google.android.material:material:1.3.0"
        const val sdp = "com.intuit.sdp:sdp-android:1.0.6"

        val androidx = Androidx

        object Androidx {
            private const val NAV_VERSION = "2.3.4"

            const val appcompat = "androidx.appcompat:appcompat:1.2.0"
            const val coreKtx = "androidx.core:core-ktx:1.3.2"
            const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:2.2.0"
            const val exifinterface = "androidx.exifinterface:exifinterface:1.1.0"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
            const val navFragmentKtx =
                "androidx.navigation:navigation-fragment-ktx:$NAV_VERSION"
            const val navUiKtx = "androidx.navigation:navigation-ui-ktx:$NAV_VERSION"
            const val navFeatureModule =
                "androidx.navigation:navigation-dynamic-features-fragment:$NAV_VERSION"
            const val navTesting = "androidx.navigation:navigation-testing:$NAV_VERSION"
        }
    }
}

object AndroidConfig {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 24
    const val targetSdkVersion = 30
}

object Version {
    const val KOTLIN = "1.4.32"
    const val JetpackNav = "2.3.4"
}

object Library {
    const val NAV_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Version.JetpackNav}"
    const val NAV_UI = "androidx.navigation:navigation-ui-ktx:${Version.JetpackNav}"
    const val NAV_FEATURE_MODULE =
        "androidx.navigation:navigation-dynamic-features-fragment:${Version.JetpackNav}"
    const val NAV_TESTING = "androidx.navigation:navigation-testing:${Version.JetpackNav}"

    const val KOTLIN_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:1.4.32"
    const val CORE_KTX = "androidx.core:core-ktx:1.3.2"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"

    const val ANDROID_LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:2.2.0"
}