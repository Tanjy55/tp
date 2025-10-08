package seedu.quotely.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.quotely.command.Command;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;

/**
 * Tests for general Parser functionality including unknown commands and basic
 * parsing behavior.
 */
public class ParserGeneralTest {

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
}
