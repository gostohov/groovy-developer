package org.example

import groovy.xml.MarkupBuilder

class XmlConverter {
    static void convertToXml(Map<String, Object> jsonData, String filePath) {
        def writer = new FileWriter(filePath)
        def xml = new MarkupBuilder(writer)

        xml.employee {
            name jsonData.name
            age jsonData.age
            secretIdentity jsonData.secretIdentity
            powers {
                jsonData.powers.each { powerValue ->
                    power("${powerValue}")
                }
            }
        }
        writer.close()
    }
}