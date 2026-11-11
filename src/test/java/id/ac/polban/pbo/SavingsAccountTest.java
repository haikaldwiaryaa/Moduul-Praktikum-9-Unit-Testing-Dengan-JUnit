package id.ac.polban.pbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SavingsAccountTest {

    @Test
    void aturanSubclass_berlakuLewatReferenceInduk() {
        BankAccount acc = new SavingsAccount(
            "003", "Citra", 100_000, 0.05);

        assertThrows(
            InsufficientBalanceException.class,
            () -> acc.withdraw(60_000)
        );

        assertEquals(100_000, acc.getBalance(), 0.001);
    }

    @Test
    void withdraw_tepatBatasMinimum_diterima()
            throws InsufficientBalanceException {
        SavingsAccount savings = new SavingsAccount(
            "003", "Citra", 100_000, 0.05);

        savings.withdraw(50_000);

        assertEquals(SavingsAccount.MIN_BALANCE,
                     savings.getBalance(), 0.001);
    }
    
    @Test
    void constructor_bungaNegatif_ditolak() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> new SavingsAccount("003", "Citra", 100_000, -0.01));

        assertEquals("Bunga tidak boleh negatif", ex.getMessage());
    }
    
}