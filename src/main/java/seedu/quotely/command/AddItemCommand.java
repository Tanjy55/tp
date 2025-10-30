package seedu.quotely.command;

import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.util.LoggerConfig;

import java.util.logging.Logger;

public class AddItemCommand extends Command {
    private static final String COMMAND_WORD = "addItem";
    private static final Logger logger = LoggerConfig.getLogger(AddItemCommand.class);
    private Quote quote;
    private String itemName;
    private Double price;
    private int quantity;
    private double taxRate;

    public AddItemCommand(String itemName, Quote quote, Double price, int quantity, double taxRate) {
        super(COMMAND_WORD);
        this.itemName = itemName;
        this.quote = quote;
        this.price = price;
        this.quantity = quantity;
        this.taxRate = taxRate;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        assert this.quote != null : "Quote reference disappeared during execution.";
        assert this.price >= 0 && this.quantity > 0 : "Invalid price or quantity detected.";

        logger.fine(String.format(
                "Executing AddItemCommand for item %s to quote %s with price %.2f, quantity %d, tax %.2f%%",
                itemName, quote.getQuoteName(), price, quantity, taxRate));

        ui.showMessage(
                String.format(
                        "Adding %s to quote %s with price %.2f, quantity %d, tax %.2f%%",
                        itemName, quote.getQuoteName(), price, quantity, taxRate));
      
        quote.addItem(itemName, price, quantity, taxRate);

        logger.fine(String.format(
                "Successfully added item %s to quote %s",
                itemName, quote.getQuoteName()));
    }
}
