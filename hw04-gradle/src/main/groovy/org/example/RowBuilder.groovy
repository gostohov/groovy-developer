package org.example

import org.apache.poi.xssf.usermodel.XSSFRow

class RowBuilder {
    Integer id
    List<CellBuilder> cells = []
    XSSFRow row

    RowBuilder(Integer id) {
        this.id = id
    }

    def cell(@DelegatesTo(CellBuilder) Closure closure) {
        def delegate = new CellBuilder(id)
        closure.setDelegate(delegate)
        closure.setResolveStrategy(Closure.DELEGATE_FIRST)
        closure.call()

        def cell = row.createCell(delegate.idx)
        cell.setCellValue(delegate.value)
        cell.setCellStyle(delegate.style.poiStyle)
        delegate.cell = cell
        cells << delegate
    }
}