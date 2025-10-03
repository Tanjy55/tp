package seedu.quotely.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.quotely.command.Command;
import seedu.quotely.command.FinishQuoteCommand;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class ParserTest {
    @Test
    public void parseUnknownCommand_invalidInput_throwException() {
        QuotelyState state = new QuotelyState();
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("unknowncommand", state, null);
        });
    }

    @Test
    public void parseExitCommand_validInput_returnExitCommand() {
        QuotelyState state = new QuotelyState();
        try {
            Command command = Parser.parse("exit", state, null);
            assertTrue(command instanceof seedu.quotely.command.ExitCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseFinishQuoteCommand_insideQuoteState_returnFinishQuoteCommand() {
        QuotelyState state = new QuotelyState();
        Quote q = new Quote("quote name", "customer name");
        state.setInsideQuote(q);

        try {
            Command command = Parser.parse("finish", state, null);
            assertTrue(command instanceof FinishQuoteCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseFinishQuoteCommand_outsideQuoteState_throwException() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("finish", state, null);
        });
    }

    @Test
    public void parseTotalQuoteCommand_insideQuote_returnTotalCommand() {
        QuotelyState state = new QuotelyState();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote name", "customer name");
        quoteList.addQuote(q);
        state.setInsideQuote(q);
        try {
            Command command = Parser.parse("total", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.CalculateTotalCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseTotalQuoteCommand_outsideQuoteWithQuoteName_returnTotalCommand() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote name", "customer name");
        quoteList.addQuote(q);
        try {
            Command command = Parser.parse("total n/quote name", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.CalculateTotalCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseTotalQuoteCommand_outsideQuoteWithInvalidQuoteName_throwException() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote name", "customer name");
        quoteList.addQuote(q);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("total n/invalid quote name", state, quoteList);
        });
    }

    @Test
    public void parseTotalQuoteCommand_outsideQuoteNoQuoteName_throwException() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote name", "customer name");
        quoteList.addQuote(q);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("total", state, quoteList);
        });
    }

    @Test
    public void parseRegisterCommand_validInput_returnRegisterCommand() {
        try {
            Command command = Parser.parse("register c/Customer Name", null, null);
            assertTrue(command instanceof seedu.quotely.command.RegisterCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseRegisterCommand_invalidInput_throwException() {
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("register invalidinput", null, null);
        });
    }

    @Test
    public void parseShowQuotesCommand_validInput_returnShowQuotesCommand() {
        try {
            Command command = Parser.parse("show", null, null);
            assertTrue(command instanceof seedu.quotely.command.ShowQuotesCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseAddQuoteCommand_validInput_returnAddQuoteCommand() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        try {
            Command command = Parser.parse("quote n/Quote Name c/Customer Name", state, null);
            assertTrue(command instanceof seedu.quotely.command.AddQuoteCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseAddQuoteCommand_withoutCustomerName_throwException() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("quote n/Quote Name", state, null);
        });
    }

    @Test
    public void parseAddQuoteCommand_withoutQuoteName_throwException() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("quote c/Customer Name", state, null);
        });
    }

    @Test
    public void parseAddQuoteCommand_insideQuoteState_throwException() {
        QuotelyState state = new QuotelyState();
        Quote q = new Quote("quote name", "customer name");
        state.setInsideQuote(q);
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("quote n/Quote Name c/Customer Name", state, null);
        });
    }

    @Test
    public void parseDeleteQuoteCommand_validInputOutsideQuote_returnDeleteQuoteCommand() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        quoteList.addQuote(q);
        try {
            Command command = Parser.parse("unquote n/quote 1", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.DeleteQuoteCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseDeleteQuoteCommand_validInputinsideQuote_returnDeleteQuoteCommand() {
        QuotelyState state = new QuotelyState();
        QuoteList quoteList = new QuoteList();
        Quote q = new Quote("quote 1", "customer 1");
        state.setInsideQuote(q);
        quoteList.addQuote(q);
        try {
            Command command = Parser.parse("unquote", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.DeleteQuoteCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void parseDeleteQuoteCommand_invalidQuoteName_throwException() {
        QuotelyState state = new QuotelyState();
        state.setOutsideQuote();
        QuoteList quoteList = new QuoteList();
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("unquote n/invalid quote", state, quoteList);
        });
    }
}
