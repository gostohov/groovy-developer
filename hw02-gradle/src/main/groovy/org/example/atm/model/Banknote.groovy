package org.example.atm.model

enum Banknote {
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    FIVE_THOUSAND(5000)

    final int value

    private Banknote(int value) {
        this.value = value
    }

    int getValue() {
        return value
    }
}