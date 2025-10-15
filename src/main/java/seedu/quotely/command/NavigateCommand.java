package seedu.quotely.command;

import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.Quote;
import seedu.quotely.exception.QuotelyException;

public class NavigateCommand extends Command {
    private Quote quote;

    public NavigateCommand(Quote quote) {
        super("nav");
        this.quote = quote;
    }

    public NavigateCommand() {
        super("nav");
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        if (quote == null) { //if trying to navigate to main menu
            if (state.getQuoteReference() == null) {
                ui.showMessage("You're already at the main menu.");
            } else {
                ui.showMessage("Navigating to the main menu.");
                state.setOutsideQuote();
            }
            return;
        }

        if (state.getQuoteReference() == quote) { //if at main menu
            ui.showMessage("You're already at quote: " + quote.getQuoteName());

//            ui.showMessage("Navigating to quote: " + quote.getQuoteName());
//            state.setQuoteReference(quote);
//            state.setInsideQuote(quote);
//        } else if (state.getQuoteReference() != quote) { //if navigating from one quote to another
//            ui.showMessage("Navigating to quote: " + quote.getQuoteName());
//            state.setQuoteReference(quote);
//            state.setInsideQuote(quote);
        } else { //if trying to navigate to the same quote
//            ui.showMessage("You're already at quote: " + quote.getQuoteName());
            ui.showMessage("Navigating to quote: " + quote.getQuoteName());
            state.setQuoteReference(quote);
            state.setInsideQuote(quote);
        }
    }
}
