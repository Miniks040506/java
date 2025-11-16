package util;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Validator {
    
    // Cho phép H:mm hoặc HH:mm
    private static final DateTimeFormatter FORMATTER =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("MM/dd/yyyy ")
                    .appendPattern("H:mm")  // giờ có thể 1 hoặc 2 chữ số
                    .toFormatter();
    
    private static final DateTimeFormatter FORMATTER_DATE =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    private static final DateTimeFormatter FORMATTER_MONTH_YEAR =
        DateTimeFormatter.ofPattern("MM/yyyy");
    
    // Lấy ngày hôm nay dạng dd/MM/yyyy
    public static String getToday(){
        LocalDate today = LocalDate.now();        
        return today.format(FORMATTER_DATE);
    }
    
    public static boolean isValidMonth(String month){
        try {
            YearMonth.parse(month, FORMATTER_MONTH_YEAR);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid month: " + month);
            return false;
        }
    }

    // Check string có đúng định dạng ngày/giờ không
    public static boolean isValidDate(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date: " + dateStr);
            return false;
        }
    }
    public static boolean isValidDay(String dayStr) {
        try {
            LocalDate.parse(dayStr, FORMATTER_DATE);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date: " + dayStr);
            return false;
        }
    }

    // Parse string -> LocalDateTime (assume valid)
    public static LocalDateTime parse(String dateStr) {
        return LocalDateTime.parse(dateStr, FORMATTER);
    }

    // Check start < end
    public static boolean isValidRange(String start, String end) {
        try {
            LocalDateTime s = parse(start);
            LocalDateTime e = parse(end);
            if (!e.isAfter(s)) {
                System.out.println("Invalid range: end must be after start (" + start + " - " + end + ")");
                return false;
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Invalid range format: " + ex.getMessage());
            return false;
        }
    }
    
    // Check booking time nằm trong khoảng [start, end]
    public static boolean isValidBookingTime(String start, String end, String booking) {
        try {
            LocalDateTime s = parse(start);
            LocalDateTime e = parse(end);
            LocalDateTime b = parse(booking);

            if ((b.isAfter(s) || b.isEqual(s)) && (b.isBefore(e) || b.isEqual(e))) {
                return true;
            } else {
                System.out.println("Invalid booking: must be between " + start + " and " + end);
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Invalid date format: " + ex.getMessage());
            return false;
        }
    }
    
    // Player name phải từ 2 - 18 ký tự
    public static boolean isValidPlayer(String name){
        return name != null && name.length() >= 2 && name.length() <= 18;
    }
    
    // Duration phải từ 1 - 5 giờ
    public static boolean isValidDuration(int duration){
        return duration >= 1 && duration <= 5;
    }
}
