package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.data.Quote;

public class DeleteQuoteCommandTest {
    @Test
    public void execute_deleteCurrentQuote_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote quote = new Quote("TestQuote", "TestCustomer");
        quoteList.addQuote(quote);
        state.setInsideQuote(quote);
        try {
            DeleteQuoteCommand deleteQuoteCommand = new DeleteQuoteCommand(quote);
            deleteQuoteCommand.execute(ui, quoteList, companyName, state);
            assertFalse(deleteQuoteCommand.isExit());
            assertEquals(0, quoteList.getQuotes().size());
            assertNull(state.getQuoteReference());
            assertFalse(state.isInsideQuote());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }

    @Test
    public void execute_deleteOtherQuote_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote quote = new Quote("TestQuote", "TestCustomer");
        Quote quote2 = new Quote("AnotherQuote", "AnotherCustomer");
        quoteList.addQuote(quote);
        quoteList.addQuote(quote2);
        state.setInsideQuote(quote);
        try {
            DeleteQuoteCommand deleteQuoteCommand = new DeleteQuoteCommand(quote2);
            deleteQuoteCommand.execute(ui, quoteList, companyName, state);
            assertFalse(deleteQuoteCommand.isExit());
            assertEquals(1, quoteList.getQuotes().size());
            assertEquals(quoteList.getQuotes(), java.util.List.of(quote));
            assertTrue(state.isInsideQuote());
            assertEquals(state.getQuoteReference(), quote);
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }

    @Test
    public void execute_deleteQuoteOutside_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote quote = new Quote("TestQuote", "TestCustomer");
        quoteList.addQuote(quote);
        state.setOutsideQuote();
        try {
            DeleteQuoteCommand deleteQuoteCommand = new DeleteQuoteCommand(quote);
            deleteQuoteCommand.execute(ui, quoteList, companyName, state);
            assertFalse(deleteQuoteCommand.isExit());
            assertEquals(0, quoteList.getQuotes().size());
            assertFalse(state.isInsideQuote());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
