package org.example

class WebServerConfig {
    String host
    int port
    String context
    Map<String, EnvironmentConfig> environments = [:]
    MappingsConfig mappings = new MappingsConfig()

    // Метод для применения наследования окружений,
    // например: при "prod" мы можем взять часть настроек от базового
    void inheritFromBaseIfNeeded() {
        environments.each { envName, envConfig ->
            if (envConfig.inherits && environments.containsKey(envConfig.inherits)) {
                def base = environments[envConfig.inherits]
                // Наследуем поля, если они не определены в текущем env
                if (!envConfig.host) envConfig.host = base.host
                if (!envConfig.port) envConfig.port = base.port
                if (!envConfig.context) envConfig.context = base.context
                // mappings тоже можно наследовать при желании, но предположим, что у окружений свои маппинги
            }
        }
    }

    @Override
    String toString() {
        """WebServerConfig:
           host = $host
           port = $port
           context = $context
           environments = $environments
           mappings = $mappings
        """
    }
}