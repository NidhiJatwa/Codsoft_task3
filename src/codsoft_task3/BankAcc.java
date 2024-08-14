
package codsoft_task3;

public class BankAcc {
     double balance;

    public BankAcc(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized double checkBalance() {
        return balance;
    }
}


