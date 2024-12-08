package org.example

import groovy.json.JsonSlurper

class JsonParser {
    static Map<String, Object> parseJsonFromUrl(String url) {
        // Загружаем JSON из URL и парсим его в Map
        return new JsonSlurper().parse(url.toURL()) as Map<String, Object>
    }
}
