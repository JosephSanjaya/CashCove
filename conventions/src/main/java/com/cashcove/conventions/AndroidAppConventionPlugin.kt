package com.cashcove.conventions

import com.cashcove.conventions.utils.applyHilt
import com.cashcove.conventions.utils.applyKotlinPlugins
import com.cashcove.conventions.utils.applyKspPlugins
import com.cashcove.conventions.utils.applyNetwork
import com.cashcove.conventions.utils.applyPluginsWithLog
import com.cashcove.conventions.utils.configureFlavors
import com.cashcove.conventions.utils.testDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                applyPluginsWithLog("com.android.application")
                applyKotlinPlugins()
                applyKspPlugins()
                applyHilt()
                applyNetwork()
                configureFlavors()
                applyPluginsWithLog("com.cashcove.conventions.target")
                applyPluginsWithLog("com.cashcove.conventions.detekt")
                testDependencies()
            }
        }
    }
}
