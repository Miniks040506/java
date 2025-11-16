public class Main {
    public static void main(String[] args) {
        Car car = new Car();

        car.setMake("Porsche");
        car.setColor("Grey");
        car.setModel("Carrera");

//        car.make = "Vinfast";
//        car.color = "Grey";
//  can't set the value because there are in private access

        System.out.println("Make = " + car.getMake());
        System.out.println("Model = " + car.getModel());
        car.describeCar();

        Car targa = new Car();
        car.setMake("Ferrari");
        car.setColor("Red");
        car.setModel("Targa");
        car.setConvertible(false);
        car.setDoors(2);

        car.describeCar();
    }
}
