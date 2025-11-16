public class Main {
    public static void main(String[] args) {
        //BankAccount account = new BankAccount("0430050806", 1000.00, "Miniks", "Miniks0430050806@hmail.com", "0983717453");
        BankAccount account = new BankAccount();

        System.out.println("Account number = " + account.getAccountNumber());
        System.out.println("Balance = " + account.getBalance());
//        account.setAccountNumber("0430050806");
//        account.setBalance(1000.00);
//        account.setCustomerName("Miniks");
//        account.setCustomerName("Miniks0430050806@hmail.com");
//        account.setCustomerPhone("0983717453");

        account.withdrawFunds(100);
        account.depositFunds(250);
        account.withdrawFunds(50);

        account.withdrawFunds(200);
        account.depositFunds(100);
        account.withdrawFunds(45.55);
        account.withdrawFunds(54.46);

        BankAccount qtAcount = new BankAccount("Qthi", "tvqt300806@gmail.com", "0984123421");
        System.out.println("AccountNo: " + qtAcount.getAccountNumber() + "; name " + qtAcount.getCustomerName());

    }
}
