package com.tientoan.memolingo

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

internal fun Project.configureSpotlessForRootProject() {
    apply(plugin = "com.diffplug.spotless")

    extensions.configure<SpotlessExtension> {
        kotlin {
            target("build-logic/convention/src/**/*.kt")
            ktlint(libs.findVersion("ktlint").get().requiredVersion)
                .editorConfigOverride(
                    mapOf(
                        "ktlint_standard_property-naming" to "disabled",
                        "ktlint_standard_kdoc" to "disabled",
                    ),
                )
            endWithNewline()
        }

        format("kts") {
            target("*.kts")
            target("build-logic/*.kts")
            target("build-logic/convention/*.kts")
            endWithNewline()
        }
    }
}

internal fun Project.configureSpotlessCommon() {
    apply(plugin = "com.diffplug.spotless")
    extensions.configure<SpotlessExtension> {
        kotlin {
            target("src/**/*.kt")
            ktlint(libs.findVersion("ktlint").get().requiredVersion)
            trimTrailingWhitespace()
            leadingTabsToSpaces()
            endWithNewline()
        }
        format("kts") {
            target("*.kts")
            endWithNewline()
        }
    }
}
