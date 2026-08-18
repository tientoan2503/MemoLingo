import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.tientoan.memolingo.configureDetektCommon
import com.tientoan.memolingo.configureSpotlessCommon
import com.tientoan.memolingo.libs
import com.tientoan.memolingo.logHighlight
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        logHighlight("Apply KMP Library Convention Plugin")

        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
            apply("com.android.kotlin.multiplatform.library")
        }

        configureSpotlessCommon()
        configureDetektCommon()

        extensions.configure<KotlinMultiplatformExtension> {
            compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }

            configure<KotlinMultiplatformAndroidLibraryTarget> {
                logHighlight("compileSdk version: ${libs.findVersion("android-compileSdk").get().requiredVersion}")
                logHighlight("minSdk version: ${libs.findVersion("android-minSdk").get().requiredVersion}")

                compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
                minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()

                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }

            if (System.getProperty("os.name").lowercase().contains("mac")) {
                iosArm64()
                iosSimulatorArm64()
            }

            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                }
                commonTest.dependencies {
                    implementation(libs.findLibrary("kotlin-test").get())
                }
            }
        }
    }
}
