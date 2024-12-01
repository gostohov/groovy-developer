package org.example.atm

import org.example.atm.model.Banknote
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class ATMTest {
    private ATM atm

    @BeforeEach
    void setUp() {
        atm = new ATM()
    }

    @Test
    void testDeposit() {
        atm.deposit([(Banknote.THOUSAND): 2])
        assertEquals(2000, atm.balance)
    }

    @Test
    void testWithdraw() {
        atm.deposit([
            (Banknote.THOUSAND): 5,
            (Banknote.FIVE_HUNDRED): 4
        ])

        def result = atm.withdraw(2500)
        
        assertEquals(2, result[Banknote.THOUSAND])
        assertEquals(1, result[Banknote.FIVE_HUNDRED])
        assertEquals(4500, atm.balance)
    }

    @Test
    void testWithdrawInsufficientFunds() {
        atm.deposit([(Banknote.THOUSAND): 1])
        
        assertThrows(IllegalStateException) {
            atm.withdraw(2000)
        }
    }

    @Test
    void testWithdrawImpossibleAmount() {
        atm.deposit([(Banknote.THOUSAND): 1])
        
        assertThrows(IllegalStateException) {
            atm.withdraw(1500)
        }
    }
}
