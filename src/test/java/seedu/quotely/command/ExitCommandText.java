package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandText {
    @Test
    public void execute_exitCommand_success() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }
}
