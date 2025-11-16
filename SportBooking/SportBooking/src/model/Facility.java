/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class Facility {
    private String id;
    private String name;
    private String type;
    private String location;
    private int capacity;
    private String availabilityStart;
    private String availabilityEnd;

    public Facility(String id, String name, String type, String location, int capacity, String availabilityStart, String availabilityEnd) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.capacity = capacity;
        this.availabilityStart = availabilityStart;
        this.availabilityEnd = availabilityEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAvailabilityStart() {
        return availabilityStart;
    }

    public void setAvailabilityStart(String availabilityStart) {
        this.availabilityStart = availabilityStart;
    }

    public String getAvailabilityEnd() {
        return availabilityEnd;
    }

    public void setAvailabilityEnd(String availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    @Override
public String toString() {
    return "Facility{" + 
           "\n  id=" + id + 
           ",\n  name=" + name + 
           ",\n  type=" + type + 
           ",\n  location=" + location + 
           ",\n  capacity=" + capacity + 
           ",\n  availabilityStart=" + availabilityStart + 
           ",\n  availabilityEnd=" + availabilityEnd + 
           "\n}";
}
    
    

    
}
