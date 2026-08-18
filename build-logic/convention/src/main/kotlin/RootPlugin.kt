import com.tientoan.memolingo.configureGitHooks
import com.tientoan.memolingo.configureSpotlessForRootProject
import com.tientoan.memolingo.logHighlight
import org.gradle.api.Plugin
import org.gradle.api.Project

class RootPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        require(target.path == ":")
        logHighlight("Apply Root Plugin")

        logHighlight("1. Register GitHook Task")
        target.configureGitHooks()

        logHighlight("2. Apply Spotless")
        target.configureSpotlessForRootProject()
    }
}
