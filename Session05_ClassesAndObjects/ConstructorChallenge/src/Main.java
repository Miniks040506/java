public class Main {
    public static void main(String[] args) {

        CustomerData customer = new CustomerData("Tim", 1000, "tim@gmail.com");

        System.out.println(customer.getName());
        System.out.println(customer.getCreditLimit());
        System.out.println(customer.getEmail());

        CustomerData secondCustomer = new CustomerData();

        System.out.println(secondCustomer.getName());
        System.out.println(secondCustomer.getCreditLimit());
        System.out.println(secondCustomer.getEmail());

        CustomerData thirdCustomer = new CustomerData("Joe", "joe07@gmail.com");

        System.out.println(thirdCustomer.getName());
        System.out.println(thirdCustomer.getCreditLimit());
        System.out.println(thirdCustomer.getEmail());
    }
}
