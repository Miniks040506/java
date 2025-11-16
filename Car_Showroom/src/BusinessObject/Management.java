package BusinessObject;

import Core.Entities.Brand;
import Core.Entities.Car;
import Core.Interfaces.IBrandDAO;
import Core.Interfaces.ICarDAO;
import Presentation.Menu;
import Utilities.DataInput;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Management {

    ICarDAO carDAO;
    IBrandDAO brandDAO;

    public Management(ICarDAO carDAO, IBrandDAO brandDAO) throws Exception {
        this.carDAO = carDAO;
        this.brandDAO = brandDAO;
    }

    public void processMenu() {
        int choice;
        try {
            do {
                System.out.println("\n\n|------------------------ MAIN  MENU --------------------------|");
                Menu.print("1.List all brands|2.Add a new brand|"
                        + "3.Search for a brand by ID|4.Update a brand by ID|"
                        + "5.List all brands with prices less than or equal to an input value|"
                        + "6.List all cars in ascending order of brand names|"
                        + "7.Search cars by partial brand name match|"
                        + "8.Add a new car|9.Remove a car by ID|"
                        + "10.Update a car by ID|11.List all cars by a specific color|"
                        + "12.Save data to files|13.Quit program|"
                        + "14.List all brands that contain part of sound string|Select: ");
                choice = Menu.getUserChoice();
                switch (choice) {
                    case 1:
                        printBrandMenu(brandDAO.getBrands());
                        break;
                    case 2:
                        addNewBrand();
                        break;
                    case 3:
                        showBrandInfo();
                        break;
                    case 4:
                        updateBrand();
                        break;
                    case 5:
                        printBrandLessThanPrice();
                        break;
                    case 6:
                        printCarMenu(sortCarList(carDAO.getCars()));
                        break;
                    case 7:
                        printCarMatchBrandName();
                        break;
                    case 8:
                        addNewCar();
                        break;
                    case 9:
                        deleteCar();
                        break;
                    case 10:
                        updateCar();
                        break;
                    case 11:
                        printCarMatchColor();
                        break;
                    case 12:
                        exportToFile();
                        Menu.isSave = true;
                        break;
                    case 13:
                        if (!Menu.isSave) {
                            exportToFile();
                            Menu.isSave = true;
                        }
                        System.out.println("Thank you for using Michael BMW Car Showroom Management System!");
                        break;
                    case 14:
                        printBrandBySound();
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } while (choice != 13);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Car inputCar() throws Exception {
        String carID = DataInput.getString("Enter the car id:");
        if (carDAO.getCarById(carID) != null) {
            System.out.println(">>The car already exists.");
            return null;
        }
        printBrandMenu(brandDAO.getBrands());
        String brandID = DataInput.getString("Enter the brand id(match with the id in the table):");
        if (brandDAO.getBrandById(brandID) == null) {
            System.out.println(">>The brand is not exists.");
            return null;
        }
        String color = DataInput.getString("Enter the car color:");
        String frameID = DataInput.getString("Enter the frame id:");
        String engineID = DataInput.getString("Enter the engine id:");
        return new Car(carID, brandDAO.getBrandById(brandID), color, frameID, engineID);
    }

    //------------------------------------------------
    public void setNewCarInfo(Car car) throws Exception {
        String color = DataInput.getString("Enter new car color:");
        if (!color.isEmpty()) {
            car.setColor(color);
        }
        String frameID = DataInput.getString("Enter new frame id:");
        if (!frameID.isEmpty()) {
            car.setFrameID(frameID);
        }
        String engineID = DataInput.getString("Enter new engine id:");
        if (!engineID.isEmpty()) {
            car.setEngineID(engineID);
        }
    }

    // get car id to check
    public Car getCar() throws Exception {
        String id = DataInput.getString("Enter car id:");
        Car car = carDAO.getCarById(id);
        return car;
    }

    //add new car to list
    public void addNewCar() {
        try {
            Car car = inputCar();
            if (car == null) {
                return;
            }
            carDAO.addCar(car);
            Menu.isSave = false;
            System.out.println(">>The car has added successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //update car info
    public void updateCar() {
        try {
            Car car = getCar();
            if (car == null) {
                System.out.println(">>This car has not registered yet.");
                return;
            }
            System.out.println(">>Enter new information to update or press 'ENTER' to skip.");
            setNewCarInfo(car);
            carDAO.updateCar(car);
            Menu.isSave = false;
            System.out.println(">>The car has updated successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //delete car
    public void deleteCar() {
        try {
            Car car = getCar();
            if (car == null) {
                System.out.println(">>This car has not registered yet.");
                return;
            }
            //showCarInfo(car);
            //boolean isDelete = DataInput.getYesNo("Are you sure you want to delete this car? (Y/N): ");
            carDAO.removeCar(car);
            Menu.isSave = false;
            System.out.println(">>The car has been successfully deleted.");

        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }
    
    // filter car list that contains words
    public List<Car> searchByPartialName(String value) throws Exception {
        Predicate<Car> predicate = p -> p.getBrand().getBrandName().toLowerCase()
                .contains(value.toLowerCase());
        List<Car> cars = carDAO.search(predicate);
        return cars;
    }

    // print car list that brand name contains words
    public void printCarMatchBrandName() throws Exception {
        String searchBrandName = DataInput.getString("Enter by partial brand name:");
        List<Car> matchList = searchByPartialName(searchBrandName);
        printCarMenu(matchList);
    }

    // filter car list by color
    public List<Car> filterCarByColor(String value) throws Exception {
        Predicate<Car> filterColor = p -> p.getColor().equalsIgnoreCase(value);
        List<Car> cars = carDAO.search(filterColor);
        return cars;
    }

    // print car list that match input color
    public void printCarMatchColor() throws Exception {
        String searchColor = DataInput.getString("Enter color you want to list car:");
        List<Car> matchList = filterCarByColor(searchColor);
        printCarMenu(matchList);
    }

    //sort car list
    public List<Car> sortCarList(List<Car> carSortedList) {
        List<Car> sorted = carSortedList.stream()
                .sorted(Comparator
                        .comparing((Car c) -> c.getBrand().getBrandName(), String.CASE_INSENSITIVE_ORDER)
                        .thenComparing((Car c) -> c.getBrand().getPrice(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return sorted;
    }

    //print car menu
    public void printCarMenu(List<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("No car available.");
            return;
        }
        System.out.println("\n|==================================================== CARS LIST "
                + "====================================================|");
        System.out.println(String.format("| %-6s | %-8s | %-30s | %-16s | %-8s | %-7s | %-8s | %-9s |",
                "CAR ID", "BRAND ID", "BRAND NAME", "SOUND BRAND", "PRICE",
                "COLOR", "FRAME ID", "ENGINE ID"));
        System.out.println("|====================================================="
                + "==============================================================|");
        for (Car car : cars) {
            System.out.println(String.format("| %-6s | %-8s | %-30s | %-16s | %-8s | %-7s | %-8s | %-9s |",
                    car.getCarID(), car.getBrand().getBrandID(), car.getBrand().getBrandName(),
                    car.getBrand().getSoundBrand(), car.getBrand().getPrice() + "B", car.getColor(),
                    car.getFrameID(), car.getEngineID()));
            System.out.println("|-----------------------------------------------------"
                    + "--------------------------------------------------------------|");
        }
        System.out.println("\nTOTAL: " + cars.size() + " brand(s).");
    }

    public void exportToFile() throws Exception {
        carDAO.saveCarListToFile();
        brandDAO.saveBrandListToFile();
        System.out.println("Data has been successfully saved to `cars.txt` and `brands.txt`.");
    }
    
    
    //1.	Thêm chức năng mới tìm và hiển thị danh sách các 
//    brand mà sound brand có chứa chuỗi S (S là giá trị ki
//    ểu chuỗi được nhập từ bàn phím, không phân biệt
//            HOA-thường) và cho biết có bao nhiêu brand đã tìm thấy
    public List<Brand> filterBrandBySound(String value) throws Exception {
        Predicate<Brand> predicate = s -> s.getSoundBrand().toLowerCase().contains(value.toLowerCase());
        List<Brand> brands = brandDAO.search(predicate);
        return brands;
    }
    
    public void printBrandBySound() throws Exception {
        String search = DataInput.getString("Enter string to filter by sound: ");
        List<Brand> matchList = filterBrandBySound(search);
        printBrandMenu(matchList);
    }
    

    public Brand inputBrand() throws Exception {
        String brandID = DataInput.getString("Enter the brand id:");
        if (brandDAO.getBrandById(brandID) != null) {
            System.out.println(">>The brand already exists.");
            return null;
        }
        String brandName = DataInput.getString("Enter the brand name:");
        String soundBrand = DataInput.getString("Enter the sound manufacturer:");
        double price = DataInput.getDoubleNumber("Enter the brand price:");
        return new Brand(brandID, brandName, soundBrand, price);
    }

    //------------------------------------------------
    public void setNewBrandInfo(Brand brand) throws Exception {
        String brandName = DataInput.getString("Enter new brand name:");
        if (!brandName.isEmpty()) {
            brand.setBrandName(brandName);
        }
        String soundBrand = DataInput.getString("Enter new sound brand:");
        if (!soundBrand.isEmpty()) {
            brand.setSoundBrand(soundBrand);
        }
        double price = DataInput.getDoubleNumber("Enter new brand price:");
        if (price > 0) {
            brand.setPrice(price);
        }
    }

    //------------------------------------------------
    public void addNewBrand() {
        try {
            Brand brand = inputBrand();
            if (brand == null) {
                return;
            }
            brandDAO.addBrand(brand);
            Menu.isSave = false;
            System.out.println(">>The brand has added successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //------------------------------------------------
    public Brand getBrand() throws Exception {
        String id = DataInput.getString("Enter brand id:");
        Brand brand = brandDAO.getBrandById(id);
        return brand;
    }

    //------------------------------------------------
    public void updateBrand() {
        try {
            Brand brand = getBrand();
            if (brand == null) {
                System.out.println(">>This brand does not exist!");
                return;
            }
            System.out.println(">>Enter new information to update or press 'ENTER' to skip.");
            setNewBrandInfo(brand);
            brandDAO.updateBrand(brand);
            Menu.isSave = false;
            System.out.println(">>The brand has updated successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    public List<Brand> filterBrandLessThanPrice(double value) throws Exception {
        Predicate<Brand> predicate = s -> s.getPrice() <= value;
        List<Brand> students = brandDAO.search(predicate);
        return students;
    }

    public void printBrandLessThanPrice() throws Exception {
        double searchName = DataInput.getDoubleNumber("Enter price to filter: ");
        List<Brand> matchList = filterBrandLessThanPrice(searchName);
        printBrandMenu(matchList);
    }

    //------------------------------------------------
    public void printBrandMenu(List<Brand> brands) {
        if (brands.isEmpty()) {
            System.out.println("No brands available.");
            return;
        }
        System.out.println("\n|============================== BRANDS LIST ==============================|");
        System.out.println(String.format("| %-8s | %-30s | %-16s | %-8s |",
                "BRAND ID", "BRAND NAME", "SOUND BRAND", "PRICE"));
        System.out.println("|=========================================================================|");
        for (Brand brand : brands) {
            System.out.println(String.format("| %-8s | %-30s | %-16s | %-8s |",
                    brand.getBrandID(), brand.getBrandName(),
                    brand.getSoundBrand(), brand.getPrice() + "B"));
            System.out.println("|-------------------------------------------------------------------------|");
        }
        System.out.println("\nTOTAL FOUND: " + brands.size() + " brand(s).");
    }

    //------------------------------------------------
    public void showBrandInfo() throws Exception {
        Brand brand = getBrand();
        if (brand == null) {
            System.out.println("This brand does not exist!");
        } else {
            System.out.println("Brand Details:");
            System.out.println("--------------------------------------------------");
            System.out.println("Brand ID   : " + brand.getBrandID());
            System.out.println("Brand Name : " + brand.getBrandName());
            System.out.println("Sound Brand: " + brand.getSoundBrand());
            System.out.println("Price      : " + brand.getPrice());
            System.out.println("--------------------------------------------------");
        }
    }

}
