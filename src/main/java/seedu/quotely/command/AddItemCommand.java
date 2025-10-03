package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class AddItemCommand extends Command {
    private String quoteName;
    private String itemName;
    private Double price;
    private int quantity;

    public AddItemCommand(String itemName, String quoteName, Double price, int quantity) {
        super("addItem");
        this.itemName = itemName;
        this.quoteName = quoteName;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName) throws QuotelyException {

        ui.showMessage(
                String.format(
                        "Adding item to quote %s with name %s, price %.2f, quantity %d",
                        quoteName, itemName, price, quantity
                )
        );

        /*
        Adding item needs quote reference or user input from parser
        Quote currentQuote = state.getQuoteReference();
        to edit add item method
         */
        quoteList.addItem(quoteName, itemName, price, quantity);
    }
}
