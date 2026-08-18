package com.tientoan.memolingo

import org.gradle.api.configuration.BuildFeatures

fun BuildFeatures.isIsolatedProjectsEnabled(): Boolean = isolatedProjects.active.orElse(false).get()
