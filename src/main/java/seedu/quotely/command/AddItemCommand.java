package seedu.quotely.command;

import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;

public class AddItemCommand extends Command {
    private Quote quote;
    private String itemName;
    private Double price;
    private int quantity;
    private boolean isTax;

    public AddItemCommand(String itemName, Quote quote, Double price, int quantity, boolean isTax) {
        super("addItem");
        this.itemName = itemName;
        this.quote = quote;
        this.price = price;
        this.quantity = quantity;
        this.isTax = isTax;
    }

    @Override
    public void execute(Ui ui,
            QuoteList quoteList,
            CompanyName companyName,
            QuotelyState state) throws QuotelyException {

        ui.showMessage(
                String.format(
                        "Adding %s to quote %s with price %.2f, quantity %d",
                        itemName, quote.getQuoteName(), price, quantity));
        quote.addItem(itemName, price, quantity, isTax);
    }
}
