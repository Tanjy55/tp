package seedu.quotely.command;

import seedu.quotely.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class RegisterCommand extends Command {
    private final String newName;

    public RegisterCommand(String name) {
        super("register");
        this.newName = name;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName) throws QuotelyException {
        ui.showMessage("Registering company: " + newName);
        companyName.setCompanyName(newName);
    }
}
