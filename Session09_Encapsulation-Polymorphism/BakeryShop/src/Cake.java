public class Cake extends Food {

    private Food flavor;
    private Food topping;

    public Cake(String name, double price) {
        super("CAKE", name, price);
    }

    @Override
    public String getName() {
        if (topping != null && flavor != null) {
            return flavor.getName() + " " + super.getName() + " with " + topping.getName();
        }
        else if (topping != null) {
            return super.getName() + " with " + topping.getName();
        }
        else if (flavor != null) {
            return flavor.getName() + " " + super.getName();
        }
        return super.getName();
    }

    @Override
    public double getAdjustedPrice() {
        return super.getAdjustedPrice() +
                ((flavor == null) ? 0 : flavor.getAdjustedPrice()) +
                ((topping == null) ? 0 : topping.getAdjustedPrice());
    }

    public double getExtraPrice(String topping) {
        return switch (topping.toUpperCase()) {
            case "COCONUT FLAKES", "CHOCOLATE SHAVINGS" -> 1.0;
            case "ALMOND", "WALNUTS" -> 1.5;
            case "ORIGINAL" -> 0;
            default -> 0.5;
        };
    }

    public double getExtraFlavorPrice(String flavor) {
        return switch (flavor.toUpperCase()) {
            case "CHOCOLATE", "CHEESE" -> 1.0;
            case "BLUEBERRY", "STRAWBERRY", "LIME" -> 1.5;
            default -> 0.5;
        };
    }

    public void addTopping(String topping) {
        this.topping = new Food("TOPPING", topping, getExtraPrice(topping));
    }

    public void addFlavor(String flavor) {
        this.flavor = new Food("FLAVOR", flavor, getExtraFlavorPrice(flavor));
    }

    public void printItemizedList() {
        printItem("ORIGINAL CAKE", getBasePrice());
        if (flavor != null) {
            flavor.printItem();
        }
        if (topping != null) {
            topping.printItem();
        }
    }

    @Override
    public void printItem() {
        printItemizedList();
        System.out.println("-".repeat(65));
        super.printItem();
    }
}
