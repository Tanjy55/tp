package seedu.quotely.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.data.Quote;

public class NavigateCommandTest {

    // Helper method to set up initial state
    private void setupInitialState(QuoteList quoteList, QuotelyState state, Quote currentQuote) {
        quoteList.addQuote(currentQuote);
        state.setOutsideQuote();
    }

    // Helper method to set up inside-quote state
    private void setupInsideQuoteState(QuoteList quoteList, QuotelyState state, Quote insideQuote) {
        quoteList.addQuote(insideQuote);
        state.setInsideQuote(insideQuote);
    }

    @Test
    public void execute_navigateToQuoteFromOutside_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote targetQuote = new Quote("TargetQuote", "TargetCustomer");
        setupInitialState(quoteList, state, targetQuote);

        try {
            NavigateCommand navigateCommand = new NavigateCommand(targetQuote);
            navigateCommand.execute(ui, quoteList, companyName, state);
            assertFalse(navigateCommand.isExit());
            assertTrue(state.isInsideQuote());
            assertEquals(targetQuote, state.getQuoteReference());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }


    @Test
    public void execute_navigateToSameQuote_noChange() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote currentQuote = new Quote("CurrentQuote", "CurrentCustomer");
        setupInsideQuoteState(quoteList, state, currentQuote);

        try {
            NavigateCommand navigateCommand = new NavigateCommand(currentQuote);
            navigateCommand.execute(ui, quoteList, companyName, state);
            assertFalse(navigateCommand.isExit());
            assertTrue(state.isInsideQuote());
            assertEquals(currentQuote, state.getQuoteReference());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }

    @Test
    public void execute_navigateToDifferentQuote_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote oldQuote = new Quote("OldQuote", "OldCustomer");
        Quote newQuote = new Quote("NewQuote", "NewCustomer");
        setupInsideQuoteState(quoteList, state, oldQuote);
        quoteList.addQuote(newQuote);

        try {
            NavigateCommand navigateCommand = new NavigateCommand(newQuote);
            navigateCommand.execute(ui, quoteList, companyName, state);
            assertFalse(navigateCommand.isExit());
            assertTrue(state.isInsideQuote());
            assertEquals(newQuote, state.getQuoteReference());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }

    @Test
    public void execute_navigateToMainMenuFromQuote_success() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        Quote currentQuote = new Quote("CurrentQuote", "CurrentCustomer");
        setupInsideQuoteState(quoteList, state, currentQuote);

        try {
            NavigateCommand navigateCommand = new NavigateCommand(); // Nav to main menu
            navigateCommand.execute(ui, quoteList, companyName, state);
            assertFalse(navigateCommand.isExit());
            assertFalse(state.isInsideQuote());
            assertNull(state.getQuoteReference());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }

    @Test
    public void execute_navigateToMainMenuWhenAlreadyOutside_noChange() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        state.setOutsideQuote(); // Already outside

        try {
            NavigateCommand navigateCommand = new NavigateCommand(); // Nav to main menu
            navigateCommand.execute(ui, quoteList, companyName, state);
            assertFalse(navigateCommand.isExit());
            assertFalse(state.isInsideQuote());
            assertNull(state.getQuoteReference());
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
