package Core.Interfaces;

import Core.Entities.Facility;
import java.util.List;
import java.util.function.Predicate;

public interface IFacilityDAO {
    List<Facility> getFacilities() throws Exception ;
    Facility getFacilityById(String id) throws Exception ;
    Facility getFacilityByName(String name) throws Exception ;
    void updateFacility(Facility customer) throws Exception ;
    void saveFacilitiesListToFile(List<Facility> facilities) throws Exception ;
    List<Facility> search(Predicate<Facility> predicate) throws Exception ;
}