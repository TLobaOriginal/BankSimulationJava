public class Main {
    public static void main(String[] args) {
        Bank alliedIrishBank = new Bank();
        Account account1 = alliedIrishBank.addAccount("JimBob", 500.0, 0);
        alliedIrishBank.deposit(account1, 200.0);
        System.out.println(alliedIrishBank);
    }
}
