import java.util.ArrayList;

public class Bank {
    ArrayList<Account> accounts;
    private double money;
    private int newAccountNumber = 1000000; //Seven digit accountNumbers
    private int gracePeriod;

    Bank(){
        accounts = new ArrayList<>();
        this.money = 0.0;
        gracePeriod = 60;
    }

    //TODO We will change the exceptions to loggers and add in a minimum deposit
    public Account addAccount(String accountHolderName, double initialDeposit, int option){
        Account account;
        if(option == 0){
            account = new DebitAccount(accountHolderName, initialDeposit, newAccountNumber++);
        }
        else {
            account = new CreditAccount(accountHolderName, initialDeposit, newAccountNumber++, gracePeriod);
        }
        accounts.add(account);
        return account;
    }

    //TODO We will change the exceptions to loggers
    public void withdraw(Account account, double amount){
        if(amount <= 0)
            throw new IllegalArgumentException("Insufficient amount");
        boolean found = false;
        for(int i = 0; i < accounts.size() && !found; i++){
            if(accounts.get(i).equals(account)){
                accounts.get(i).withdraw(amount);
                found = true;
            }
        }
        throw new RuntimeException("Account does not exist.");
    }

    public void deposit(Account account, double amount){
        if(amount <= 0)
            throw new IllegalArgumentException("Insufficient amount");
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).equals(account)){
                accounts.get(i).deposit(amount);
                return;
            }
        }
        throw new RuntimeException("Account does not exist.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Bank accounts:\n");
        for(Account account: accounts)
            sb.append(account.toString());
        return sb.toString();
    }


    private class DebitAccount implements Account {
        private String name;
        private double money;
        private int accountNumber;

        //TODO We will change the exceptions to loggers
        public DebitAccount(String name, double money, int accountNumber){
            if(money < 0 || name.isEmpty())
                throw new IllegalArgumentException("This is not a valid input");
            this.money = money;
            this.name = name;
            this.accountNumber = accountNumber;
        }

        //TODO We will change the exceptions to loggers
        @Override
        public void withdraw(double amount) {
            if(amount <= 0 || amount > money){
                throw new IllegalArgumentException("Invalid amount");
            }
            this.money -= amount;
        }

        //TODO We will change the exceptions to loggers
        @Override
        public void deposit(double amount) {
            if(amount <= 0){
                throw new IllegalArgumentException("Invalid amount");
            }
            this.money += amount;
        }

        @Override
        public String displayBalance() {
            if(this.money >= 0)
                return "Balance: €" + this.money;
            else
                return "Balance: -€" + Math.abs(this.money);
        }

        @Override
        public boolean equals(Account account) {return account.getAccountNumber() == accountNumber;}

        @Override
        public String toString() {
            return "Account Name: " + name + " " + displayBalance() + "\n";
        }

        /*Needed Getters*/
        public double getMoney() {
            return money;
        }

        @Override
        public int getAccountNumber() {
            return accountNumber;
        }

        /*Needed Setters*/
        protected void setMoney(double money){
            this.money = money;
        }
    }



    private class CreditAccount extends DebitAccount{
        private double bill;
        private int gracePeriod;

        public CreditAccount(String name, double money, int accountNumber, int gracePeriod) {
            super(name, money, accountNumber);
            this.gracePeriod = gracePeriod;
        }

        public boolean pastGracePeriod(int days){
            return gracePeriod > days;
        }

        public boolean withinGracePeriod(int days){
            return !pastGracePeriod(days);
        }

        @Override
        public void withdraw(double amount) {
            if(amount <= 0){
                throw new IllegalArgumentException("Invalid amount");
            }
            double money = getMoney() - amount;
            super.setMoney(money);
        }

    }


}
