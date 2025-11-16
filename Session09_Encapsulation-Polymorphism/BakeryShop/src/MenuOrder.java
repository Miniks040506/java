import java.util.Scanner;

public class MenuOrder {
    private Drink drink;
    private Food food;

    public MenuOrder() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Choose food type (cake, cookie, bread): ");
        String foodType = sc.nextLine().trim();
        String foodName = foodType;

        if (foodType.equals("cake") || foodType.equals("bread")) {
            System.out.print("Enter the name of your " + foodType + ": ");
            foodName = sc.nextLine().trim();
        }

        food = Food.getFood(foodType, foodName, 7.00);

        if (food instanceof Cake cake) {
            System.out.print("Add cake flavor (e.g., strawberry, cheese, lime): ");
            String flavor = sc.nextLine().trim();
            cake.addFlavor(flavor);

            System.out.print("Add cake topping (e.g., almond, chocolate shavings): ");
            String topping = sc.nextLine().trim();
            cake.addTopping(topping);
        } else if (food instanceof Cookie cookie) {
            System.out.print("Choose cookie type (e.g., butter, chocolate): ");
            String type = sc.nextLine().trim();
            cookie.chooseCookieType(type);

            System.out.print("Enter cookie size (small, medium, large): ");
            String cookieSize = sc.nextLine().trim();
            cookie.setSize(cookieSize);
        } else if (food instanceof Bread bread) {
            System.out.print("Add bread flavor (e.g., chocolate, almond): ");
            String flavor = sc.nextLine().trim();
            bread.addFlavor(flavor);
        }

        System.out.print("Enter drink name (e.g., milk, soda): ");
        String drinkName = sc.nextLine().trim();

        System.out.print("Enter drink size (small, medium, large): ");
        String drinkSize = sc.nextLine().trim();

        System.out.print("Enter drink status (HOT / COLD): ");
        String drinkStatus = sc.nextLine().trim().toUpperCase();

        drink = new Drink(drinkName, 4.00, drinkStatus);
        drink.setSize(drinkSize);

    }

    public double getTotalPrice() {
        return food.getAdjustedPrice() + drink.getAdjustedPrice();
    }

    public void printReceipt() {
        System.out.println("=".repeat(65));
        food.printItem();
        drink.printItem();
        System.out.println("-".repeat(65));
        System.out.printf("%45s:%8.2f $\n", "TOTAL", getTotalPrice());
    }
}
