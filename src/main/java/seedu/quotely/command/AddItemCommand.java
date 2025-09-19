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
        ui.showMessage(
            String.format(
            "Adding item to quote %s with name %s, price %.2f, quantity %d", 
            quoteName, itemName, price, quantity
            )
        );
    }
    
}
