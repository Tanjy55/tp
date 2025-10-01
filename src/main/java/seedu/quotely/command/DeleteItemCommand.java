package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
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
                        CompanyName companyName) throws QuotelyException {
        ui.showMessage("Deleting item: " + itemName + " from quote: " + quoteName);
        quoteList.removeItem(quoteName, itemName);
    }
}
