package view;

import java.util.List;
import java.util.Scanner;
import model.*;

public class View {

    private final Scanner sc;

    public View(Scanner sc) {
        this.sc = sc;
    }

    public int showMainMenu() {

        System.out.println("===== Sports Booking System =====");

        String[] options = {"Import Facility from CSV file",
            "Update Facility Information",
            "View Facilities & Servives",
            "Book a Facility / Services",
            "View Today's Bookings",
            "Cancel a Booking",
            "Monthly Revenue Report",
            "Service Usage Statistics",
            "Save All Data",
            "Others-Quit"
        };

        Menu.showMenu(options);
        return Integer.parseInt(sc.nextLine());
    }
    
    public int showUpdateMenu(){
        System.out.println("==== Choose a field to update ====");

        String[] options = {"Location",
            "Capacity",
            "Availability Schedule",
            "Save",
            "Others-Quit"
        };

        Menu.showMenu(options);
        return Integer.parseInt(sc.nextLine());
    }

//    public void showMountains(List mountains) {
//        if (mountains.isEmpty()) {
//            System.out.println("No mountains loaded.");
//            return;
//        }
//        for (Object m : mountains) {
//            System.out.println(m);
//        }
//    }
    
//    public String choiceMountains(List<Mountain> mountains) {
//        if (mountains.isEmpty()) {
//            System.out.println("No mountains loaded.");
//            return "";
//        }
//        for (Object m : mountains) {
//            System.out.println(m);
//        }
//        int choice = readInt("Select mountain: ",1,mountains.size());
//        return mountains.get(choice-1).getCode();
//    }
//    public void showStudents(List<Student> students) {
//        if (students.isEmpty()) {
//            System.out.println("No students loaded.");
//            return;
//        }
//        for (Object m : students) {
//            System.out.println(m);
//        }
//    }

    public int readInt(String prompt, int min) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val < min) {
                    throw new Exception();
                }
                return val;
            } catch (Exception e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }
    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(sc.nextLine().trim());
                
                return val;
            } catch (Exception e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public String readStringAllowEmpty(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
