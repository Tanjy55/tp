package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;

public class DeleteQuoteCommand extends Command {
    private String quoteName;

    public DeleteQuoteCommand(String quoteName) {
        super("delete");
        this.quoteName = quoteName;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        if (state.isInsideQuote()) {
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_STATE);
        }

        ui.showMessage("Deleting quote: " + quoteName);
        quoteList.removeQuote(quoteName);
    }
}
