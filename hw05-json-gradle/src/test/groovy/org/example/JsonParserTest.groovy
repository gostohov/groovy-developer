package org.example

import spock.lang.Specification

class JsonParserTest extends Specification {
    def "test parseJsonFromUrl"() {
        given: "URL JSON-файла"
        String testUrl = "https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json"

        when: "JSON загружается и парсится"
        Map<String, Object> result = JsonParser.parseJsonFromUrl(testUrl)

        then: "Результат содержит корректные данные"
        result.name == "Пупкин Морква Свеклович"
        result.age == 22
        result.secretIdentity == "322-223"
        result.powers == [100, 50, 70]
    }
}