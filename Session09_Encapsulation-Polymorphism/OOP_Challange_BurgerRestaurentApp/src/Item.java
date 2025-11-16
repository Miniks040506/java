public class Item {

    private String type;
    private String name;
    private double price;
    private String size = "MEDIUM";

    public Item(String type, String name, double price) {
        this.type = type.toUpperCase();
        this.name = name.toUpperCase();
        this.price = price;
    }

    public String getName() {

        if (type.equals("SIDE") || type.equals("DRINK")) {
            return size + " " + name;
        }

        return name;
    }

    public double getBasePrice() {
        return price;
    }

    public double getAdjustedPrice() {
        return switch (size.toUpperCase()) {
            case "SMALL" -> getBasePrice() * 0.7;
            case "LARGE" -> getBasePrice() + 1.3;
            default -> getBasePrice();
        };
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static void printItem(String name, double price) {
        System.out.printf("%20s:%7.2f%n", name.toUpperCase(), price);
    }

    public void printItem() {
        printItem(getName(), getAdjustedPrice());
    }

}
