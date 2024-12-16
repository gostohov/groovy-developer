package org.example

import org.apache.poi.xssf.usermodel.XSSFWorkbook

class Workbook {
    String fileName
    XSSFWorkbook workbook

    Workbook(String fileName, XSSFWorkbook workbook) {
        this.fileName = fileName
        this.workbook = workbook
    }

    def sheet(int idx = 0, String name = null, @DelegatesTo(SheetBuilder) Closure<?> closure) {
        def delegate = new SheetBuilder(idx, name)
        def sheet = workbook.createSheet(delegate.name)
        delegate.sheet = sheet
        closure.delegate = delegate
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }

    def save() {
        new File(fileName).withOutputStream { os ->
            workbook.write(os)
        }
        workbook.close()
        println "Файл ${fileName} успешно создан."
    }
}
