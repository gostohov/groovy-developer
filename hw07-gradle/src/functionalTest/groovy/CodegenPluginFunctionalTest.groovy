import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.io.TempDir
import org.gradle.testkit.runner.GradleRunner

class CodegenPluginFunctionalTest {
    @TempDir
    public File testProjectDir
    File buildFile

    @BeforeEach
    void init() {
        buildFile = new File(testProjectDir, 'build.gradle')
        buildFile << """\
            plugins {
                id 'groovy'
                id 'org.example.codegenPlugin' apply true
            }
            
            repositories {
                mavenCentral()
            }

            codegen {
                field = 'isItGroovy'
                className = 'GeneratedClass'
                packageName = 'org.example'
            }
        """.stripIndent()
    }

    @AfterEach
    void tearDown() {
        buildFile.delete()
    }


    @Test
    void generateTask() {
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments('build')
                .withPluginClasspath()
                .build()

        assert result.task(":generate").outcome.name() == "SUCCESS"

        def filePath = "build/generated-source/org/example/GeneratedClass.groovy"
        def file = new File(testProjectDir, filePath)
        def clazz = new GroovyClassLoader().parseClass(file)

        assert clazz.simpleName == "GeneratedClass"
        assert clazz.packageName == "org.example"
    }
}