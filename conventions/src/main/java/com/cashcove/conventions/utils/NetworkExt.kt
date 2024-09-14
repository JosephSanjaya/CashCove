package com.cashcove.conventions.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.applyNetwork() {
    dependencies {
        findLibs("chucker")?.let { add("debugImplementation", it) }
        // findLibs("chucker-no-op")?.let { add("releaseImplementation", it) }
        // FIXME: revert this when we want to genuinely release the app
        findLibs("chucker")?.let { add("releaseImplementation", it) }
    }
}