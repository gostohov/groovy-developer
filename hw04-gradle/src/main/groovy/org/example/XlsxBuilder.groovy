package org.example

import org.apache.poi.xssf.usermodel.XSSFWorkbook

class XlsxBuilder {
    String filename
    XSSFWorkbook workbook = new XSSFWorkbook()

    XlsxBuilder(String filename) {
        this.filename = filename
    }

    def builder(@DelegatesTo(Workbook) Closure closure) {
        def delegate = new Workbook(filename, workbook)
        closure.setDelegate(delegate)
        closure.setResolveStrategy(Closure.DELEGATE_FIRST)
        closure.call()
        return delegate
    }
}