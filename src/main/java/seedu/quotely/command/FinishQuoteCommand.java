package seedu.quotely.command;

import seedu.quotely.Ui;

public class FinishQuoteCommand extends Command {
    public FinishQuoteCommand() {
        super("finish");
    }
    @Override
    public void execute(Ui ui) {
        ui.showMessage("Finishing quote process.");
    }
    
}
