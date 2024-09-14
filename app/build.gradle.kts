plugins {
    id("com.cashcove.conventions.app")
    id("com.cashcove.conventions.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.cashcove.app"
    defaultConfig {
        applicationId = "com.cashcove.app"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    coreLibraryDesugaring(libs.androidx.desugar)
}
