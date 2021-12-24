public interface Account {
    void withdraw(double amount);
    void deposit(double amount);
    String displayBalance();
    boolean equals(Account account);
    int getAccountNumber();
}
