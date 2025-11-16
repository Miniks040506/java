package fileio;

import model.Booking;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingFileHelper {

    private static final String FILE_NAME = "BookingInfo.dat";

    // Save all bookings vào file binary
    public void save(List<Booking> bookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bookings); // serialization
            System.out.println("Booking court has been successfully saved to \"" + FILE_NAME + "\" file.");
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }

    // Load bookings từ file binary
    @SuppressWarnings("unchecked")
    public List<Booking> load() {
        File f = new File(FILE_NAME);

        if (!f.exists()) {
            System.out.println(FILE_NAME + " not found. Creating new file...");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Booking>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
