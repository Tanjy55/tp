package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class AddQuoteCommand extends Command {
    private String quoteName;
    private String customerName;
    private QuoteList quoteList;

    public AddQuoteCommand(String quoteName, String companyName, QuoteList quoteList) {
        super("add");
        this.quoteName = quoteName;
        this.customerName = companyName;
        this.quoteList = quoteList;
    }

    @Override
    public void execute(Ui ui) throws QuotelyException {
        ui.showMessage("Adding quote: " + quoteName + " for " + customerName);
        quoteList.addQuote(new Quote(quoteName, customerName));
    }
}
