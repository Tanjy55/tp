package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class FinishQuoteCommand extends Command {
    public FinishQuoteCommand() {
        super("finish");
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName) throws QuotelyException {

        /*
        Finish quote needs to set state back to main menu
        Suggestion: pass state to constructor
        state.setInsideQuote(false);
        state.setQuoteReference(null);
         */

        ui.showMessage("Finishing quote process.");
    }

}
