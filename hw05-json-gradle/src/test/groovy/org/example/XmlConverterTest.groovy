package org.example

import spock.lang.Specification

class XmlConverterTest extends Specification {
    def "test convertToXml"() {
        given: "JSON-данные"
        Map<String, Object> jsonData = [
                name: "Пупкин Морква Свеклович",
                age: 22,
                secretIdentity: "322-223",
                powers: [100, 50, 70]
        ]
        String filePath = "test.xml"

        when: "XML создаётся"
        XmlConverter.convertToXml(jsonData, filePath)

        then: "XML-файл содержит правильные данные"
        def fileContent = new File(filePath).text
        fileContent.contains("<name>Пупкин Морква Свеклович</name>")
        fileContent.contains("<age>22</age>")
        fileContent.contains("<secretIdentity>322-223</secretIdentity>")
        fileContent.contains("<power>100</power>")
        fileContent.contains("<power>50</power>")
        fileContent.contains("<power>70</power>")

        cleanup: "Удаляем тестовый файл"
        new File(filePath).delete()
    }
}