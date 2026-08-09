import com.android.build.api.dsl.ApplicationExtension
import com.tientoan.memolingo.configureKotlinAndroid
import com.tientoan.memolingo.configureSpotlessForAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

/**
 * Created by rikka on 9/8/26.
 **/

class AndroidApplicationConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      apply(plugin = "com.android.application")
      apply(plugin = "memolingo.android.lint")
      
      extensions.configure<ApplicationExtension> {
        configureKotlinAndroid(this)
        defaultConfig.targetSdk = 36
      }
      configureSpotlessForAndroid()
    }
  }
}