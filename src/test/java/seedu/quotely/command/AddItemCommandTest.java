package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.data.Quote;
import seedu.quotely.data.Item;

public class AddItemCommandTest {
    @Test
    public void execute_addItemCommand_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        try {
            Quote quote = new Quote("TestQuote", "TestCustomer");
            quoteList.addQuote(quote);
            state.setInsideQuote(quote);
            AddItemCommand addItemCommand = new AddItemCommand("TestItem", quote, 10.0, 2, 0);
            addItemCommand.execute(ui, quoteList, companyName, state);
            assertEquals(1, quote.getItems().size());
            Item item = quote.getItems().get(0);
            assertEquals("TestItem", item.getItemName());
            assertEquals(10.0, item.getPrice());
            assertEquals(2, item.getQuantity());
            assertFalse(item.hasTax());
            assertFalse(addItemCommand.isExit());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
