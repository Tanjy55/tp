package seedu.quotely.command;

import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class ExitCommand extends Command {
    private static final String COMMAND_WORD = "exit";

    public ExitCommand() {
        super(COMMAND_WORD);
    }

    @Override
    public void execute(Ui ui,
            QuoteList quoteList,
            CompanyName companyName,
            QuotelyState state) throws QuotelyException {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
