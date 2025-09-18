package seedu.quotely.command;

import seedu.quotely.Ui;

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
    public void execute(Ui ui) {
        ui.showMessage("Adding an item to quote: " + itemName + " to " + quoteName + " with price " + price + " and quantity " + quantity);
    }
    
}
