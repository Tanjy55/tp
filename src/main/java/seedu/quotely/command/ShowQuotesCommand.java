package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;

import java.util.List;

public class ShowQuotesCommand extends Command {

    private static final double GST_RATE = 0.09;

    public ShowQuotesCommand() {
        super("show");
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        if (state.isInsideQuote()) {
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_STATE);
        }

        List<Quote> quotes = quoteList.getQuotes();

        if (quotes == null || quotes.isEmpty()) {
            ui.showMessage("No quotes to show.");
            return;
        }

        ui.showMessage("Displaying all quotes:\n");

        for (Quote q : quotes) {
            ui.showQuote(companyName, q, GST_RATE);
        }
    }
}



