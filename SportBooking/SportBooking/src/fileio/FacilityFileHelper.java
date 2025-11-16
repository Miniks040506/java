package fileio;

import java.io.*;
import java.util.*;
import model.Facility;

public class FacilityFileHelper implements IFileReadWrite<Facility> {
    private final String INPUT_FILE = "src/fileio/facility_schedule.csv";
    private final String OUTPUT_FILE = "src/fileio/Active_Room_List.txt";

    @Override
    public List<Facility> read() throws Exception {
        List<Facility> list = new ArrayList<>();
        Set<String> uniqueNames = new HashSet<>();
        int success = 0, failed = 0;

        File f = new File(INPUT_FILE);
        if (!f.exists()) {
            System.out.println("Error: File does not exist.");
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line; boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (first) { first = false; continue; } // bỏ header

                Facility fac = convert(line, uniqueNames);
                if (fac != null) { list.add(fac); uniqueNames.add(fac.getName()); success++; }
                else failed++;
            }
        }

        System.out.println(success + " rooms successfully loaded.");
        System.out.println(failed + " entries failed.");
        write(list); // ghi ra file txt
        return list;
    }

    @Override
    public boolean write(List<Facility> list) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            bw.write("Id, Facility Name, Facility Type, Location, Capacity, Availability Start, Availability End");
            bw.newLine();
            for (Facility f : list) {
                bw.write(String.format("%s, %s, %s, %s, %d, %s, %s",
                        f.getId(), f.getName(), f.getType(), f.getLocation(),
                        f.getCapacity(), f.getAvailabilityStart(), f.getAvailabilityEnd()));
                bw.newLine();
            }
        }
        System.out.println("Saved " + list.size() + " facilities to " + OUTPUT_FILE);
        return true;
    }

    private Facility convert(String line, Set<String> uniqueNames) {
        String[] p = line.split(",");
        if (p.length != 7) { System.out.println("Invalid line: " + line); return null; }

        String id = p[0].trim(), name = p[1].trim(), type = p[2].trim(),
               loc = p[3].trim(), capStr = p[4].trim(),
               start = p[5].trim(), end = p[6].trim();

        if (uniqueNames.contains(name)) { System.out.println("Duplicate name: " + name); return null; }

        int cap;
        try { cap = Integer.parseInt(capStr); if (cap <= 0) throw new NumberFormatException(); }
        catch (NumberFormatException e) { System.out.println("Invalid capacity: " + capStr); return null; }

        return new Facility(id, name, type, loc, cap, start, end);
    }
}
