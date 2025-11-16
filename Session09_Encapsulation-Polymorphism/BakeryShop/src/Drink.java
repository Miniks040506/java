public class Drink extends Food{

    private String status;

    public Drink(String name, double price, String status) {
        super("DRINK", name, price);
        this.status = status;
    }

    @Override
    public String getName() {
        if (status != null) {
            return status + " " + super.getName();
        }
        return super.getName();
    }

    @Override
    public double getAdjustedPrice() {
        return super.getAdjustedPrice();
    }

    @Override
    public void printItem() {
        super.printItem();
    }
}
