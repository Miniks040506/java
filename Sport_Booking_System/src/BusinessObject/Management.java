package BusinessObject;

import Core.Entities.BookingInfo;
import Core.Entities.Facility;
import Core.Interfaces.IBookingDAO;
import Core.Interfaces.IFacilityDAO;
import DataObjects.BookingDAO;
import Presentation.Menu;
import Utilities.DataInput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Management {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //private final DateTimeFormatter FORMATTER_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter TIME_FORMATER = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter DATE_SHOW_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    IBookingDAO bookingDAO;
    IFacilityDAO facilityDAO;

    public Management(IBookingDAO bookingDAO, IFacilityDAO facilityDAO) {
        this.bookingDAO = bookingDAO;
        this.facilityDAO = facilityDAO;
    }

    public void processMenu() {
        int choice;
        boolean stop = true;
        try {
            do {
                System.out.println("\n\n===========================================");
                System.out.println("      KHOE GROUP SPORTS BOOKING SYSTEM");
                System.out.println("===========================================");
                Menu.print("1.Import Facility from CSV file|2.Update Facility Information|"
                        + "3.View Facilities & Services|4.Book a Facility / Service|"
                        + "5.View Today's Bookings|6.Cancel a Booking|"
                        + "7.Monthly Revenue Report|8.Service Usage Statistics|"
                        + "9.Save All Data|10.Quit Program|Select: ");
                choice = Menu.getUserChoice();
                switch (choice) {
                    case 1:
                        importFromCsvFile();
                        break;
                    case 2:
                        updateFacility();
                        break;
                    case 3:
                        ViewFacilitiesAndServices(getValidFacilities());
                        break;
                    case 4:
                        bookingNewFacility();
                        break;
                    case 5:
                        showBookingListByDate(getLocalDate());
                        break;
                    case 6:
                        CancelABooking();
                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:
                        exportToFile();
                        break;
                    case 10:
                        checkSaveBeforeExit();
                        stop = false;
                        break;
//                    case 11:
//                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } while (stop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //1. import valid facilities from "facility_schedule.csv" to "Active_Room_List.txt"
    public void importFromCsvFile() throws Exception {
        List<Facility> validFacilities = getValidFacilities();
        facilityDAO.saveFacilitiesListToFile(validFacilities);
        int failedFacilities = facilityDAO.getFacilities().size() - validFacilities.size();

        System.out.println("Data has been successfully import "
                + "from \"facility_schedule.csv\" to \"Active_Room_List.txt\" file.");
        System.out.println(validFacilities.size() + " rooms successfully loaded.");
        System.out.println(failedFacilities + " entries failed.");
        Menu.isSave = false;
    }

    //get valid facility
    public List<Facility> getValidFacilities() throws Exception {
        return getValidFacilities(facilityDAO.getFacilities());
    }

    //get valid facility with unique name to import to txt file
    public List<Facility> getValidFacilities(List<Facility> facilities) {
        Set<String> uniqueNames = new HashSet<>();
        List<Facility> validFacilities = new ArrayList<>();

        for (Facility facility : facilities) {
            String name = facility.getFacilityName().trim().toLowerCase();
            if (!uniqueNames.contains(name)) {
                validFacilities.add(facility);
                uniqueNames.add(name);
            }
        }
        return validFacilities;
    }

    //Set new facility infomation, if empty(skip), the value of that field is the old one.
    public void setNewFacilityInfo(Facility facility) throws Exception {
        String location = DataInput.getString("Enter new location:");
        if (!location.isEmpty()) {
            facility.setLocation(location);
        }
        String capacity = DataInput.getString("Enter new capacity:");
        if (!capacity.isEmpty()) {
            try {
                facility.setCapacity(Integer.parseInt(capacity));
            } catch (NumberFormatException ex) {
                throw new Exception("Please enter the integer number!");
            }
        }
        String start = DataInput.getString("Enter new available start time(Expected format: yyyy-MM-dd HH:mm):");
        if (!start.isEmpty()) {
            facility.setAvailabilityStart(start);
        }
        String end = DataInput.getString("Enter new available end time(Expected format: yyyy-MM-dd HH:mm):");
        if (!end.isEmpty()) {
            facility.setAvailabilityEnd(end);
        }
    }

    //2.Update Facility infomation
    public void updateFacility() {
        try {
            String input = DataInput.getString("Enter facility id/name:");
            Facility facility = facilityDAO.getFacilityById(input);
            if (facility == null) {
                facility = facilityDAO.getFacilityByName(input);
            } else if (facility == null) {
                System.out.println(">>This facility does not exist!");
                return;
            }
            System.out.println(">>Enter new information to update or press 'ENTER' to skip.");
            setNewFacilityInfo(facility);
            facilityDAO.updateFacility(facility);
            Menu.isSave = false;
            System.out.println(">>The facility has updated and save successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //3.show Facility and Services that valid
    public void ViewFacilitiesAndServices(List<Facility> facilities) {
        if (facilities.isEmpty()) {
            System.out.println("No facilities and services available.");
            return;
        }
        System.out.println("\n|=============================================== FACILITIES & SERVICES "
                + "=================================================|");
        System.out.println(String.format("| %-25s | %-15s | %-30s | %10s | %-25s |",
                "FACILITY NAME", "FACILITY TYPE", "lOCATION", "CAPACITY", "AVAILABLE SCHEDULE"));
        System.out.println("|===========================+=================+==============="
                + "=================+============+===========================|");
        for (Facility facility : facilities) {
            String availability = facility.getAvailabilityStart().format(DATE_SHOW_FORMATTER)
                    + " " + facility.getAvailabilityStart().format(TIME_FORMATER) + "-"
                    + facility.getAvailabilityEnd().format(TIME_FORMATER);
            System.out.println(String.format("| %-25s | %-15s | %-30s | %10d | %-25s |",
                    facility.getFacilityName(), facility.getFacilityType(), facility.getLocation(),
                    facility.getCapacity(), availability));
            System.out.println("|---------------------------+-----------------+-------------"
                    + "-------------------+------------+---------------------------|");
        }
    }

    //4.Booking a facility / service
    public void bookingNewFacility() {
        try {
            BookingInfo bookingInfo = inputBookingInfo();
            if (bookingInfo == null) {
                return;
            }
            bookingDAO.addBookingInfo(bookingInfo);
            Menu.isSave = false;
            System.out.println(">>Booking successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //Input booking infomation and get random booking id code that unique  
    public BookingInfo inputBookingInfo() throws Exception {
        BookingInfo bookingInfo = null;
        String bookingID = "";
        
        String playerName = DataInput.getString("Enter the player name:");
        ViewFacilitiesAndServices(getValidFacilities());
        String facilityName = DataInput.getString("Enter the facility name:");
        String dateTime = DataInput.getString("Enter the booking date & time(Expected format: yyyy-MM-dd HH:mm):");
        int duration = DataInput.getIntegerNumber("Enter the duration:");
        Facility facility = facilityDAO.getFacilityByName(facilityName);
        
        while (BookingDAO.uniqueID.contains(bookingID) || bookingID.isEmpty()) {
            bookingID = generateUniqueBookingCode();
            bookingInfo = new BookingInfo(bookingID, playerName, facility, dateTime, duration);
        }
        return bookingInfo;
    }

    //generate random Booking Id Code
    public String generateUniqueBookingCode() {
        long max = 999_999_999_999_999L;
        long randomCode = ThreadLocalRandom.current().nextLong(0, max + 1);
        return String.format("%015d", randomCode);
    }

    //5.view list of booking for specify date or for today by default if left blank
    public void showBookingListByDate(LocalDate date) {
        List<BookingInfo> bookings = sortByTime(bookingDAO.getBookingsByDate(date));
        if (bookings.isEmpty()) {
            System.out.println("There are currently no courts or services booked !");
            return;
        }
        System.out.println("------------------------------------------------------"
                + "--------------------------------");
        System.out.println("Bookings on " + date.format(DATE_SHOW_FORMATTER));
        System.out.println("------------------------------------------------------"
                + "--------------------------------");
        System.out.println(String.format(" %-10s | %-25s | %-25s | %10s ",
                "TIME", "FACILITY", "USER", "DURATION"));
        System.out.println("------------------------------------------------------"
                + "--------------------------------");
        for (BookingInfo booking : bookings) {
            System.out.println(String.format(" %-10s | %-25s | %-25s | %10s ",
                    booking.getDateTime().format(TIME_FORMATER),
                    booking.getFacility().getFacilityName(),
                    booking.getPlayerName(),
                    booking.getDuration()));
            System.out.println("------------------------------------------------------"
                    + "--------------------------------");
        }
    }

    //sorted by time
    public List<BookingInfo> sortByTime(List<BookingInfo> bookings) {
        return bookings.stream().
                sorted(Comparator.comparing(BookingInfo::getDateTime)).
                collect(Collectors.toList());
    }

    //Input Date
    public LocalDate getLocalDate() throws Exception {
        String date = DataInput.getString("Enter date want to show bookings(format: yyyy-MM-dd):");
        LocalDate chosenDate;
        try {
            if (date.trim().isEmpty()) {
                // if left blank, choose today
                chosenDate = LocalDate.now();
            } else {
                // else, input date
                chosenDate = LocalDate.parse(date, DATE_FORMATER);
            }
        } catch (DateTimeParseException e) {
            throw new Exception(">> Invalid date format. Please use yyyy-MM-dd.");
        }
        return chosenDate;
    }

    //6.cancel a booking by unique id and the time 
    public void CancelABooking() throws Exception {
        try {
            showAllBooking(bookingDAO.getBookings());
            String bookingID = DataInput.getString("Enter the booking ID to cancel:");
            BookingInfo booking = bookingDAO.getBookingInfoById(bookingID);
            if (booking == null) {
                System.out.println(">> Booking for ID " + bookingID + " could not be found.");
                return;
            }
            if (booking.getDateTime().isBefore(LocalDateTime.now())) {
                System.out.println("This booking (ID: " + bookingID + ") cannot be canceled");
                return;
            }
            showMessage(booking);
            boolean isDelete = DataInput.getYesNo("Do you really want to cancel this court booking? [Y/N]: ");
            if (isDelete) {
                bookingDAO.cancelBookingInfo(booking);
                Menu.isSave = false;
                System.out.println(">>The booking ID 071521060920025 has been successfully canceled.");
                //bookingDAO.saveBookingListToFile();
            } else {
                System.out.println(">>The deletion process is cancelled.");
            }
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //show all booking 
    public void showAllBooking(List<BookingInfo> bookings) {
        if (bookings.isEmpty()) {
            System.out.println("No booking available!");
            return;
        }
        System.out.println("\nBOOKING LIST");
        System.out.println("--------------------------------------------------------"
                + "---------------------------------------------------------------");
        System.out.println(String.format(" %-20s | %-25s | %-25s | %-20s | %10s ",
                "BOOKING ID", "PlAYER", "FACILITY", "DATE & TIME", "DURATION"));
        System.out.println("--------------------------------------------------------"
                + "---------------------------------------------------------------");
        for (BookingInfo booking : bookings) {
            System.out.println(String.format(" %-20s | %-25s | %-25s | %-20s | %10s ",
                    booking.getBookingID(),
                    booking.getPlayerName(),
                    booking.getFacility().getFacilityName(),
                    booking.getDateTime().format(FORMATTER),
                    booking.getDuration()));
            System.out.println("--------------------------------------------------------"
                    + "---------------------------------------------------------------");
        }
    }

    public void showMessage(BookingInfo booking) throws Exception {
        System.out.println("Booking Infomation:");
        System.out.println("--------------------------------------------------");
        System.out.println("Booking ID    : " + booking.getBookingID());
        System.out.println("Player Name   : " + booking.getPlayerName());
        System.out.println("Facility Name : " + booking.getFacility().getFacilityName() + 
                "[Id: " + booking.getFacility().getFacilityID() + "]");
        System.out.println("Date          : " + booking.getDateTime().format(DATE_FORMATER));
        System.out.println("Time          : " + booking.getDateTime().format(TIME_FORMATER));
        System.out.println("Duration      : " + booking.getDuration());
        System.out.println("--------------------------------------------------");
    }

    //7.Booking revenue report, statistic the total revenue by facilities name
    
    
    //8. Service usage statistics -> statistics sport type and number of unique player booking per 
    // service, in the range of time.
//    public List<Object[]> statisticByService() throws Exception {
//        Function<BookingInfo, String> groupFunc = BookingInfo::getFacilityName;
//        Function<Map.Entry<String, List<BookingInfo>>, Object[]> mapFunc = entry -> {
//            String mountainCode = entry.getKey();
//            int numOfStudent = entry.getValue().size();
//            double totalFeeValue = entry.getValue().stream()
//                    .mapToDouble(Student::getTuitionFee).sum();
//            return new Object[]{mountainCode, numOfStudent, formatTuitionFee(totalFeeValue)};
//        };
//        return studentDAO.statistic(groupFunc, mapFunc);
//    } 
    
//    public void printServiceStatistic() throws Exception {
//        List<Object[]> statisticByMountain = statisticByMountainPeak();
//        System.out.println("Statistics of Registration by Mountain Peak:");
//        System.out.println("------------------------------------------------------");
//        System.out.println("| Peak Name  | Number of Participants  | Total Cost  | ");
//        System.out.println("------------------------------------------------------");
//        for (Object[] row : statisticByMountain) {
//            String code = (String) row[0];
//            int numStu = (int) row[1];
//            String totalFee = (String) row[2];
//            System.out.format("| %-11s|  %-23d| %-12s|\n", code, numStu, totalFee);
//        }
//        System.out.println("------------------------------------------------------");
//    }
    
    //9. save data to file
    public void exportToFile() throws Exception {
        bookingDAO.saveBookingListToFile();
        List<Facility> validFacilities = getValidFacilities();
        facilityDAO.saveFacilitiesListToFile(validFacilities);
        Menu.isSave = true;
        System.out.println("Registration data has been successfully saved to file.");
    }

    //10. check save status before exit program, 
    //if unsaved then ask user to save and then exit the program any way
    public void checkSaveBeforeExit() throws Exception {
        if (!Menu.isSave) {
            boolean checkSave = DataInput.getYesNo("Do you want to save the changes before exiting? (Y/N)");
            if (checkSave) {
                exportToFile();
                System.out.println("Program end with saved data.");
            } else {
                System.out.println("Program end without saved data");
            }
            return;
        }
        System.out.println("Program end.");
    }
}
