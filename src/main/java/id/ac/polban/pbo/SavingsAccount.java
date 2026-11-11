package id.ac.polban.pbo;

public class SavingsAccount extends BankAccount {
    public static final double MIN_BALANCE = 50_000;
    private final double interestRate;

    public SavingsAccount(String accountNumber, String owner,
                          double initialBalance,
                          double interestRate) {
        super(accountNumber, owner, initialBalance);
        if (interestRate < 0)
            throw new IllegalArgumentException(
                "Bunga tidak boleh negatif");
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientBalanceException {
        if (getBalance() - amount < MIN_BALANCE)
            throw new InsufficientBalanceException(
                "Saldo minimum Rp50.000 harus dipertahankan");
        super.withdraw(amount);
    }

    public void addInterest() {
        balance += balance * interestRate;
    }
}