package seedu.quotely.command;

import seedu.quotely.Ui;

public class DeleteQuoteCommand extends Command{
    private String quoteName;

    public DeleteQuoteCommand(String quoteName) {
        super("delete");
        this.quoteName = quoteName;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Deleting quote: " + quoteName);
    }
}
