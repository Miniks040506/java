package Presentation;

import BusinessObject.Management;
import Core.Interfaces.IBrandDAO;
import Core.Interfaces.ICarDAO;
import Utilities.DataInput;
import java.util.Arrays;
import java.util.List;

public class Menu {

    public static boolean isSave = true;

    //------------------------------------------------
    public static void print(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select:")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }
        });
    }

    //------------------------------------------------
    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    //------------------------------------------------
    public static void manageMenu(IBrandDAO serviceBrand, ICarDAO serviceCar) throws Exception {
        Management service = new Management(serviceCar, serviceBrand);
        service.processMenu();
    }

}
