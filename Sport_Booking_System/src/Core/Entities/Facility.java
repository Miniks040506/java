package Core.Entities;

import Presentation.Menu;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Facility {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    private final DateTimeFormatter FORMATTER_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String facilityID;
    private String facilityName;
    private String facilityType;
    private String location;
    private int capacity;
    private LocalDateTime availabilityStart;
    private LocalDateTime availabilityEnd;

    public Facility() {
    }

    public Facility(String facilityID, String facilityName, String facilityType, String location,
            int capacity, String availabilityStart, String availabilityEnd) throws Exception {
        setFacilityID(facilityID);
        setFacilityName(facilityName);
        setFacilityType(facilityType);
        setLocation(location);
        setCapacity(capacity);
        setAvailabilityStart(availabilityStart);
        setAvailabilityEnd(availabilityEnd);
    }

    /**
     * @return the facilityID
     */
    public String getFacilityID() {
        return facilityID.toUpperCase();
    }

    /**
     * @param facilityID the facilityID to set
     */
    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    /**
     * @return the facilityName
     */
    public String getFacilityName() {
        return Menu.capitalize(facilityName);
    }

    /**
     * @param facilityName the facilityName to set
     */
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    /**
     * @return the facilityType
     */
    public String getFacilityType() {
        return facilityType;
    }

    /**
     * @param facilityType the facilityType to set
     */
    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    /**
     * @return the Location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param Location the Location to set
     */
    public void setLocation(String location) throws Exception {
        if (location.isEmpty()) {
            throw new Exception("Location can not be empty!");
        }
        this.location = location;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) throws Exception {
        if (capacity <= 0) {
            throw new Exception("Capacity must be positive!");
        }
        this.capacity = capacity;
    }

    /**
     * @return the AvailabilityStart
     */
    public LocalDateTime getAvailabilityStart() {
        return this.availabilityStart;
    }

    /**
     * @param AvailabilityStart the AvailabilityStart to set
     */
    public void setAvailabilityStart(String availabilityStart) throws Exception {
        if (availabilityStart == null || availabilityStart.trim().isEmpty()) {
            throw new Exception("Availability start time cannot be empty!");
        }
        
        LocalDateTime parsedDateTime;
        try {
            parsedDateTime = LocalDateTime.parse(availabilityStart.trim(), FORMATTER);
            
        } catch (DateTimeParseException e) {
            throw new Exception("Input date/time string format is invalid. Expected format: yyyy-MM-dd HH:mm", e);
        }

        this.availabilityStart = parsedDateTime;
    }

    /**
     * @return the AvailabilityEnd
     */
    public LocalDateTime getAvailabilityEnd() {
        return this.availabilityEnd;
    }

    /**
     * @param AvailabilityEnd the AvailabilityEnd to set
     */
    public void setAvailabilityEnd(String availabilityEnd) throws Exception {
        if (availabilityEnd == null || availabilityEnd.trim().isEmpty()) {
            throw new Exception("Availability start time cannot be empty!");
        }
        
        LocalDateTime parsedDateTime;
        try {
            parsedDateTime = LocalDateTime.parse(availabilityEnd.trim(), FORMATTER);
            
        } catch (DateTimeParseException e) {
            throw new Exception("Input date/time string format is invalid. Expected format: yyyy-MM-dd HH:mm", e);
        }

        this.availabilityEnd = parsedDateTime;
    }
    

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %s, %s", getFacilityID(), getFacilityName(),
                getFacilityType(), getLocation(), getCapacity(),
                getAvailabilityStart().format(FORMATTER_OUT), getAvailabilityEnd().format(FORMATTER_OUT));
    }
}
