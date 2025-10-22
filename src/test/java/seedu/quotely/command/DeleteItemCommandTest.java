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

public class DeleteItemCommandTest {
    @Test
    public void execute_deleteItemCommand_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote quote = new Quote("TestQuote", "TestCustomer");
        quoteList.addQuote(quote);
        quote.addItem("Item1", 10.0, 2, 0);
        try {
            DeleteItemCommand deleteItemCommand = new DeleteItemCommand("Item1", quote);
            deleteItemCommand.execute(ui, quoteList, companyName, state);
            assertFalse(deleteItemCommand.isExit());
            assertEquals(0, quote.getItems().size());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
