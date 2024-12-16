package org.example

class HtmlGenerator {
    static String generateHtml(Map<String, Object> jsonData) {
        def html = new StringBuilder('<div><div id="employee">')
        html.append("<p>${jsonData.name}</p><br/>")
        html.append("<p>${jsonData.age}</p><br/>")
        html.append("<p>${jsonData.secretIdentity}</p><br/>")
        html.append('<ul id="powers">')
        jsonData.powers.each { power ->
            html.append("<li>${power}</li>")
        }
        html.append('</ul></div></div>')
        return html.toString()
    }

    static void saveHtml(String html, String filePath) {
        new File(filePath).text = html
    }
}