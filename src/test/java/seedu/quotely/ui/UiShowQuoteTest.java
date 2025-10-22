package seedu.quotely.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Item;
import seedu.quotely.data.Quote;

/**
 * Unit tests for the {@link Ui#showQuote(CompanyName, Quote, double)} method.
 * These tests verify that the quote display formatting works correctly for both
 * populated and empty quotes.
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
    public void showQuote_withItems_rendersHeaderTableAndTotals() {
        try {
            CompanyName company = new CompanyName("Acme Pte Ltd");
            Quote quote = new Quote("Q-0001", "Jane Doe");

            // Item ctor: (name, price, quantity, isTax)
            quote.getItems().add(new Item("Consultation", 150.0, 2, false));
            quote.getItems().add(new Item(
                    "Extra long description that will be truncated to fit the table width",
                    999.99, 1, false
            ));

            double gstRate = 0.09; // 9%

            // Compute expected strings exactly like Ui.showQuote
            double subtotal = 2 * 150.0 + 999.99;  // 1299.99
            double gst = subtotal * gstRate;        // 116.9991 -> "$117.00"
            double total = subtotal + gst;          // 1416.9891 -> "$1416.99"

            String subStr = String.format("$%.2f", subtotal);
            String gstStr = String.format("$%.2f", gst);
            String totStr = String.format("$%.2f", total);

            Ui.getInstance().showQuote(company, quote, gstRate);
            String s = out.toString();

            // Header
            assertTrue(s.contains("Company name: Acme Pte Ltd"));
            assertTrue(s.contains("Quote ID: Q-0001"));
            assertTrue(s.contains("Customer name: Jane Doe"));

            // An item line should include the name
            assertTrue(s.contains("Consultation"));

            // Totals (use the same formatting as the method)
            assertTrue(s.contains("Subtotal:") && s.contains(subStr),
                    "Expected subtotal not found: " + subStr + "\nOUTPUT:\n" + s);
            assertTrue(s.contains("GST:") && s.contains(gstStr),
                    "Expected GST not found: " + gstStr + "\nOUTPUT:\n" + s);
            assertTrue(s.contains("Total:") && s.contains(totStr),
                    "Expected total not found: " + totStr + "\nOUTPUT:\n" + s);

            // Box marker present
            assertTrue(s.contains("______QUOTE"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void showQuote_noItems_showsNoItemsRowAndZeroTotals() {
        try {
            CompanyName company = new CompanyName("N/A Co.");
            Quote quote = new Quote("EMPTY", "Nobody");

            quote.getItems().clear();

            Ui.getInstance().showQuote(company, quote, 0.08);
            String s = out.toString();

            assertTrue(s.contains("(no items)"));
            assertTrue(s.contains("Subtotal:") && s.contains("$0.00"));
            assertTrue(s.contains("GST:") && s.contains("$0.00"));
            assertTrue(s.contains("Total:") && s.contains("$0.00"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
