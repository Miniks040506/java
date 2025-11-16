public class Cookie extends Food {

    private Food cookieType;

    public Cookie() {
        super("COOKIE", "COOKIE", 3);
    }

    @Override
    public String getName() {
        if (cookieType != null) {
            setName(cookieType.getName() + " COOKIE");
        }
        return super.getName();
    }

    @Override
    public double getAdjustedPrice() {
        return super.getAdjustedPrice() +
                ((cookieType == null) ? 0 : cookieType.getAdjustedPrice());
    }

    public double getExtraPrice(String cookieType) {
        return switch (cookieType.toUpperCase()) {
            case "BUTTER", "CHOCOLATE" -> 1.0;
            case "ALMOND", "WALNUTS" -> 1.5;
            default -> 0.5;
        };
    }

    public void chooseCookieType(String cookieType) {
        this.cookieType = new Food("BONUS", cookieType, getExtraPrice(cookieType));
    }

    public void printItemizedList() {
        printItem("ORIGINAL COOKIE", getBasePrice());
        if (cookieType != null) {
            cookieType.printItem();
        }
    }

    @Override
    public void printItem() {
        printItemizedList();
        System.out.println("-".repeat(65));
        super.printItem();
    }
}
