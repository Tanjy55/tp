package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.Quote;
import seedu.quotely.exception.QuotelyException;

public class DeleteItemCommand extends Command {
    private String itemName;
    private Quote quote;

    public DeleteItemCommand(String itemName, Quote quote) {
        super("deleteItem");
        this.itemName = itemName;
        this.quote = quote;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        ui.showMessage("Deleting " + itemName + " from quote " + quote.getQuoteName());

        /*
        Delete item needs quote reference or user input from parser
        Quote currentQuote = state.getQuoteReference();
        to edit remove item method
         */
        quote.removeItem(itemName);
    }
}
