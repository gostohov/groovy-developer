package org.example


import org.apache.poi.xssf.usermodel.XSSFSheet

class SheetBuilder {
    Integer id
    String name
    XSSFSheet sheet

    SheetBuilder(Integer id, String name) {
        this.id = id
        this.name = name ?: this.id
    }

    def row(int idx, @DelegatesTo(RowBuilder) Closure closure) {
        def delegate = new RowBuilder(idx)
        delegate.row = sheet.createRow(idx)
        closure.delegate = delegate
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }
}