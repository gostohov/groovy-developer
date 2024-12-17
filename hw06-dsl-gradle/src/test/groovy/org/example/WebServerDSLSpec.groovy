package org.example

import spock.lang.Specification

class WebServerDSLSpec extends Specification {

    def "Test basic server config"() {
        when:
        def config = WebServerDSL.configure {
            server {
                host 'localhost'
                port 8080
                context '/test'
                mappings {
                    get '/hello', 'com.example.HelloHandler'
                }
            }
        }

        then:
        config.host == 'localhost'
        config.port == 8080
        config.context == '/test'
        config.mappings.mappings.size() == 1
        config.mappings.mappings[0].path == '/hello'
        config.mappings.mappings[0].handler == 'com.example.HelloHandler'
    }

    def "Test environment inheritance"() {
        when:
        def config = WebServerDSL.configure {
            server {
                host 'base.local'
                port 8000
                environments {
                    base {
                        host 'base.local'
                        port 8000
                        mappings {
                            get '/health', 'BaseHealthHandler'
                        }
                    }
                    dev {
                        inherits 'base'
                        host 'dev.local'
                        port 8081
                        mappings {
                            get '/devOnly', 'DevHandler'
                        }
                    }
                }
            }
        }

        then:
        config.environments['dev'].host == 'dev.local'
        config.environments['dev'].port == 8081
        config.environments['dev'].mappings.mappings.size() == 1
        config.environments['dev'].mappings.mappings[0].handler == 'DevHandler'
        config.environments['base'].mappings.mappings.size() == 1
    }
}