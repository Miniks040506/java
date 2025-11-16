package Core.Interfaces;

import Core.Entities.BookingInfo;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IBookingDAO {
    List<BookingInfo> getBookings() throws Exception ;
    BookingInfo getBookingInfoById(String id) throws Exception ;
    public List<BookingInfo> getBookingsByDate(LocalDate date);
    void addBookingInfo(BookingInfo customer) throws Exception ;
    void cancelBookingInfo(BookingInfo customer) throws Exception ;
    void saveBookingListToFile() throws Exception ;
    List<BookingInfo> search(Predicate<BookingInfo> predicate) throws Exception ;
    <K> List<K> statistic(Function<BookingInfo, String> groupFunc, 
            Function<Map.Entry<String, List<BookingInfo>>, K> mapFunc) throws Exception;
}