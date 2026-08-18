package com.tientoan.memolingo

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

// Stability config + optional compose-metrics / compose-reports (Gradle property memolingo.enableComposeCompilerReports).
fun Project.applyComposeCompilerSettings() {
    val appliedFlagKey: String = "memolingo.compose.compiler.settings.applied"
    if (extensions.extraProperties.has(appliedFlagKey)) {
        return
    }
    extensions.configure<ComposeCompilerGradlePluginExtension> {
        stabilityConfigurationFiles.add(
            rootProject.layout.projectDirectory.file("gradle/compose-stability.conf"),
        )
        if (findProperty("memolingo.enableComposeCompilerReports") == "true") {
            metricsDestination.set(layout.buildDirectory.dir("compose-metrics"))
            reportsDestination.set(layout.buildDirectory.dir("compose-reports"))
        }
    }
    extensions.extraProperties.set(appliedFlagKey, true)
}
