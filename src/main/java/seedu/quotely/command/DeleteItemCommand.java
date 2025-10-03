package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;

public class DeleteItemCommand extends Command {
    private String itemName;
    private String quoteName;

    public DeleteItemCommand(String itemName, String quoteName) {
        super("deleteItem");
        this.itemName = itemName;
        this.quoteName = quoteName;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        if (!state.isInsideQuote()) {
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_STATE);
        }

        ui.showMessage("Deleting item: " + itemName + " from quote: " + quoteName);
        Quote currentQuote = state.getQuoteReference();
        //to edit remove item method
        quoteList.removeItem(quoteName, itemName);
    }
}
