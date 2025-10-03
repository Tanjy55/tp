package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class FinishQuoteCommand extends Command {
    public FinishQuoteCommand() {
        super("finish");
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {
        ui.showMessage("Finishing quote process.");
        state.setOutsideQuote();
    }

}
