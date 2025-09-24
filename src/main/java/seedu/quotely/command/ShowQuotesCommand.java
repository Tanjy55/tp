package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class ShowQuotesCommand extends Command {
    public ShowQuotesCommand() {
        super("show");
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName) throws QuotelyException {
        ui.showMessage("Showing all quotes...");
    }
    
}
