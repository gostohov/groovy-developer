package org.example

static void main(String[] args) {
    new XlsxBuilder("test.xlsx").builder {
        sheet(0) {
            row(0) {
                cell {
                    value = 1
                    style = new Style()
                }
                cell {
                    idx = 3
                    value = "test"
                    style = new Style()
                }
            }
        }
    }.save()
}