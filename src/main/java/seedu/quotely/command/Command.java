package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.exception.QuotelyException;

public abstract class Command {
    protected String commandWord;

    public Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public abstract void execute(Ui ui) throws QuotelyException;

    public boolean isExit() {
        return false;
    }
    
}
