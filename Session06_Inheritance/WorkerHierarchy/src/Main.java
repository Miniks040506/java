public class Main {
    public static void main(String[] args) {
        Employee tim = new Employee("Tim", "11/11/1985",
                "01/01/2020");
        System.out.println(tim);
        System.out.println(tim.name + " Age = " + tim.getAge());
        System.out.println(tim.name + " Paycheck = $" + tim.collectPay());

        SalariedEmployee joe = new SalariedEmployee("Joe", "11/11/1990",
                "03/03/2020", 35000);
        System.out.println(joe);
        System.out.println(joe.name + " Paycheck = $" + joe.collectPay());

        joe.retire();
        System.out.println(joe);
        System.out.println("Joe's Pension check = $" + joe.collectPay());

        HourlyEmployee mary = new HourlyEmployee("Mary", "05/05/1970",
                "03/03/2021", 15);
        System.out.println(mary);
        System.out.println(mary.name + " Paycheck = $" + mary.collectPay());
        System.out.println(mary.name + " Holiday Pay = $" + mary.getDoublePay());
    }
}
