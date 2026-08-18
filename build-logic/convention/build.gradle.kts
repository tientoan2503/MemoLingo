import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `kotlin-dsl`
  alias(libs.plugins.android.lint)
}

group = "com.tientoan.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.compose.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)
  compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidLint") {
      id = libs.plugins.memolingo.android.lint.get().pluginId
      implementationClass = "AndroidLintConventionPlugin"
    }
    
    register("androidApplication") {
      id = libs.plugins.memolingo.android.application.asProvider().get().pluginId
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    
    register("androidApplicationCompose") {
      id = libs.plugins.memolingo.android.application.compose.get().pluginId
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    
    register("androidTest") {
      id = libs.plugins.memolingo.android.test.get().pluginId
      implementationClass = "AndroidTestConventionPlugin"
    }
    
    register("kmpLibrary") {
      id = libs.plugins.memolingo.kmp.library.get().pluginId
      implementationClass = "KmpLibraryConventionPlugin"
    }

    register("kmpCompose") {
      id = libs.plugins.memolingo.kmp.compose.get().pluginId
      implementationClass = "KmpComposeConventionPlugin"
    }

    register("root") {
      id = libs.plugins.memolingo.root.get().pluginId
      implementationClass = "RootPlugin"
    }
  }
}
