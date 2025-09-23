package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.QuoteList;

public class DeleteQuoteCommand extends Command{
    private String quoteName;
    private QuoteList quoteList;

    public DeleteQuoteCommand(String quoteName, QuoteList quoteList) {
        super("delete");
        this.quoteName = quoteName;
        this.quoteList = quoteList;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Deleting quote: " + quoteName);
        quoteList.removeQuote(quoteName);
    }
}
