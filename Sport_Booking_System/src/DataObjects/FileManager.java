package DataObjects;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileManager {
    private String fileName;
    private String fileName2;

    public FileManager() {
    }
    //----------------------------------------
    
    public FileManager(String fileName) {
        this.fileName = fileName;
    }
    //----------------------------------------

    public FileManager(String fileName, String fileName2) {
        this.fileName = fileName;
        this.fileName2 = fileName2;
    }
    //----------------------------------------

    public List<String> readDataFromFile() throws IOException {
        List<String> result;
        result = Files.readAllLines(new File(fileName).toPath(),
                StandardCharsets.UTF_8);
        return result;
    }
    //----------------------------------------
    
    public void saveDataToFile(String data) throws IOException {
        Files.write(new File(fileName).toPath(),
                data.getBytes(StandardCharsets.UTF_8));
    }
    //-----------------------------------------

    public void saveDataToFile2(String data) throws IOException {
        Files.write(new File(fileName2).toPath(),
                data.getBytes(StandardCharsets.UTF_8));
    }
}
