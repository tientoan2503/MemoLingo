import com.tientoan.memolingo.applyComposeCompilerSettings
import com.tientoan.memolingo.libs
import com.tientoan.memolingo.logHighlight
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        logHighlight("Apply KMP Compose Convention Plugin")

        with(pluginManager) {
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        applyComposeCompilerSettings()

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.findLibrary("compose-runtime").get())
                    implementation(libs.findLibrary("compose-foundation").get())
                    implementation(libs.findLibrary("compose-material3").get())
                    implementation(libs.findLibrary("compose-ui").get())
                    implementation(libs.findLibrary("compose-components-resources").get())
                    implementation(libs.findLibrary("compose-uiToolingPreview").get())
                    implementation(libs.findLibrary("androidx-lifecycle-viewmodelCompose").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
                }

                androidMain.dependencies {
                    implementation(libs.findLibrary("compose-uiToolingPreview").get())
                }
            }
        }

        dependencies {
            add("androidRuntimeClasspath", libs.findLibrary("compose-uiTooling").get())
        }
    }
}
