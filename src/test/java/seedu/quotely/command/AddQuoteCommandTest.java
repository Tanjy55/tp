package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class AddQuoteCommandTest {
    @Test
    public void execute_addQuoteCommand_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        try {
            state.setOutsideQuote();
            AddQuoteCommand addQuoteCommand = new AddQuoteCommand("TestQuote", "TestCustomer");
            addQuoteCommand.execute(ui, quoteList, companyName, state);
            assertFalse(addQuoteCommand.isExit());
            assertEquals(1, quoteList.getQuotes().size());
            assertEquals("TestQuote", quoteList.getQuotes().get(0).getQuoteName());
            assertEquals("TestCustomer", quoteList.getQuotes().get(0).getCustomerName());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
