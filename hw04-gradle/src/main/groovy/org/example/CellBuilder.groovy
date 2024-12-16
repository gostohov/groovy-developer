package org.example


import org.apache.poi.xssf.usermodel.XSSFCell

class CellBuilder {
    Integer idx
    Object value
    XSSFCell cell
    Style style

    CellBuilder(Integer idx) {
        this.idx = idx
    }
}