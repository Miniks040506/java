
import java.util.*;
import java.io.*;
import controller.*;
import java.time.LocalDateTime;
import model.*;
import util.Validator;
import view.View;

public class Main {
    
//    MountainController mountainCtrl = new MountainController();
    FacilityController facilityCtrl = new FacilityController();
    BookingController bookingCtrl = new BookingController();
    
    
    
    View view = new View(new Scanner(System.in));
        
    public static void main(String[] args) {        
        Main main = new Main();
        while (true) {
            int choice = main.view.showMainMenu();
            switch (choice) {
                case 1:
                    main.exportCSVToTxt();
                    break;
                case 2:
                    main.updateFacility();
                    break;
                case 3:
                    main.viewFacilitiesServices();
                    break;
                case 4:
                    main.bookFacilityService();
                    break;
                case 5:
                    main.viewBookingByDate();
                    break;
                case 6:
                    main.cancelBooking();
                    break;
                case 7:
                    main.reportRevenueByMonth();
                    break;
                case 8:
                    main.reportUsage();
                    break;
                case 9:
                    main.save();
                    break;
                default:
                    System.out.println("Exitting system...");
                    return;
            }
        }
    }
    
    private void exportCSVToTxt(){
        facilityCtrl.load();
    }
    
    private void updateFacility(){
       try
        {
            String idname;
            //Check whether code exist
            while(true){
                idname = view.readString("Enter Id or Name to update: ");                
                if(!facilityCtrl.isExistId(idname) && !facilityCtrl.isExistName(idname)){
                    view.showMessage("No Facility or Service found in the datbase!");
                    return;
                }else{
                    break;
                }                
            }
            
            Facility facility;
            if(facilityCtrl.isExistId(idname)){
                facility = facilityCtrl.getById(idname);
            }else{
                facility = facilityCtrl.getByName(idname);
            }
            System.out.println("Current info: ");
            System.out.println(facility);
            
            while(true){
                int choice = view.showUpdateMenu();
                switch (choice) {
                    case 1:
                        String location;
                        do {
                                location = view.readString("Enter new location: ");
                                if (location.isEmpty()) {
                                    System.out.println("Invalid Location!");
                                }
                            } while (location.isEmpty());
                        facility.setLocation(location);
                        break;
                    case 2:
                        int capacity = view.readInt("Enter new capacity: ",1);
                        facility.setCapacity(capacity);
                        break;
                    case 3:
                        String start;
                        while (true) {
                            start = view.readString("Enter new start (MM/dd/yyyy HH:mm): ");
                            if (start.isEmpty() || !Validator.isValidDate(start)) {
                                System.out.println("Invalid format or missing!");
                            } else {
                                break;
                            }
                        }

                        String end;
                        while (true) {
                            end = view.readString("Enter new end (MM/dd/yyyy HH:mm): ");
                            if (end.isEmpty() || !Validator.isValidDate(end)) {
                                System.out.println("Invalid format or missing!");
                            } else if (!Validator.isValidRange(start, end)) {
                                System.out.println("End time must be after start time!");
                            } else {
                                break;
                            }
                        }
                        facility.setAvailabilityStart(start);
                        facility.setAvailabilityEnd(end);
                        break;
                    case 4:
                        System.out.println("Saving...");
                        // Create saving function here
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
            }
                       
        }
        catch (Exception ex){
            System.out.println("f2 fails");
        }
    }
    
    private void viewFacilitiesServices(){
        if(facilityCtrl.getAll().size() == 0){
            System.out.println("Facilities & Services list is currently empty, not loaded yet");
            return;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s | %-15s | %-25s | %-9s | %-30s\n",
                "Facility Name", "Type", "Location", "Capacity", "Availability");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        for (Facility f : facilityCtrl.getAll()) {
            System.out.printf("%-25s | %-15s | %-25s | %-9s | %-30s\n",
                    f.getName(),
                    f.getType(),
                    f.getLocation(),
                    f.getCapacity(),
                    f.getAvailabilityStart()+"-"+f.getAvailabilityEnd().substring(f.getAvailabilityEnd().indexOf(" ")).trim()
            );
                    
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    }
    
    private void bookFacilityService(){
        try{
            String player;
            while(true){
                player = view.readString("Enter player's name: ");                
                if(!Validator.isValidPlayer(player)){
                    view.showMessage("Invalid name!");
                    return;
                }else{
                    break;
                }

                }

            String facilityName;
            //Check whether facility exist
            while(true){
                facilityName = view.readString("Enter facility's name: ");                
                if(!facilityCtrl.isExistName(facilityName)){
                    view.showMessage("Facility does not exist!");
                    return;
                }else{
                    break;
                }
            }
            Facility facility = facilityCtrl.getByName(facilityName);
            
            System.out.println("Available slot: "+facility.getAvailabilityStart()+"-"+facility.getAvailabilityEnd().substring(facility.getAvailabilityEnd().indexOf(" ")).trim());
            String dateTime;
            while(true){
                dateTime = view.readString("Enter booking time(MM/dd/yyyy HH:mm): ");
                if(!Validator.isValidDate(dateTime)){
                    System.out.println("please try again!");
                }else if(!Validator.isValidBookingTime(facility.getAvailabilityStart(), facility.getAvailabilityEnd(), dateTime)){
                    System.out.println("Your schedule must match an available time slot of the chosen facility");
                }
                else{
                    break;
                }
            }
            int duration;
            while(true){
                duration = view.readInt("Enter duration: ",1);                
                if(!Validator.isValidDuration(duration)){
                    view.showMessage("Invalid duration!");
                    return;
                }else{
                    break;
                }

            }
            bookingCtrl.addBooking(new Booking(player,facilityName,dateTime,duration));
            bookingCtrl.save();
            System.out.println("Booking completed!");
                                    
        }catch(Exception ex){
            System.out.println("f4 fails: "+ ex.getMessage());
        }

    }
    
    private void viewBookingByDate(){
        try{
            while(true){
                String date;
                while(true){
                    date = view.readString("Enter a date(MM/dd/yyyy): ");                
                    if(date.isEmpty()){
                        date = Validator.getToday();
                        break;
                    }else if(!Validator.isValidDay(date)){
                        view.showMessage("Invalid day format!");
                        return;
                    }
                    else{
                        break;
                    }
                 }

                List<Booking> bookingByDate = bookingCtrl.getByDate(date);
                

                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("Booking on: " + date);
                if(bookingByDate.size() == 0){
                    System.out.println("There are currently no courts or services booked!");
                    return;
                }else{
                    bookingByDate.sort(Comparator.comparing(Booking::getDateTime));
                }
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.printf("%-12s | %-28s | %-28s | %-9s\n",
                        "Time", "Facility", "User", "Duration");
                System.out.println("---------------------------------------------------------------------------------------");

                for (Booking b : bookingByDate ) {
                    System.out.printf("%-12s | %-28s | %-28s | %-9s\n",
                            b.getDateTime().substring(b.getDateTime().indexOf(" ")),
                            b.getFacilityName(),
                            b.getPlayer(),
                            b.getDuration()                       
                    );

                }
                System.out.println("---------------------------------------------------------------------------------------");
                String choice = view.readString("Continue?(Y/N): ").toLowerCase();
                if(choice.equals("y")){
                    continue;
                }else if(choice.equals("n")){
                    return;
                }else{
                    System.out.println("Invalid choice!. Returning to the main menu...");
                    return;
                }
            }
            
                                    
        }catch(Exception ex){
            System.out.println("f5 fails: "+ ex.getMessage());
        }
    }

    private void cancelBooking(){
        try{
            String bookingId;
            
            //Check whether code exist          
            bookingId = view.readString("Enter booking id(PlayerName + Date + Facility): ");
            Booking booking = bookingCtrl.getById(bookingId);
                              
            
            if (booking == null) {
                System.out.println("Booking for ID '" + bookingId + "' could not be found");
                return;
            }

            // Kiểm tra thời gian
            LocalDateTime bookingTime = Validator.parse(booking.getDateTime());
            if (bookingTime.isBefore(LocalDateTime.now())) {
                System.out.println("This booking (ID: " + bookingId + ") cannot be canceled because it has already passed.");
                return;
            }

            // Hiển thị thông tin booking
            System.out.println("Booking information:");
            System.out.println("+ Booking ID : " + booking.getId());
            System.out.println("+ Player name: " + booking.getPlayer());
            System.out.println("+ Facility   : " + booking.getFacilityName());
            System.out.println("+ Date/Time  : " + booking.getDateTime());
            System.out.println("+ Duration   : " + booking.getDuration());
            System.out.println("------------------------------------------------------");

            Scanner sc = new Scanner(System.in);
            System.out.print("Do you really want to cancel this booking? [Y/N]: ");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                bookingCtrl.cancelBooking(booking);            
                bookingCtrl.save();
                System.out.println("The booking ID " + bookingId + " has been successfully canceled.");
            } else {
                System.out.println("Cancel operation aborted.");
            }                            
        }catch(Exception ex){
            System.out.println("f6 fails: "+ ex.getMessage());
        }
    }
    
    private void reportRevenueByMonth(){
        String monthYear;
        while(true){
            monthYear = view.readString("Enter a date(MM/yyyy): ");                
                if(!Validator.isValidMonth(monthYear)){
                    view.showMessage("Invalid month format!");
                    return;
                }
                else{
                    break;
                }
        }
        System.out.println("Monthly Revenue Report - " + monthYear);
        bookingCtrl.reportRevenue(bookingCtrl.getByMonth(monthYear));
        
        
    }
    
    private void reportUsage(){
        bookingCtrl.reportServiceUsage(bookingCtrl.getByStatus());
    }
    
    private void save(){
        bookingCtrl.save();
    }
}

