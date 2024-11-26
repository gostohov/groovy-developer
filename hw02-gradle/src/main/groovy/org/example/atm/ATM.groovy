package org.example.atm

import org.example.atm.model.Banknote
import org.example.atm.storage.CellStorage
import org.example.atm.service.MoneyProcessor

class ATM {
    private CellStorage storage = new CellStorage()

    void deposit(Map<Banknote, Integer> banknotes) {
        storage += banknotes
    }

    Map<Banknote, Integer> withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной")
        }
        if (amount > getBalance()) {
            throw new IllegalStateException("Недостаточно средств")
        }

        Map<Banknote, Integer> result = MoneyProcessor.calculateWithdrawal(amount, storage.getState())
        storage -= result
        return result
    }

    int getBalance() {
        storage.balance
    }
}
