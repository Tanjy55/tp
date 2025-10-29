package seedu.quotely;

import seedu.quotely.command.Command;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.parser.Parser;
import seedu.quotely.ui.Ui;
import seedu.quotely.util.LoggerConfig;

import seedu.quotely.storage.Storage;
import seedu.quotely.storage.JsonSerializer;
import java.io.IOException;

import java.util.logging.Logger;

public class Quotely {
    private static Logger logger;
    private static final String DEFAULT_STORAGE_FILEPATH = "data/quotely.json";

    private Ui ui;
    private CompanyName companyName;
    private QuoteList quoteList;
    private QuotelyState state;

    // Fields for storage
    private Storage storage;
    private JsonSerializer serializer;

    /**
     * Constructor for Quotely
     * 1) create new ui instance
     * 2) create new CompanyName and set a default company name
     * 3) initialise storage and load existing data if any
     */
    public Quotely() {
        ui = Ui.getInstance();
        state = QuotelyState.getInstance();
        companyName = new CompanyName("Default");

        // Initialize storage and load data
        storage = new Storage(DEFAULT_STORAGE_FILEPATH);
        serializer = new JsonSerializer();
        quoteList = loadDataFromFile();
    }

    /**
     * Loads the QuoteList from the file specified in storage.
     * If the file is not found or is corrupted, returns a new empty QuoteList.
     */
    private QuoteList loadDataFromFile() {
        try {
            String jsonData = storage.loadData();
            QuoteList loadedList = serializer.deserialize(jsonData);
            logger.info("Successfully loaded data from " + DEFAULT_STORAGE_FILEPATH);
            return loadedList;
        } catch (IOException e) {
            logger.warning("Failed to read from data file. "
                    + "Starting with a new QuoteList. Error: " + e.getMessage());
            ui.showError("Could not load data file. Starting fresh.");
            return new QuoteList();
        } catch (Exception e) { // Catches potential JSON syntax errors
            logger.severe("Data file is corrupted. Starting with a new QuoteList. Error: " + e.getMessage());
            ui.showError("Data file appears to be corrupted. Starting fresh.");
            return new QuoteList();
        }
    }

    /**
     * Saves the current QuoteList to the file specified in storage.
     */
    private void saveDataToFile() {
        try {
            String jsonData = serializer.serialize(quoteList);
            storage.saveData(jsonData);
            logger.info("Data saved successfully to " + DEFAULT_STORAGE_FILEPATH);
        } catch (IOException e) {
            logger.severe("Failed to save data to file: " + e.getMessage());
            ui.showError("Error: Failed to save data to file.");
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        ui.showLine();
        while (!isExit) {
            try {
                logger.finer("Reading user inputs...");
                String fullCommand = ui.readCommand(state);
                ui.showLine();
                logger.finer("Read successful, parsing command: ...");
                // parser throws QuotelyException if parse invalid
                Command c = Parser.parse(fullCommand, state, quoteList);
                logger.finer("Parse successful, executing command...");
                // execute throws QuotelyException if data mutation fails
                c.execute(ui, quoteList, companyName, state);
                isExit = c.isExit();

                // Save data after every successful command that doesn't exit
                if (!isExit) {
                    saveDataToFile();
                }

            } catch (QuotelyException e) {
                ui.showError(e.getMessage());
                logger.severe(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        // Initialize global logging configuration
        LoggerConfig.initializeGlobalLogging();

        logger = LoggerConfig.getLogger(Quotely.class);
        logger.info("Starting Quotely application");

        try {
            new Quotely().run();
            logger.info("Quotely application finished successfully");
        } catch (Exception e) {
            logger.severe("Quotely application crashed: " + e.getMessage());
            throw e;
        }
    }
}
