public class Food {

    private String type;
    private String name;
    private double price;
    private String size = "MEDIUM";

    public Food(String type, String name, double price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static Food getFood (String type, String name, double price) {

        return switch (type.toUpperCase()) {
            case "CAKE" -> new Cake(name, price);
            case "COOKIE" -> new Cookie();
            case "BREAK" -> new Bread(name, price);
            default -> new Food(type, name, price);
        };
    }

    public String getName() {

        if (type.equals("DRINK") || type.equals("COOKIE")) {
            return size + " " + name;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return price;
    }

    public double getAdjustedPrice() {
        if (type.equals("DRINK") || type.equals("COOKIE")) {
            return switch (size.toUpperCase()) {
                case "SMALL" -> getBasePrice() * 0.8;
                case "LARGE" -> getBasePrice() * 1.2;
                default -> getBasePrice();
            };
        }
        return getBasePrice();
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static void printItem(String name, double price) {
        System.out.printf("%45s:%8.2f $%n", name.toUpperCase(), price);
    }

    public void printItem() {
        printItem(getName(), getAdjustedPrice());
    }
}
