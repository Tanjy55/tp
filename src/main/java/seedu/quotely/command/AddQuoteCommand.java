package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.command.Command;
import seedu.quotely.exception.QuotelyException;

public class AddQuoteCommand extends Command {
    private String quoteName;
    private String companyName;

    public AddQuoteCommand(String quoteName, String companyName) {
        super("add");
        this.quoteName = quoteName;
        this.companyName = companyName;
    }

    @Override
    public void execute(Ui ui) throws QuotelyException {
        ui.showMessage("Adding quote: " + quoteName + " for " + companyName);
    }    
}
