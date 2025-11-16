public class Bread extends Food{

    private Food flavor;

    public Bread(String name, double price) {
        super("BREAD", name, price);
    }

    @Override
    public String getName() {
        if (flavor != null) {
            return flavor.getName() + " " + super.getName();
        }
        return "ORIGINAL " + super.getName();
    }

    @Override
    public double getAdjustedPrice() {
        return super.getAdjustedPrice() +
                ((flavor == null) ? 0 : flavor.getAdjustedPrice());
    }

    public double getExtraFlavorPrice(String flavor) {
        return switch (flavor.toUpperCase()) {
            case "CHOCOLATE", "STRAWBERRY" -> 1.0;
            case "COCONUT FLAKES", "ALMOND" -> 1.5;
            default -> 0.5;
        };
    }

    public void addFlavor(String flavor) {
        this.flavor = new Food("FLAVOR", flavor, getExtraFlavorPrice(flavor));
    }

    public void printItemizedList() {
        printItem("ORIGINAL BREAD", getBasePrice());
        if (flavor != null) {
            flavor.printItem();
        }
    }

    @Override
    public void printItem() {
        printItemizedList();
        System.out.println("-".repeat(65));
        super.printItem();
    }
}
