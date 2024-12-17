package org.example

class MappingsConfig {
    // key: method + path, value: handler class
    List<Map<String,String>> mappings = []

    void addMapping(String method, String path, String handler) {
        mappings << [method: method, path: path, handler: handler]
    }

    @Override
    String toString() {
        mappings.collect { m -> "${m.method} ${m.path} -> ${m.handler}" }.join('\n')
    }
}