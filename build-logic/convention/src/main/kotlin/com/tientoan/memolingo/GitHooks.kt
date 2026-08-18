package com.tientoan.memolingo

import org.gradle.api.Project
import org.gradle.api.tasks.Exec

internal fun Project.configureGitHooks() {
    tasks.register("installGitHooks", Exec::class.java) {
        description = "Installs git hooks via Lefthook (requires 'brew install lefthook')"
        group = "git hooks"
        commandLine(
            "sh",
            "-c",
            "lefthook install || echo '⚠️  Lefthook not found. Run: brew install lefthook'",
        )
        environment("PATH", System.getenv("PATH") ?: "")
    }

    tasks.named("prepareKotlinBuildScriptModel") {
        dependsOn("installGitHooks")
    }
}
