package org.example

class WebServerDSL {
    private WebServerConfig config = new WebServerConfig()
    private EnvironmentConfig currentEnv
    private boolean environmentSection = false
    private boolean mappingsSection = false

    static WebServerConfig configure(Closure closure) {
        WebServerDSL dsl = new WebServerDSL()
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
        dsl.config.inheritFromBaseIfNeeded()
        return dsl.config
    }

    void host(String hostName) {
        if (environmentSection && currentEnv) {
            currentEnv.host = hostName
        } else {
            config.host = hostName
        }
    }

    void port(int p) {
        if (environmentSection && currentEnv) {
            currentEnv.port = p
        } else {
            config.port = p
        }
    }

    void context(String ctx) {
        if (environmentSection && currentEnv) {
            currentEnv.context = ctx
        } else {
            config.context = ctx
        }
    }

    void server(Closure closure) {
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    void environments(Closure closure) {
        environmentSection = true
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
        environmentSection = false
        currentEnv = null
    }

    void mappings(Closure closure) {
        mappingsSection = true
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
        mappingsSection = false
    }

    void env(String name, Closure closure) {
        // для окружения
        currentEnv = new EnvironmentConfig()
        config.environments[name] = currentEnv
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
        currentEnv = null
    }

    // Небольшой хелпер для создания окружений через динамические методы
    def methodMissing(String name, args) {
        if (environmentSection && args && args[0] instanceof Closure) {
            // предполагаем, что вызов вида:
            // dev { ... }
            currentEnv = new EnvironmentConfig()
            config.environments[name] = currentEnv
            Closure closure = args[0]
            closure.delegate = this
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure()
            currentEnv = null
        } else if (mappingsSection) {
            // вызовы вида: get '/path', 'handler.ClassName'
            String method = name.toUpperCase()
            if (args.size() == 2) {
                String path = args[0]
                String handler = args[1]
                if (environmentSection && currentEnv) {
                    currentEnv.mappings.addMapping(method, path, handler)
                } else {
                    config.mappings.addMapping(method, path, handler)
                }
            }
        } else {
            throw new MissingMethodException(name, this.class, args)
        }
    }

    // Наследование окружений
    void inherits(String envName) {
        if (environmentSection && currentEnv) {
            currentEnv.inherits = envName
        }
    }
}