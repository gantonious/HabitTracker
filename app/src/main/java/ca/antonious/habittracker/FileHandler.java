package ca.antonious.habittracker;

/**
 * Created by George on 2016-09-01.
 */
public interface FileHandler {
    String loadFileAsString(String filename);
    void saveStringToFile(String filename, String contents);
}
