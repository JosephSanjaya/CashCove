plugins {
    alias(sjy.plugins.buildlogic.app)
    alias(sjy.plugins.buildlogic.compose)
    alias(sjy.plugins.buildlogic.detekt)
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
    implementation(project(":features:auth:login"))
    implementation(project(":features:auth:onboarding"))
    implementation(project(":features:auth:register"))
    implementation(project(":features:auth:otp"))
    coreLibraryDesugaring(libs.androidx.desugar)
    implementation(project(":core"))
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}
