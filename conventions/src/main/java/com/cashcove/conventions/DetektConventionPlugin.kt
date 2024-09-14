package com.cashcove.conventions

import com.android.tools.r8.internal.md
import com.cashcove.conventions.utils.findLibs
import com.cashcove.conventions.utils.findPlugin
import com.cashcove.conventions.utils.libs
import com.cashcove.conventions.utils.printMessage
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val detekt = findPlugin("detekt", true) ?: return
            runCatching {
                pluginManager.apply(detekt)
            }.onSuccess {
                printMessage("Success applying detekt plugin, starting configuration..")
                the<DetektExtension>().apply {
                    buildUponDefaultConfig = true
                    allRules = false
                    autoCorrect = true
                    config.setFrom("../config/detekt-rule.yml")
                    baseline = file("../config/detekt-${name}-baseline.xml")
                    parallel = true
                }
                val jvmTarget = libs.findVersion("jvm-target").get().toString()
                tasks.withType<Detekt>().configureEach {
                    this.jvmTarget = jvmTarget
                    reports {
                        html.required.set(true)
                        xml.required.set(true)
                        txt.required.set(true)
                        sarif.required.set(true)
                        md.required.set(true)
                    }
                }
                tasks.withType<DetektCreateBaselineTask>().configureEach {
                    this.jvmTarget = jvmTarget
                }

                dependencies {
                    findLibs("detekt-formatting")?.let {
                        add("detektPlugins", it)
                    }
                    findLibs("detekt-vkompose")?.let {
                        add("detektPlugins", it)
                    }
                    findLibs("detekt-twitter")?.let {
                        add("detektPlugins", it)
                    }
                }

                runCatching {
                    tasks.whenTaskAdded {
                        if (name.startsWith("assemble") || name.startsWith("compile") || name == "run") {
                            dependsOn(tasks.getByName("detekt"))
                        }
                    }
                }
            }
        }
    }
}
