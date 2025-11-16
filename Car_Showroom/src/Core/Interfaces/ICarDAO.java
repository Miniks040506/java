package Core.Interfaces;

import Core.Entities.Car;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ICarDAO {
    List<Car> getCars() throws Exception ;
    Car getCarById(String id) throws Exception ;
    void addCar(Car customer) throws Exception ;
    void updateCar(Car customer) throws Exception ;
    void removeCar(Car customer) throws Exception ;
    void saveCarListToFile() throws Exception ;
    List<Car> search(Predicate<Car> predicate) throws Exception ;
    public List<Object[]> statistic(Function<Car, String> groupFunc, 
            Function<Map.Entry<String, List<Car>>, Object[]> mapFunc) throws Exception;
}