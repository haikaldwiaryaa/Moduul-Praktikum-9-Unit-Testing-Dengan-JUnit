package id.ac.polban.pbo;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    protected double balance;

    public BankAccount(String accountNumber, String owner,
                       double initialBalance) {
        if (accountNumber == null || accountNumber.isBlank())
            throw new IllegalArgumentException(
                "Nomor rekening wajib diisi");
        if (initialBalance < 0)
            throw new IllegalArgumentException(
                "Saldo awal tidak boleh negatif");
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException(
                "Jumlah setoran harus lebih dari 0");
        balance += amount;
    }
    
    public void withdraw(double amount)
            throws InsufficientBalanceException {
        if (amount <= 0)
            throw new IllegalArgumentException(
                "Jumlah penarikan harus lebih dari 0");
        if (amount > balance)
            throw new InsufficientBalanceException(
                "Saldo tidak mencukupi");
        balance -= amount;
    }
    
    public void transfer(BankAccount target, double amount)
            throws InsufficientBalanceException {
        if (target == null)
            throw new IllegalArgumentException("Rekening tujuan wajib diisi");
        if (target == this)
            throw new IllegalArgumentException(
                "Tidak bisa transfer ke rekening sendiri");
        withdraw(amount);
        target.deposit(amount);
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
}