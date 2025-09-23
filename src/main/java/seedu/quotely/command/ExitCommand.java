package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.exception.QuotelyException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void execute(Ui ui) throws QuotelyException {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
    
}
