package seedu.quotely.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.quotely.command.Command;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

/**
 * Tests for item-related command parsing including add item and delete item
 * commands.
 */
public class ParserItemCommandTest {

    @Test
    public void parseAddItemCommand_validInputInsideQuote_returnAddItemCommand() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setInsideQuote(q);
        quoteList.addQuote(q);
        try {
            Command command = Parser.parse("add i/Item1 p/10.0 q/2", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.AddItemCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseAddItemCommand_validInputOutsideQuote_returnAddItemCommand() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setOutsideQuote();
        quoteList.addQuote(q);
        try {
            Command command = Parser.parse("add i/Item 1 n/quote 1 p/10.0 q/2", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.AddItemCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseAddItemCommand_invalidInputInsideQuote_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setInsideQuote(q);
        quoteList.addQuote(q);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("add i/Item1 p/invalidprice q/2", state, quoteList);
        });
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("add p/invalidprice q/2", state, quoteList);
        });
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("add i/Item1 p/-20 q/2", state, quoteList);
        });
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("add i/Item1 p/2 q/2.5", state, quoteList);
        });
    }

    @Test
    public void parseDeleteItemCommand_validInputInsideQuote_returnDeleteItemCommand() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setInsideQuote(q);
        quoteList.addQuote(q);
        q.addItem("Item1", 10.0, 4, false);
        try {
            Command command = Parser.parse("delete i/Item1", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.DeleteItemCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseDeleteItemCommand_validInputOutsideQuote_returnDeleteItemCommand() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setOutsideQuote();
        quoteList.addQuote(q);
        q.addItem("Item1", 10.0, 4, false);
        try {
            Command command = Parser.parse("delete i/Item1 n/quote 1", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.DeleteItemCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseAddItemCommand_invalidQuoteNameOutsideQuote_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setOutsideQuote();
        quoteList.addQuote(q);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("add n/invalid quote i/Item1 p/10.0 q/2", state, quoteList);
        });
    }

    @Test
    public void parseAddItemCommand_invalidPriceOutsideQuote_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setOutsideQuote();
        quoteList.addQuote(q);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("add n/quote 1 i/Item1 p/-0.3 q/2", state, quoteList);
        });
    }

    @Test
    public void parseAddItemCommand_invalidQuantityOutsideQuote_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setOutsideQuote();
        quoteList.addQuote(q);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("add n/quote 1 i/Item1 p/12.2 q/-10", state, quoteList);
        });
    }

    @Test
    public void parseDeleteItemCommand_noQuoteNameOutsideQuote_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setOutsideQuote();
        quoteList.addQuote(q);
        q.addItem("Item1", 10.0, 4, false);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("delete i/Item1", state, quoteList);
        });
    }

    @Test
    public void parseDeleteItemCommand_invalidItemInsideQuote_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setInsideQuote(q);
        quoteList.addQuote(q);
        q.addItem("Item1", 10.0, 4, false);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("delete i/invalid item", state, quoteList);
        });
    }

    @Test
    public void parseDeleteItemCommand_noArguments_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setInsideQuote(q);
        quoteList.addQuote(q);
        q.addItem("Item1", 10.0, 4, false);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("delete", state, quoteList);
        });
    }
}
