package org.example

class DslRunner {
    static void main(String[] args) {
        def script = new GroovyShell(this.class.classLoader).parse(
                new File("src/main/groovy/com/example/dsl/config.groovy"))
        def config = script.run()
        println config
    }
}
