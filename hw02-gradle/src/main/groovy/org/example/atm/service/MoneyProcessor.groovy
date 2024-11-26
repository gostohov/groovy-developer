package org.example.atm.service

import org.example.atm.model.Banknote
import groovy.transform.CompileStatic

@CompileStatic
class MoneyProcessor {
    static Map<Banknote, Integer> calculateWithdrawal(int amount, Map<Banknote, Integer> available) {
        int remainingAmount = amount

        Map<Banknote, Integer> result = available.keySet()
                .toList()
                .sort { -it.value }
                .collectEntries { banknote ->
                    int count = available[banknote]
                    int maxBanknotesNeeded = (int) (remainingAmount / banknote.value)
                    int neededCount = Math.min(maxBanknotesNeeded, count)
                    if (neededCount > 0) {
                        remainingAmount -= neededCount * banknote.value
                        [(banknote): neededCount]
                    } else {
                        [:]
                    }
                }

        if (remainingAmount > 0) {
            throw new IllegalStateException("Невозможно выдать запрошенную сумму: ${remainingAmount} осталось неразменным")
        }

        return result
    }
}
