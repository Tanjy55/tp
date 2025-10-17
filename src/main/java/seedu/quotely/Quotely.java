package seedu.quotely;

import seedu.quotely.command.Command;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.parser.Parser;
import seedu.quotely.ui.Ui;
import seedu.quotely.util.LoggerConfig;
import java.util.logging.Logger;

public class Quotely {
    private Ui ui;
    private CompanyName companyName;
    private QuoteList quoteList;
    private QuotelyState state;

    /**
     * Constructor for Quotely
     * 1) create new ui instance
     * 2) create new CompanyName and set a default company name
     * 3) create new QuoteList instance, which contains list of quotes
     * <p>
     * Data handling
     * - Quotelist currently implemented as ArrayList
     * - Each quote contains another ArrayList for Items
     * - quoteList and companyName is passed into parser then command
     * - lists are not mutated until execute() is called
     * - data is stored in classes under data package only
     * <p>
     * State:
     * - 2 states currently implemented
     * - differentiate main menu and quote environments
     * - first state: in main menu
     * - other state: inside quote
     * Additions for v2.0:
     * - local file saving
     * - isTax attribute for Items
     * - pdf export function
     * - user may show particular quote
     * - business address, customer address
     * - quote runs on serial number instead of name
     * <p>
     * Possible additions for 2.0b:
     * - installment calculator
     * - converter (future update if unable to finish)
     * - pdf different template generation
     */
    public Quotely() {
        ui = new Ui();
        companyName = new CompanyName("Default");
        quoteList = new QuoteList();
        state = new QuotelyState();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        ui.showLine();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(state);
                ui.showLine();
                Command c = Parser.parse(fullCommand, state, quoteList);
                c.execute(ui, quoteList, companyName, state);
                isExit = c.isExit();
            } catch (QuotelyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        // Initialize global logging configuration
        LoggerConfig.initializeGlobalLogging();

        Logger logger = LoggerConfig.getLogger(Quotely.class);
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
