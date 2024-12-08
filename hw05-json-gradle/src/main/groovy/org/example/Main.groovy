package org.example

class Main {
    static void main(String[] args) {
        String jsonUrl = "https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json"

        println "Загружаем JSON из URL..."
        Map<String, Object> jsonData = JsonParser.parseJsonFromUrl(jsonUrl)

        println "Генерируем HTML..."
        String html = HtmlGenerator.generateHtml(jsonData)
        HtmlGenerator.saveHtml(html, "employee.html")

        println "Генерируем XML..."
        XmlConverter.convertToXml(jsonData, "employee.xml")

        println "HTML и XML файлы успешно созданы."
    }
}