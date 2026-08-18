package com.tientoan.memolingo

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

internal fun Project.configureDetektForAndroid() {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    extensions.configure<DetektExtension> {
        // Point to the custom configuration file
        config.setFrom(isolated.rootProject.projectDirectory.file("config/detekt/detekt.yml"))

        // Use the JDK version from the convention plugin
        toolVersion = libs.findVersion("detekt").get().requiredVersion

        // Run detekt on the JVM and Android source sets
        source.setFrom(
            files(
                "src/main/java",
                "src/test/java",
                "src/main/kotlin",
                "src/test/kotlin",
            ),
        )

        allRules = true
        buildUponDefaultConfig = true
        parallel = true
        autoCorrect = true
    }
}

internal fun Project.configureDetektCommon() {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    extensions.configure<DetektExtension> {
        // Point to the custom configuration file
        config.setFrom(isolated.rootProject.projectDirectory.file("config/detekt/detekt.yml"))

        // Use the JDK version from the convention plugin
        toolVersion = libs.findVersion("detekt").get().requiredVersion

        // Run detekt on the KMP source sets
        source.setFrom(
            files(
                "src/commonMain/kotlin",
                "src/androidMain/kotlin",
                "src/iosMain/kotlin",
                "src/jvmMain/kotlin",
            ),
        )

        allRules = false
        buildUponDefaultConfig = true
        parallel = true
        autoCorrect = true
    }
}
