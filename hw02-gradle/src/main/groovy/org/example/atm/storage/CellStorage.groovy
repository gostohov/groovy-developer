package org.example.atm.storage

import org.example.atm.model.Banknote
import groovy.transform.CompileStatic

@CompileStatic
class CellStorage {
    private Map<Banknote, Integer> cells = [:]

    def plus(Map<Banknote, Integer> entry) {
        entry.each { Banknote banknote, Integer count ->
            cells[banknote] = (cells[banknote] ?: 0) + count
        }
        this
    }

    def minus(Map<Banknote, Integer> entry) {
        entry.each { Banknote banknote, Integer count ->
            if ((cells[banknote] ?: 0) < count) {
                throw new IllegalStateException("Недостаточно банкнот номинала ${banknote.value}")
            }
            cells[banknote] = cells[banknote] - count
        }
        this
    }

    Integer getBalance() {
        int sum = 0
        cells.each { Banknote banknote, Integer count ->
            sum += banknote.value * count
        }
        return sum
    }

    Map<Banknote, Integer> getState() {
        return cells.asImmutable()
    }
}