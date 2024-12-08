package org.example

import spock.lang.Specification

class HtmlGeneratorTest extends Specification {
    def "test generateHtml"() {
        given: "JSON-данные"
        Map<String, Object> jsonData = [
                name: "Пупкин Морква Свеклович",
                age: 22,
                secretIdentity: "322-223",
                powers: [100, 50, 70]
        ]

        when: "HTML генерируется"
        String html = HtmlGenerator.generateHtml(jsonData)

        then: "HTML содержит правильные данные"
        html.contains("<p>Пупкин Морква Свеклович</p>")
        html.contains("<p>22</p>")
        html.contains("<p>322-223</p>")
        html.contains("<li>100</li>")
        html.contains("<li>50</li>")
        html.contains("<li>70</li>")
    }
}