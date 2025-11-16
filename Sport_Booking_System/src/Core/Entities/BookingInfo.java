package Core.Entities;

import Presentation.Menu;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingInfo {
    
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    private final DateTimeFormatter FORMATTER_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String playerName;
    private Facility facility;
    private LocalDateTime dateTime;
    private int duration;
    private String bookingID;
    
    public BookingInfo () {
    }

    public BookingInfo(String bookingID, String playerName, Facility facility, 
            String dateTime, int duration) throws Exception {
        setBookingID(bookingID);
        setPlayerName(playerName);
        setFacility(facility);
        setDateTime(dateTime);
        setDuration(duration);
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return Menu.capitalize(playerName);
    }

    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the facilityName
     */
    public Facility getFacility() {
        return facility;
    }

    /**
     * @param facilityName the facilityName to set
     */
    public void setFacility(Facility facility) throws Exception{
        if (facility == null) {
            throw new Exception("This facility does not existed! Choose valid facilities in the table.");
        }
        this.facility = facility;
    }

    /**
     * @return the dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(String dateTime) throws Exception{
        if (dateTime == null || dateTime.trim().isEmpty()) {
            throw new Exception("Availability start time cannot be empty!");
        }
        
        LocalDateTime parsedDateTime;
        try {
            parsedDateTime = LocalDateTime.parse(dateTime.trim(), FORMATTER);           
        } catch (DateTimeParseException e) {
            throw new Exception("Input date/time string format is invalid. Expected format: yyyy-MM-dd HH:mm", e);
        }
        this.dateTime = parsedDateTime;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) throws Exception{
        if (duration <= 0) {
            throw new Exception("Duration must be positive!");
        }
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d", getBookingID(), getPlayerName(), 
                Menu.capitalize(getFacility().getFacilityName()), 
                getDateTime().format(FORMATTER_OUT), getDuration());
    }
}
