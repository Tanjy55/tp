package seedu.quotely.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QuotelyStateTest {
    @Test
    void quotelyState_validInput_success() {
        QuotelyState quotelyState = QuotelyState.getInstance();
        try {
            assertInstanceOf(QuotelyState.class, quotelyState);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    /**
     * tests setInsideQuote and setOutsideQuote within this test
     */
    @Test
    void isInsideQuote_validInput_success() {
        QuotelyState quotelyState = QuotelyState.getInstance();
        quotelyState.setOutsideQuote();
        try {
            assertFalse(quotelyState.isInsideQuote());
            Quote quote = new Quote("test", "testCustomer");
            quotelyState.setInsideQuote(quote);
            assertTrue(quotelyState.isInsideQuote());
            quotelyState.setOutsideQuote();
            assertFalse(quotelyState.isInsideQuote());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    /**
     * tests setQuoteReference within this test
     */
    @Test
    void getQuoteReference_validInput_returnQuoteReference() {
        QuotelyState quotelyState = QuotelyState.getInstance();
        quotelyState.setOutsideQuote();
        try {
            assertNull(quotelyState.getQuoteReference());
            Quote quote = new Quote("test", "testCustomer");
            quotelyState.setQuoteReference(quote);
            assertEquals(quote, quotelyState.getQuoteReference());
            quotelyState.setOutsideQuote();
            assertNull(quotelyState.getQuoteReference());
            quotelyState.setInsideQuote(quote);
            assertEquals(quote, quotelyState.getQuoteReference());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
