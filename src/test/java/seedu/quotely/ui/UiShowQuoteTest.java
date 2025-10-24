package seedu.quotely.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;

/**
 * Test for Ui.showQuote without adding items.
 */
public class UiShowQuoteTest {

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
    public void showQuote_noItems_printsHeaderAndZeroTotals() {
        CompanyName company = new CompanyName("Acme Pte Ltd");
        Quote quote = new Quote("Q-0001", "John Doe");

        Ui.getInstance().showQuote(company, quote, 0.09);
        String output = out.toString();

        // Verify header
        assertTrue(output.contains("Company name: Acme Pte Ltd"));
        assertTrue(output.contains("Quote ID: Q-0001"));
        assertTrue(output.contains("Customer name: John Doe"));

        // Verify totals
        assertTrue(output.contains("Subtotal:"));
        assertTrue(output.contains("$0.00"));
        assertTrue(output.contains("GST:"));
        assertTrue(output.contains("Total:"));
    }
}
