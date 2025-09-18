package seedu.quotely.command;

import seedu.quotely.Ui;

public class DeleteItemCommand extends Command {
    private String itemName;
    private String quoteName;

    public DeleteItemCommand(String itemName, String quoteName) {
        super("deleteItem");
        this.itemName = itemName;
        this.quoteName = quoteName;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Deleting item: " + itemName + " from quote: " + quoteName);
    }
    
}
