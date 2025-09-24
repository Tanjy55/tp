package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class AddQuoteCommand extends Command {
    private String quoteName;
    private String customerName;

    public AddQuoteCommand(String quoteName, String customerName) {
        super("add");
        this.quoteName = quoteName;
        this.customerName = customerName;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName) throws QuotelyException {
        ui.showMessage("Adding quote: " + quoteName + " for " + customerName);
        quoteList.addQuote(new Quote(quoteName, customerName));
    }
}
