public class BankAccount {

    private String accountNumber;
    private double balance;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    public BankAccount () {
        this ("040506", 250.00, "Miniks", "miniks456@gmail.com", "0983717453");
        System.out.println("Empty constructor called");
    }

    public BankAccount(String customerName, String customerEmail, String customerPhone) {
        this ("00300806", 300.00, customerName, customerEmail, customerPhone);
//        this.customerName = customerName;
//        this.customerPhone = customerPhone;
//        this.customerEmail = customerEmail;
    }

    public  BankAccount (String accountNumber, double balance, String customerName, String customerEmail, String customerPhone) {
        System.out.println("Account constructor with parameters called");
        this.accountNumber = accountNumber;
        setBalance(balance);
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public void depositFunds(double depositAmount) {
        balance += depositAmount;
        System.out.println("Deposit of $" + depositAmount +
                " make. New balance is $" + this.balance); // ko can this. cx dc
    }

    public void withdrawFunds (double withdrawAmount) {
        if (balance - withdrawAmount < 0) {
            System.out.println("Insufficient Funds! You only have $"
                    + balance + " in your account");
        }
        else {
            balance -= withdrawAmount;
            System.out.println("Withdraw of $" + withdrawAmount + " processes, remaining balance = $" + balance);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
