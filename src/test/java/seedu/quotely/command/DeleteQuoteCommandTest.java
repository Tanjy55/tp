package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.data.Quote;

public class DeleteQuoteCommandTest {
    @Test
    public void execute_deleteQuoteCommand_success() {
        Ui ui = new Ui();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = new QuotelyState();
        Quote quote = new Quote("TestQuote", "TestCustomer");
        quoteList.addQuote(quote);
        state.setInsideQuote(quote);
        try {
            DeleteQuoteCommand deleteQuoteCommand = new DeleteQuoteCommand(quote);
            deleteQuoteCommand.execute(ui, quoteList, companyName, state);
            assertEquals(0, quoteList.getQuotes().size());
            assertEquals(state.getQuoteReference(), null);
            assert !state.isInsideQuote();
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
