package view;

public class Menu {
    
    public static void showMenu(Object[] options) {
        for (int i = 0; i < options.length-1; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("Others-Quit.");
        System.out.print("Your options from 1 - " + (options.length-1) + ": ");        
    }
    
}
