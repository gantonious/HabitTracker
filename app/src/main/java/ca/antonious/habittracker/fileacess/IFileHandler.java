package ca.antonious.habittracker.fileacess;

/**
 * Created by George on 2016-09-01.
 *
 * IFileHandler is an interface that exposes methods to read/write files to disk
 */
public interface IFileHandler {
    String loadFileAsString(String filename);
    void saveStringToFile(String filename, String contents);
}
