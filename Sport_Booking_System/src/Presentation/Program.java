package Presentation;

import Core.Interfaces.IBookingDAO;
import DataObjects.BookingDAO;
import DataObjects.FacilityDAO;
import Core.Interfaces.IFacilityDAO;



public class Program {
    public static void main(String[] args) {
        try {
            IFacilityDAO facilityService = new FacilityDAO();
            IBookingDAO bookingService = new BookingDAO(facilityService);
            Menu.manageMenu(bookingService, facilityService);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
