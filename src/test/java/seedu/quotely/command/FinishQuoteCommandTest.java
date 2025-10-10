package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.data.Quote;

public class FinishQuoteCommandTest {
    @Test
    public void execute_finishQuoteCommand_success() {
        Ui ui = new Ui();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("TestCompany");
        QuotelyState state = new QuotelyState();
        Quote quote = new Quote("Test quote", "Test customer");
        state.setInsideQuote(quote);
        FinishQuoteCommand finishQuoteCommand = new FinishQuoteCommand();
        try {
            finishQuoteCommand.execute(ui, quoteList, companyName, state);
            assert !state.isInsideQuote();
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
