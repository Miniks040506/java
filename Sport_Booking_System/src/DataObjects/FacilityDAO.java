package DataObjects;

import Core.Entities.Facility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import Core.Interfaces.IFacilityDAO;
import java.util.HashSet;
import java.util.Set;

public class FacilityDAO implements IFacilityDAO {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    private final List<Facility> facilityList = new ArrayList<>();
    private final FileManager fileManager;

    public FacilityDAO(String fileName) throws Exception {
        this.fileManager = new FileManager(fileName);
        loadDataFromFile();
    }

    public FacilityDAO() throws Exception {
        this.fileManager = new FileManager("facility_schedule.csv","Active_Room_List.txt");
        loadDataFromFile();
    }

    public void loadDataFromFile() throws Exception {
        String id, name, type, location, start, end;
        int capacity;

        try {
            facilityList.clear();
            List<String> facilityData = fileManager.readDataFromFile();
            for (String e : facilityData) {
                List<String> parts = Arrays.asList(e.split(","));
                id = parts.get(0).trim();
                name = parts.get(1).trim();
                type = parts.get(2).trim();
                location = parts.get(3).trim();
                if (!parts.get(4).trim().matches("\\d+")) continue;
                capacity = Integer.parseInt(parts.get(4).trim());
                start = parts.get(5).trim();
                end = parts.get(6).trim();
                Facility facility = new Facility(id, name, type, location, capacity, start, end);
                facilityList.add(facility);
            }
            if (facilityList.isEmpty()) {
                System.out.println("Facility List is empty!");
            }
        } catch (Exception ex) {
            //System.out.println(count);
            //1ex.printStackTrace();
            throw new Exception("Can not read data from file(facility). Please check file again.");
            //System.out.println("Can not read data from file.");
        }
    }

    @Override
    public List<Facility> getFacilities() throws Exception {
        return facilityList;
    }

    @Override
    public Facility getFacilityById(String id) throws Exception {
        Facility facility = facilityList.stream()
                .filter(e -> e.getFacilityID().trim().equalsIgnoreCase(id))
                .findAny().orElse(null);
        return facility;
    }
    
    @Override
    public Facility getFacilityByName(String name) throws Exception {
        Facility facility = facilityList.stream()
                .filter(e -> e.getFacilityName().trim().equalsIgnoreCase(name))
                .findAny().orElse(null);
        return facility;
    }

    @Override
    public void updateFacility(Facility fcl) throws Exception {
        Facility facility = getFacilityById(fcl.getFacilityID());
        if (facility != null) {
            facility.setLocation(facility.getLocation());
            facility.setCapacity(facility.getCapacity());
            facility.setAvailabilityStart(facility.getAvailabilityStart().format(FORMATTER));
            facility.setAvailabilityEnd(facility.getAvailabilityEnd().format(FORMATTER));
        }
    }

    @Override
    public void saveFacilitiesListToFile(List<Facility> facilities) throws Exception {
        List<String> stringObjects = facilities.stream()
                .map(String::valueOf).collect(Collectors.toList());
        String data = String.join("\n", stringObjects);
        fileManager.saveDataToFile2(data);
    }

    @Override
    public List<Facility> search(Predicate<Facility> predicate) throws Exception {
        return facilityList.stream()
                .filter(facility -> predicate.test(facility))
                .collect(Collectors.toList());
    }
    
    
}
