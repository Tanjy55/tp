package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;

public class RegisterCommand extends Command {
    private final String newName;
    private final CompanyName companyName;

    public RegisterCommand(String name, CompanyName companyName) {
        super("register");
        this.newName = name;
        this.companyName = companyName;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Registering company: " + newName);
        companyName.setCompanyName(newName);
    }
}
