import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property

interface GreetingPluginExtension {
    val message: Property<String>
    val greeter: Property<String>
}

class HelloPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.create("HelloPluginTask") {
            println("Hello There")
        }
    }

}