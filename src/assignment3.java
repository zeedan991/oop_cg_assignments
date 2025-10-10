

class a1{
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;

    public a1 (String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
    public void checkBalance() {
        System.out.println("YOUR BALANCE IS : "+balance);
    }
}

class SavingsAccount extends a1 {
    protected double interestRate;
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance, double interestRate) {
        super(accountNumber, accountHolderName, initialBalance);
        this.interestRate = interestRate;
    }
    public void applyInterest() {
        double interest = balance * interestRate / 100;
        deposit(interest);

        System.out.println("Interest applied: " + interest + ". New balance: " + balance);
    }
    public void getInterestRate() {
        System.out.println(interestRate);
    }
    public void setInterestRate(double rate) {
        if (rate > 0) {
            interestRate = rate;
        } else {
            System.out.println("Interest rate must be positive.");
        }
    }
}

public class assignment3 {
    public static void main(String[] args) {

        a1 acc1 = new a1 ("101", "Alice", 1000);
        acc1.checkBalance();
        acc1.deposit(500);
        acc1.withdraw(300);



        SavingsAccount savAcc = new SavingsAccount("102", "Bob", 2000, 4.0);
        savAcc.checkBalance();
        savAcc.deposit(1000);
        savAcc.withdraw(500);
        savAcc.setInterestRate(5.55);
        savAcc.getInterestRate();
        savAcc.applyInterest();
    }
}