package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;

public class RegisterCommandTest {
    @Test
    public void execute_registerCommand_success() {
        Ui ui = new Ui();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = new QuotelyState();
        RegisterCommand registerCommand = new RegisterCommand("TestCompany");
        try {
            registerCommand.execute(ui, quoteList, companyName, state);
            assertEquals(companyName.getCompanyName(), "TestCompany");
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
