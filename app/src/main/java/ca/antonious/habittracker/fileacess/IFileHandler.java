package ca.antonious.habittracker.fileacess;

/**
 * Created by George on 2016-09-01.
 */
public interface IFileHandler {
    String loadFileAsString(String filename);
    void saveStringToFile(String filename, String contents);
}
