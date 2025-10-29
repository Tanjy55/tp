package seedu.quotely.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchQuoteCommandTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void searchQuoteCommandTest_validInput_quotesPrinted() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        String output;

        Quote quote1 = new Quote("quote1", "c1");
        Quote quote2 = new Quote("quote2", "c2");
        Quote quote3 = new Quote("quote3", "c3");

        try {
            quoteList.addQuote(quote1);
            SearchQuoteCommand searchQuoteCommand1 = new SearchQuoteCommand("1");
            searchQuoteCommand1.execute(ui, quoteList, companyName, state);
            output = out.toString();
            assertTrue(output.contains("quote1"));
            assertTrue(output.contains("c1"));

            quoteList.addQuote(quote2);
            quoteList.addQuote(quote3);
            SearchQuoteCommand searchQuoteCommand2 = new SearchQuoteCommand("quote");
            searchQuoteCommand2.execute(ui, quoteList, companyName, state);
            output = out.toString();
            assertTrue(output.contains("quote1"));
            assertTrue(output.contains("quote2"));
            assertTrue(output.contains("quote3"));

        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }

    @Test
    public void searchQuoteCommandTest_invalidInput_printNotFound() {
        Ui ui = Ui.getInstance();
        QuoteList quoteList = new QuoteList();
        CompanyName companyName = new CompanyName("default");
        QuotelyState state = QuotelyState.getInstance();
        String output;

        try {
            SearchQuoteCommand searchQuoteCommand1 = new SearchQuoteCommand("1");
            searchQuoteCommand1.execute(ui, quoteList, companyName, state);
            output = out.toString();
            assertTrue(output.contains("No matching quote found"));

            SearchQuoteCommand searchQuoteCommand2 = new SearchQuoteCommand("-1*&!@I*()%^%$!#@!#%&%");
            searchQuoteCommand2.execute(ui, quoteList, companyName, state);
            output = out.toString();
            assertTrue(output.contains("No matching quote found"));
        } catch (QuotelyException e) {
            assert false : "Execution should not fail.";
        }
    }
}
