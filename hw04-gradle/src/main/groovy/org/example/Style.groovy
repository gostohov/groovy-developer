package org.example

import org.apache.poi.xssf.usermodel.XSSFCellStyle

class Style {
    XSSFCellStyle poiStyle

    Style(XSSFCellStyle poiStyle = null) {
        this.poiStyle = poiStyle
    }
}