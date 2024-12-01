package org.example.atm.service

import org.example.atm.model.Banknote
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class MoneyProcessorTest {
    
    @Test
    void testCalculateWithdrawalOptimal() {
        def available = [
            (Banknote.FIVE_THOUSAND): 1,
            (Banknote.THOUSAND): 3,
            (Banknote.FIVE_HUNDRED): 2
        ]
        
        def result = MoneyProcessor.calculateWithdrawal(6500, available)
        
        assertEquals(1, result[Banknote.FIVE_THOUSAND])
        assertEquals(1, result[Banknote.THOUSAND])
        assertEquals(1, result[Banknote.FIVE_HUNDRED])
    }
    
    @Test
    void testCalculateWithdrawalImpossible() {
        def available = [(Banknote.THOUSAND): 1]
        
        assertThrows(IllegalStateException) {
            MoneyProcessor.calculateWithdrawal(1500, available)
        }
    }
}
