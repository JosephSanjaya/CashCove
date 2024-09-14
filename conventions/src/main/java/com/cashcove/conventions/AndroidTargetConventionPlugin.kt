package com.cashcove.conventions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.cashcove.conventions.utils.findLibs
import com.cashcove.conventions.utils.implementationWithLog
import com.cashcove.conventions.utils.isApp
import com.cashcove.conventions.utils.isAppOrLib
import com.cashcove.conventions.utils.isLib
import com.cashcove.conventions.utils.libs
import com.cashcove.conventions.utils.printMessage
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

class AndroidTargetConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            if (!isAppOrLib()) return@with
            val type =
                when {
                    isApp() -> {
                        the<ApplicationExtension>().apply {
                            defaultConfig {
                                multiDexEnabled = true
                                printMessage("Set Multidex enabled")
                            }
                        }
                        ApplicationExtension::class
                    }

                    isLib() -> LibraryExtension::class
                    else -> null
                } ?: return@with

            the(type).apply {
                val targetSdk = libs.findVersion("sdk-target").get().toString().toInt()
                val minSdkVersion = libs.findVersion("sdk-min").get().toString().toInt()
                if (this is ApplicationExtension) {
                    defaultConfig.targetSdk = targetSdk
                }
                compileSdk = libs.findVersion("sdk-compile").get().toString().toInt()
                defaultConfig {
                    minSdk = minSdkVersion
                }

                printMessage("Setting up target android sdk to: $targetSdk")
                printMessage("Setting up min android sdk to: $minSdkVersion")
                the<JavaPluginExtension>().toolchain {
                    languageVersion.set(
                        JavaLanguageVersion.of(
                            libs.findVersion("jvm-target").get().toString()
                        )
                    )
                }
                compileOptions {
                    isCoreLibraryDesugaringEnabled = true
                }
            }
            dependencies {
                findLibs("androidx-core-ktx")?.also { implementationWithLog(it) }
            }
        }
    }
}
