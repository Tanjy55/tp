package seedu.quotely.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import seedu.quotely.util.LoggerConfig;

/**
 * Manages saving and loading of Quotely application data to a local file.
 */
public class Storage {

    private static final Logger logger = LoggerConfig.getLogger(Storage.class);
    private final Path filePath;

    /**
     * Constructs a Storage object to manage data at the specified file path.
     */
    public Storage(String filePath) {

        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";

        this.filePath = Paths.get(filePath);
        logger.info("Storage initialized. Data file path: " + this.filePath);
    }

    /**
     * Ensures that the directory for the data file exists.
     */
    private void ensureDirectoryExists() throws IOException {
        Path parentDir = filePath.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            logger.info("Data directory not found. Creating: " + parentDir);
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Loads the application data from the file as a String.
     */
    public String loadData() throws IOException {
        if (!Files.exists(filePath)) {
            logger.warning("Data file not found, will attempt to create a new one: " + filePath);
            ensureDirectoryExists();
            return ""; // Return empty string to signify no data
        }

        try {
            String data = new String(Files.readAllBytes(filePath));

            logger.info("Successfully loaded data from " + filePath);
            return data;
        } catch (IOException e) {
            logger.severe("Failed to read data from file: " + filePath);
            throw e;
        }
    }

    /**
     * Saves the application data (as a String) to the file.
     */
    public void saveData(String data) throws IOException {

        assert data != null : "Data to be saved cannot be null";

        try {
            ensureDirectoryExists();
            Files.write(filePath, data.getBytes());
            logger.info("Successfully saved data to " + filePath);
        } catch (IOException e) {
            logger.severe("Failed to save data to file: " + e.getMessage());
            throw e;
        }
    }
}