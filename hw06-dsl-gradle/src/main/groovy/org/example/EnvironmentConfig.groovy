package org.example

class EnvironmentConfig {
    String host
    Integer port
    String context
    String inherits
    MappingsConfig mappings = new MappingsConfig()

    @Override
    String toString() {
        "EnvironmentConfig(host=$host, port=$port, context=$context, inherits=$inherits, mappings=$mappings)"
    }
}