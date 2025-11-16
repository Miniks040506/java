package controller;

import model.Facility;
import fileio.IFileReadWrite;
import fileio.FacilityFileHelper;
import java.util.ArrayList;
import java.util.List;

public class FacilityController {

    private List<Facility> facilities = new ArrayList<>();

    public FacilityController() {
        
    }
    
    public void load(){
        try {
            IFileReadWrite file = new FacilityFileHelper();
            facilities = file.read();
        } catch (Exception e) {
            facilities = new ArrayList<>();
        }
    }

    public List<Facility> getAll() {
        return facilities;
    }
    
    public void updateFacility(){
    
    }

    public Facility getById(String id) {
        for (Facility f : facilities) {
            if (f.getId().equalsIgnoreCase(id)) {
                return f;
            }
        }
        return null;
    }
    
    public Facility getByName(String name) {
        for (Facility f : facilities) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        return null;
    }
    
    public boolean isExistId(String id){
        return this.getById(id) != null;
    }
    
    public boolean isExistName(String name){
        return this.getByName(name) != null;
    }
    
    
}
