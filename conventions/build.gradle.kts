plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(libs.plugins.ksp)
}

dependencies {
    compileOnly(libs.plugin.agp)
    compileOnly(libs.plugin.kgp)
    compileOnly(libs.plugin.ksp)
    compileOnly(libs.plugin.compose)
    compileOnly(libs.plugin.detekt)
}

gradlePlugin {
    val androidTarget by plugins.creating {
        id = "com.cashcove.conventions.target"
        implementationClass = "com.cashcove.conventions.AndroidTargetConventionPlugin"
    }
    val app by plugins.creating {
        id = "com.cashcove.conventions.app"
        implementationClass = "com.cashcove.conventions.AndroidAppConventionPlugin"
    }
    val lib by plugins.creating {
        id = "com.cashcove.conventions.lib"
        implementationClass = "com.cashcove.conventions.AndroidLibConventionPlugin"
    }
    val compose by plugins.creating {
        id = "com.cashcove.conventions.compose"
        implementationClass = "com.cashcove.conventions.AndroidComposeConventionPlugin"
    }
    val detekt by plugins.creating {
        id = "com.cashcove.conventions.detekt"
        implementationClass = "com.cashcove.conventions.DetektConventionPlugin"
    }
}
