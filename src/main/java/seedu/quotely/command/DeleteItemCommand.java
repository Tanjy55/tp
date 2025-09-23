package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.QuoteList;

public class DeleteItemCommand extends Command {
    private String itemName;
    private String quoteName;
    private QuoteList quoteList;

    public DeleteItemCommand(String itemName, String quoteName, QuoteList quoteList) {
        super("deleteItem");
        this.itemName = itemName;
        this.quoteName = quoteName;
        this.quoteList = quoteList;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Deleting item: " + itemName + " from quote: " + quoteName);
        quoteList.removeItem(quoteName, itemName);
    }
}
