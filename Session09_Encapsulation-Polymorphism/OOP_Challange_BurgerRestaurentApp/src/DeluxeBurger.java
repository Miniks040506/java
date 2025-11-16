public class DeluxeBurger extends Burger {
    Item deluxe1;
    Item deluxe2;

    public DeluxeBurger (String name, double price) {
        super(name, price);
    }

    public void addToppings(String extra1, String extra2, String extra3, String deluxe1, String deluxe2) {
        super.addToppings(extra1, extra2, extra3);
        this.deluxe1 = new Item("TOPPING", deluxe1, 0);
        this.deluxe2 = new Item("TOPPING", deluxe2, 0);
    }

    @Override
    public void printItemizedList() {
        super.printItemizedList();
        if (deluxe1 != null) {
            deluxe1.printItem();
        }
        if (deluxe1 != null) {
            deluxe2.printItem();
        }
    }

    @Override
    public double getExtraPrice (String toppingName) {
        return 0;
    }
}
