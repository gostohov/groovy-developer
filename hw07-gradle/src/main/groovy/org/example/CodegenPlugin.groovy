package org.example

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.compile.GroovyCompile

class CodegenPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('codegen', CodegenPluginExtension)
        extension.field.convention('isItGroovy')
        extension.className.convention('GeneratedClass')
        extension.packageName.convention('org.example')
        extension.outputDir.convention(
                project.layout.buildDirectory.dir(
                        "generated-source/${extension.packageName.get().replace('.', '/')}"
                )
        )

        def generate = project.tasks.register('generate', CodegenTask) {
            field.set(extension.field)
            className.set(extension.className)
            packageName.set(extension.packageName)
            outputDir.set(extension.outputDir)
        }

        project.tasks.withType(GroovyCompile).configureEach {
            dependsOn generate
        }

        project.afterEvaluate {
            (project.extensions["sourceSets"] as SourceSetContainer)["main"]
                    .java
                    .srcDir(extension.outputDir)
        }
    }
}