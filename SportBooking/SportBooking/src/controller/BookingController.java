package controller;

import model.Booking;
import fileio.IFileReadWrite;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import fileio.BookingFileHelper;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookingController {

    private List<Booking> bookings = new ArrayList<>();
    private boolean isChanged = false;
    private BookingFileHelper fileHelper;

    
    public BookingController() {
        fileHelper = new BookingFileHelper();
        load(); // load data khi khởi tạo
    }

    public void load() {
        bookings = fileHelper.load();
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
    }

    public void save() {
        fileHelper.save(bookings);
        isChanged = false;
    }

    public boolean addBooking(Booking booking){
        if(booking == null){
            return false;
           
        }
        isChanged = true;
        return bookings.add(booking);
    }
    
    public boolean cancelBooking(Booking booking){
        booking.setStatus(false);
        isChanged = true;
        return bookings.remove(booking);
    }
    
    public List<Booking> getAll() {
        return bookings;
    }

    public Booking getById(String code) {
        for (Booking b : bookings) {
            String bId = b.getPlayer()+" "+b.getDate()+" "+b.getFacilityName();
            if (bId.equalsIgnoreCase(code)) {
                return b;
            }
        }
        return null;
    }
    
    public List<Booking> getByStatus(){
        List<Booking> list = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.isStatus()) {
                list.add(b);
            }
        }
        return list;
    }
    
    public List<Booking> getByDate(String date) {
        List<Booking> list = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getDateTime().contains(date)) {
                list.add(b);
            }
        }
        return list;
    }

    public List<Booking> getByMonth(String month){
        List<Booking> list = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getDateTime().contains(month)) {
                list.add(b);
            }
        }
        return list;
    }
        
    public boolean isExistCode(String code){
        return this.getById(code) != null;
    }
    
    public boolean getIsChanged() {
        return isChanged;
    }
    
    public void reportRevenue(List<Booking> revenue){
        if (revenue.isEmpty()) {
            System.out.println("No data available in the Monthly Revenue Report");
            return;
        }
        
        Map<String, Double> facilityRevenue = new LinkedHashMap<>();

        facilityRevenue.put("Badminton", 0.0);
        facilityRevenue.put("Football Field", 0.0);
        facilityRevenue.put("Table Tennis", 0.0);
        facilityRevenue.put("Swimming Pool", 0.0);
        
        for (Booking b : revenue) {
            
            String key = b.normalizeFacility();
            if (facilityRevenue.containsKey(key)) {
                facilityRevenue.merge(key, b.getTotal(), Double::sum);
            }
        }
        
        System.out.println("---------------------------------------------");
        System.out.println("No.   | Facility            | Amount         ");
        System.out.println("---------------------------------------------");

        long total = 0;
        int i = 1;
        for (Map.Entry<String, Double> entry : facilityRevenue.entrySet()) {
            System.out.printf("%-8d | %-15s | %.2f%n", i++, entry.getKey(), entry.getValue());
            total += entry.getValue();
        }

        System.out.println("--------------------------------------------");
        System.out.println("Total\t\t\t" + total);
    }
    
    public void reportServiceUsage(List<Booking> usage){
        if (usage.isEmpty()) {
            System.out.println("No data available in the Service Usage Report");
            return;
        }
        
        Map<String, Integer> facilityRevenue = new LinkedHashMap<>();

        facilityRevenue.put("Badminton", 0);
        facilityRevenue.put("Football Field", 0);
        facilityRevenue.put("Table Tennis", 0);
        facilityRevenue.put("Swimming Pool", 0);
        
        for (Booking b : usage) {
            
            String key = b.normalizeFacility();
            if (facilityRevenue.containsKey(key)) {
                facilityRevenue.merge(key, 1, Integer::sum);
            }
        }
        
        System.out.println("--------------------------------------");
        System.out.println(" Facility            | Amount         ");
        System.out.println("--------------------------------------");

        long total = 0;
        int i = 1;
        for (Map.Entry<String, Integer> entry : facilityRevenue.entrySet()) {
            System.out.printf("%-15s | %d%n", entry.getKey(), entry.getValue());
        }

        System.out.println("--------------------------------------");
    }
}
