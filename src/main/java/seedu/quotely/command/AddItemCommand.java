package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.QuoteList;

public class AddItemCommand extends Command {
    private String quoteName;
    private String itemName;
    private Double price;
    private int quantity;
    private QuoteList quoteList;

    public AddItemCommand(String itemName, String quoteName, Double price, int quantity, QuoteList quoteList) {
        super("addItem");
        this.itemName = itemName;
        this.quoteName = quoteName;
        this.price = price;
        this.quantity = quantity;
        this.quoteList = quoteList;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage(
                String.format(
                        "Adding item to quote %s with name %s, price %.2f, quantity %d",
                        quoteName, itemName, price, quantity
                )
        );
        quoteList.addItem(quoteName, itemName, price, quantity);
    }

}
