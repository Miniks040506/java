package DataObjects;

import Core.Entities.BookingInfo;
import Core.Entities.Facility;
import Core.Interfaces.IBookingDAO;
import Core.Interfaces.IFacilityDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingDAO implements IBookingDAO {

    public final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    private final List<BookingInfo> bookingList = new ArrayList<>();
    private final FileManager fileManager;
    public static Set<String> uniqueID = new HashSet<>();
    IFacilityDAO facilityDAO;

    public BookingDAO(IFacilityDAO facilityDAO) throws Exception {
        this.fileManager = new FileManager("BookingInfo.txt");
        this.facilityDAO = facilityDAO;
        loadDataFromFile();
    }

    public void loadDataFromFile() throws Exception {
        String playerName, facilityName, id, dateTime;
        int duration;

        try {
            bookingList.clear();
            List<String> facilityData = fileManager.readDataFromFile();
            for (String e : facilityData) {
                List<String> parts = Arrays.asList(e.split(","));
                id = parts.get(0).trim();
                playerName = parts.get(1).trim();
                facilityName = parts.get(2).trim();
                dateTime = parts.get(3).trim();
                duration = Integer.parseInt(parts.get(4).trim());
                Facility facility = facilityDAO.getFacilityByName(facilityName);
                if (facility != null) {
                    BookingInfo bookingInfo = 
                            new BookingInfo(id, playerName, facility, dateTime, duration);
                    bookingList.add(bookingInfo);
                    uniqueID.add(id);
                }
            }
            if (bookingList.isEmpty()) {
                System.out.println("Booking List is empty!");
            }
        } catch (Exception ex) {
            throw new Exception("Can not read data from file. Please check file again.");
        }
    }

    @Override
    public List<BookingInfo> getBookings() throws Exception {
        return bookingList;
    }

    @Override
    public BookingInfo getBookingInfoById(String id) throws Exception {
        return this.bookingList.stream()
                .filter(e -> e.getBookingID().trim().equalsIgnoreCase(id))
                .findAny().orElse(null);
    }

    @Override
    public List<BookingInfo> getBookingsByDate(LocalDate date) {
        return this.bookingList.stream()
                .filter(booking -> booking.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public void addBookingInfo(BookingInfo player) throws Exception {
        bookingList.add(player);
        uniqueID.add(player.getBookingID());
    }

    @Override
    public void cancelBookingInfo(BookingInfo player) throws Exception {
        bookingList.remove(player);
    }

    @Override
    public void saveBookingListToFile() throws Exception {
        List<String> stringObjects = bookingList.stream()
                .map(String::valueOf).collect(Collectors.toList());
        String data = String.join("\n", stringObjects);
        fileManager.saveDataToFile(data);
    }

    @Override
    public List<BookingInfo> search(Predicate<BookingInfo> predicate) throws Exception {
        return bookingList.stream()
                .filter(info -> predicate.test(info))
                .collect(Collectors.toList());
    }

    @Override
    public <K> List<K> statistic(Function<BookingInfo, String> groupFunc,
            Function<Map.Entry<String, List<BookingInfo>>, K> mapFunc) throws Exception {
        Map<String, List<BookingInfo>> grouped = bookingList.stream()
                .collect(Collectors.groupingBy(groupFunc));

        return grouped.entrySet().stream()
                .map(mapFunc)
                .collect(Collectors.toList());
    }
}
