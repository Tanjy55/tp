package seedu.quotely.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.quotely.command.Command;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;

/**
 * Tests for general Parser functionality including unknown commands and basic
 * parsing behavior.
 */
public class ParserGeneralTest {

    @Test
    public void parseUnknownCommand_invalidInput_throwException() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        assertThrows(QuotelyException.class, () -> {
            Parser.parse("unknowncommand", state, quoteList);
        });
    }

    @Test
    public void parseExitCommand_validInput_returnExitCommand() {
        QuotelyState state = QuotelyState.getInstance();
        QuoteList quoteList = new QuoteList();
        try {
            Command command = Parser.parse("exit", state, quoteList);
            assertTrue(command instanceof seedu.quotely.command.ExitCommand);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
