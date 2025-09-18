package seedu.quotely.command;

import seedu.quotely.Ui;

public class ShowQuotesCommand extends Command {
    public ShowQuotesCommand() {
        super("show");
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Showing all quotes...");
    }
    
}
