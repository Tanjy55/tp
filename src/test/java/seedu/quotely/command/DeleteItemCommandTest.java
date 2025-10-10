package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.data.Quote;

public class DeleteItemCommandTest {
    @Test
    public void execute_deleteItemCommand_success() {
        Ui ui = new Ui();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = new QuotelyState();
        Quote quote = new Quote("TestQuote", "TestCustomer");
        quoteList.addQuote(quote);
        quote.addItem("Item1", 10.0, 2);
        try {
            DeleteItemCommand deleteItemCommand = new DeleteItemCommand("Item1", quote);
            deleteItemCommand.execute(ui, quoteList, companyName, state);
            assertEquals(0, quote.getItems().size());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
