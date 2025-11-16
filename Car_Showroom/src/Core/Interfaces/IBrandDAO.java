package Core.Interfaces;

import Core.Entities.Brand;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IBrandDAO {
    List<Brand> getBrands() throws Exception ;
    Brand getBrandById(String id) throws Exception ;
    void addBrand(Brand customer) throws Exception ;
    void updateBrand(Brand customer) throws Exception ;
    void saveBrandListToFile() throws Exception ;
    List<Brand> search(Predicate<Brand> predicate) throws Exception ;
    public List<Object[]> statistic(Function<Brand, String> groupFunc, 
            Function<Map.Entry<String, List<Brand>>, Object[]> mapFunc) throws Exception;
}