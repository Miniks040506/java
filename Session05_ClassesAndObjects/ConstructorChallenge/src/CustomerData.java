public class CustomerData {

    private String name;
    private double creditLimit;
    private String email;

    public CustomerData() {
        this("Nobody", "nobody@gmail.com");
    }

    public CustomerData(String name, String email) {
        this(name, 1500, email);
    }

    public CustomerData(String name, double creditLimit, String email) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmail() {
        return email;
    }
}
