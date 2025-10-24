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
 * Unit tests for {@link Ui#showQuote(CompanyName, Quote, double)}.
 * Uses Quote.addItem(...) to avoid depending on Item constructor shape.
 */
public class UiShowQuoteTest {

    // ---- Test constants ----
    private static final String COMPANY = "Acme Pte Ltd";
    private static final String CUSTOMER = "Jane Doe";
    private static final String QUOTE_ID = "Q-0001";
    private static final String LONG_DESC =
            "Extra long description that will be truncated to fit the table width";

    private static final String ITEM1_NAME = "Consultation";
    private static final double ITEM1_PRICE = 150.0;
    private static final int ITEM1_QTY = 2;

    private static final double ITEM2_PRICE = 999.99;
    private static final int ITEM2_QTY = 1;

    private static final double GST_RATE_WITH_ITEMS = 0.09; // 9%
    private static final double GST_RATE_NO_ITEMS = 0.08;   // totals remain $0.00 anyway

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
        // Arrange
        CompanyName company = new CompanyName(COMPANY);
        Quote quote = new Quote(QUOTE_ID, CUSTOMER);

        // Use Quote API (matches your Item/Quote classes)
        quote.addItem(ITEM1_NAME, ITEM1_PRICE, ITEM1_QTY, false);
        quote.addItem(LONG_DESC,    ITEM2_PRICE, ITEM2_QTY, false);

        // Expected totals computed same way as Ui.showQuote formats them
        double subtotal = ITEM1_QTY * ITEM1_PRICE + ITEM2_QTY * ITEM2_PRICE;
        double gst = subtotal * GST_RATE_WITH_ITEMS;
        double total = subtotal + gst;

        String expectedSubtotal = String.format("$%.2f", subtotal);
        String expectedGst = String.format("$%.2f", gst);
        String expectedTotal = String.format("$%.2f", total);

        // Act
        Ui.getInstance().showQuote(company, quote, GST_RATE_WITH_ITEMS);
        String output = out.toString();

        // Assert — header
        assertTrue(output.contains("Company name: " + COMPANY));
        assertTrue(output.contains("Quote ID: " + QUOTE_ID));
        assertTrue(output.contains("Customer name: " + CUSTOMER));

        // Assert — item presence
        assertTrue(output.contains(ITEM1_NAME));

        // Assert — totals
        assertTrue(output.contains("Subtotal:") && output.contains(expectedSubtotal),
                "Expected subtotal not found: " + expectedSubtotal + "\nOUTPUT:\n" + output);
        assertTrue(output.contains("GST:") && output.contains(expectedGst),
                "Expected GST not found: " + expectedGst + "\nOUTPUT:\n" + output);
        assertTrue(output.contains("Total:") && output.contains(expectedTotal),
                "Expected total not found: " + expectedTotal + "\nOUTPUT:\n" + output);

        // Box marker
        assertTrue(output.contains("______QUOTE"));
    }

    @Test
    public void showQuote_noItems_showsNoItemsRowAndZeroTotals() {
        // Arrange
        CompanyName company = new CompanyName("N/A Co.");
        Quote quote = new Quote("EMPTY", "Nobody");

        // Act
        Ui.getInstance().showQuote(company, quote, GST_RATE_NO_ITEMS);
        String output = out.toString();

        // Assert
        assertTrue(output.contains("(no items)"));
        assertTrue(output.contains("Subtotal:") && output.contains("$0.00"));
        assertTrue(output.contains("GST:") && output.contains("$0.00"));
        assertTrue(output.contains("Total:") && output.contains("$0.00"));
    }
}
