package seedu.quotely.command;

import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.Quote;
import seedu.quotely.exception.QuotelyException;

public class DeleteQuoteCommand extends Command {
    private Quote quote;

    public DeleteQuoteCommand(Quote quote) {
        super("delete");
        this.quote = quote;
    }

    @Override
    public void execute(Ui ui,
            QuoteList quoteList,
            CompanyName companyName,
            QuotelyState state) throws QuotelyException {

        ui.showMessage("Deleting quote: " + quote.getQuoteName());
        // go back to main menu if the deleted quote is the current quote
        if (state.isInsideQuote() && state.getQuoteReference().equals(quote)) {
            state.setOutsideQuote();
        }
        quoteList.removeQuote(quote);
    }
}
