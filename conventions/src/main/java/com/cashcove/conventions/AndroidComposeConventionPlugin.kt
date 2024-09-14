package com.cashcove.conventions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.cashcove.conventions.utils.androidTestImplementationPlatformWithLog
import com.cashcove.conventions.utils.androidTestImplementationWithLog
import com.cashcove.conventions.utils.debugImplementationWithLog
import com.cashcove.conventions.utils.findLibs
import com.cashcove.conventions.utils.findPlugin
import com.cashcove.conventions.utils.implementationPlatformWithLog
import com.cashcove.conventions.utils.implementationWithLog
import com.cashcove.conventions.utils.isApp
import com.cashcove.conventions.utils.isAppOrLib
import com.cashcove.conventions.utils.isLib
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            if (!isAppOrLib()) return@with
            val type =
                when {
                    isApp() -> ApplicationExtension::class
                    isLib() -> LibraryExtension::class
                    else -> null
                } ?: return@with
            val composePlugin = findPlugin("compose-compiler", true) ?: return
            pluginManager.apply(composePlugin)
            the(type).apply {
                buildFeatures {
                    compose = true
                }
            }
            applyComposeDepencencies()
        }
    }

    private fun Project.applyComposeDepencencies() = dependencies {
        findLibs("compose-bom")?.let { implementationPlatformWithLog(it) }
        findLibs("compose-bom")?.let { androidTestImplementationPlatformWithLog(it) }
        findLibs("compose-activity")?.let { implementationWithLog(it) }
        findLibs("compose-ui")?.let { implementationWithLog(it) }
        findLibs("compose-material")?.let { implementationWithLog(it) }
        findLibs("compose-ui-graphics")?.let { implementationWithLog(it) }
        findLibs("compose-ui-preview")?.let { implementationWithLog(it) }
        findLibs("compose-icons-extended")?.let { implementationWithLog(it) }
        findLibs("compose-lifecycle-viewmodel")?.let { implementationWithLog(it) }
        findLibs("compose-lifecycle-runtime")?.let { implementationWithLog(it) }
        findLibs("compose-foundation")?.let { implementationWithLog(it) }
        findLibs("compose-lottie")?.let { implementationWithLog(it) }
        findLibs("compose-shimmer")?.let { implementationWithLog(it) }
        findLibs("compose-animation")?.let { implementationWithLog(it) }
        findLibs("compose-paging")?.let { implementationWithLog(it) }
        findLibs("compose-ui-tooling")?.let { debugImplementationWithLog(it) }
        findLibs("compose-ui-manifest")?.let { debugImplementationWithLog(it) }
        findLibs("compose-test-junit")?.let { androidTestImplementationWithLog(it) }
        findLibs("material-window-size")?.let { implementationWithLog(it) }
        findLibs("coil")?.let { implementationWithLog(it) }
        findLibs("coil-svg")?.let { implementationWithLog(it) }
        findLibs("coil-gif")?.let { implementationWithLog(it) }
    }
}
