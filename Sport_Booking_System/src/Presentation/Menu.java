package Presentation;

import BusinessObject.Management;
import Core.Interfaces.IBookingDAO;
import Utilities.DataInput;
import java.util.Arrays;
import java.util.List;
import Core.Interfaces.IFacilityDAO;

public class Menu {

    public static boolean isSave = true;

    //------------------------------------------------
    public static void print(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select:")) {
                System.out.println("===========================================");
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
    
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder capitalizeWords = new StringBuilder();
        String[] words = str.split("\\s+");
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            String capitalizeWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            if (capitalizeWords.length() > 0) {
                capitalizeWords.append(" ");
            }
            capitalizeWords.append(capitalizeWord);
        }
        return capitalizeWords.toString();
    }

    //------------------------------------------------
    public static void manageMenu(IBookingDAO bookingService, IFacilityDAO facilityService)
            throws Exception {
        Management service = new Management(bookingService, facilityService);
        service.processMenu();
    }
}
