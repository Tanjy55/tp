package seedu.quotely;

import seedu.quotely.command.Command;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class Quotely {
    private Ui ui;
    private CompanyName companyName;
    private QuoteList quoteList;

    /**
     * Constructor for Quotely
     * 1) create new ui instance
     * 2) create new CompanyName and set a default company name
     * 3) create new QuoteList instance, which contains list of quotes
     * <p>
     * Future flexibility
     * - Multiple CompanyName (different user), multiple Quotelist may be implemented
     * <p>
     * Data handling
     * - Quotelist currently implemented as ArrayList
     * - Each quote contains another ArrayList for Items
     * - quoteList and companyName is passed into parser then command
     * - lists are not mutated until execute() is called
     * - data is stored in classes under data package only
     */
    public Quotely() {
        ui = new Ui();
        companyName = new CompanyName("Default");
        quoteList = new QuoteList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        ui.showLine();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, quoteList, companyName);
                isExit = c.isExit();
            } catch (QuotelyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Quotely().run();
    }
}
