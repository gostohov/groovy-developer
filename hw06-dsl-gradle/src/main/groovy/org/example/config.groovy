package org.example

def config = WebServerDSL.configure {
    server {
        host 'localhost'
        port 8080
        context '/app'

        environments {
            dev {
                host 'dev.local'
                port 8081
                inherits 'base'
                mappings {
                    get '/dev/health', 'com.example.handlers.DevHealthHandler'
                }
            }

            test {
                host 'test.local'
                port 8082
                inherits 'base'
                mappings {
                    get '/test/health', 'com.example.handlers.TestHealthHandler'
                }
            }

            prod {
                inherits 'base'
                host 'prod.local'
                port 80
                mappings {
                    get '/prod/health', 'com.example.handlers.ProdHealthHandler'
                }
            }

            base {
                // Базовое окружение, от которого наследуемся
                host 'base.local'
                port 8000
                mappings {
                    get '/health', 'com.example.handlers.BaseHealthHandler'
                }
            }
        }

        mappings {
            get '/hello', 'com.example.handlers.HelloHandler'
            post '/submit', 'com.example.handlers.SubmitHandler'
        }
    }
}

config