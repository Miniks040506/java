/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import util.Validator;
import java.util.Random;
import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String id;
    private String player;
    private String facilityName;
    private String dateTime;
    private int duration;
    private static final double price = 5000000;
    private double total;
    private boolean status;

    public Booking(String id, String player, String facilityName, String dateTime, int duration, double total, boolean status) {
        this.id = id;
        this.player = player;
        this.facilityName = facilityName;
        this.dateTime = dateTime;
        this.duration = duration;
        this.total = total;
        this.status = status;
    }

    public Booking(String player, String facilityName, String dateTime, int duration) {
        this.id = this.generateBookingCode(player, dateTime);
        this.player = player;
        this.facilityName = facilityName;
        this.dateTime = dateTime;
        this.duration = duration;
        this.total = duration * price;
        this.status = true;
    }
    
    

    private String generateBookingCode(String player, String facility) {
        int rand = new Random().nextInt(900) + 100; // số 3 chữ số ngẫu nhiên
        long time = System.currentTimeMillis();
        return player.substring(0, 2).toUpperCase() + 
               facility.substring(0, 2).toUpperCase() + 
               "-" + time + "-" + rand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacility(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getDate(){
        return dateTime.substring(0, dateTime.indexOf(" "));
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }
    
    

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public String normalizeFacility() {
        String rawName = this.facilityName;
        if (rawName.toLowerCase().contains("football field")) return "Football Field";
        if (rawName.toLowerCase().contains("fadminton")) return "Badminton";
        if (rawName.toLowerCase().contains("basketball")) return "Basketball";
        if (rawName.toLowerCase().contains("tennis")) return "Tennis";
        return rawName; 
}
    
}
