package seedu.quotely.command;

import seedu.quotely.Ui;

public class RegisterCommand extends Command {
    private String companyName;

    public RegisterCommand(String companyName) {
        super("register");
        this.companyName = companyName;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Registering company: " + companyName);
    }
}
