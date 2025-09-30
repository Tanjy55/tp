package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
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
    public void execute (Ui ui,
                         QuoteList quoteList,
                         CompanyName companyName) throws QuotelyException {
        ui.showMessage("Total cost of quote " + quoteName + " for " + customerName + ": " + quoteList.calculateQuoteTotal(quoteName, customerName));
    }

}
