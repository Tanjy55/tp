package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;

public class CalculateTotalCommand extends Command {
    private String quoteName;
    private String customerName;

    public CalculateTotalCommand(String quoteName, String customerName) {
        super("total");
        this.quoteName = quoteName;
        this.customerName = customerName;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        if (state.isInsideQuote()) {
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_STATE);
        }

        ui.showMessage("Total cost of quote " + quoteName + " for " + customerName + ": "
                + quoteList.calculateQuoteTotal(quoteName, customerName));
    }
}
