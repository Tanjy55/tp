package seedu.quotely.data;

import org.junit.jupiter.api.Test;
import seedu.quotely.exception.QuotelyException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class QuoteTest {
    private static final double NO_TAX = 0.0;
    private static final double TEST_TAX_RATE_1 = 10.0;
    private static final double TEST_TAX_RATE_2 = 5.0;

    @Test
    void quote_validInput_constructorSuccess() {
        Quote quote = new Quote("test1", "customer1");
        try {
            assertInstanceOf(Quote.class, quote);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getQuoteName_validInput_returnQuoteName() {
        Quote quote = new Quote("test2", "customer2");
        try {
            assertEquals("test2", quote.getQuoteName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getCustomerName_validInput_returnCustomerName() {
        Quote quote = new Quote("test3", "customer3");
        try {
            assertEquals("customer3", quote.getCustomerName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void addItem_validInput_success() {
        Quote quote = new Quote("test4", "customer4");
        try {
            assertEquals(0, quote.getItems().size());
            quote.addItem("item1", 1.0, 48, NO_TAX);
            assertInstanceOf(Item.class, quote.getItems().get(0));
            assertEquals(1, quote.getItems().size());
            quote.addItem("item2", 9.86, 1, NO_TAX);
            assertInstanceOf(Item.class, quote.getItems().get(1));
            assertEquals(2, quote.getItems().size());
            quote.addItem("item3", 1328.1, 6, NO_TAX);
            assertInstanceOf(Item.class, quote.getItems().get(2));
            assertEquals(3, quote.getItems().size());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getItems_validInput_returnItemsArrayList() {
        Quote quote = new Quote("test5", "customer5");
        try {
            List<Item> items = new ArrayList<Item>();
            items.add(new Item("item1", 1.0, 2, TEST_TAX_RATE_1));
            items.add(new Item("item2", 5.6, 5, NO_TAX));
            items.add(new Item("item3", 9.9, 3, TEST_TAX_RATE_2));
            items.add(new Item("item4", 132870.0, 827312, NO_TAX));
            quote.addItem("item1", 1.0, 2, TEST_TAX_RATE_1);
            quote.addItem("item2", 5.6, 5, NO_TAX);
            quote.addItem("item3", 9.9, 3, TEST_TAX_RATE_2);
            quote.addItem("item4", 132870.0, 827312, NO_TAX);

            for (int i = 0; i < 4; i++) {
                assertEquals(items.get(i).getItemName(), quote.getItems().get(i).getItemName());
                assertEquals(items.get(i).getQuantity(), quote.getItems().get(i).getQuantity());
                assertEquals(items.get(i).getPrice(), quote.getItems().get(i).getPrice());
                assertEquals(items.get(i).getTaxRate(), quote.getItems().get(i).getTaxRate());
            }
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getQuoteTotal_validInput_returnQuoteTotal() {
        Quote quote = new Quote("test6", "customer6");
        try {
            quote.addItem("item1", 1.0, 48, NO_TAX);
            quote.addItem("item2", 9.86, 1, TEST_TAX_RATE_1);
            quote.addItem("item3", 1328.1, 6, TEST_TAX_RATE_2);
            assertEquals(8425.88, quote.getQuoteTotal(), 0.001);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    /**
     * Tests private method getItemIndex implicitly
     */
    @Test
    void removeItem_validInput_removedItemFromQuote() {
        Quote quote = new Quote("test7", "customer7");
        try {
            quote.addItem("item1", 1.0, 48, NO_TAX);
            quote.addItem("item2", 9.86, 1, TEST_TAX_RATE_1);
            quote.addItem("item3", 1328.1, 6, TEST_TAX_RATE_2);
            assertEquals(3, quote.getItems().size());
            quote.removeItem("item1");
            assertEquals(2, quote.getItems().size());
            quote.removeItem("item2");
            assertEquals(1, quote.getItems().size());
            quote.removeItem("item3");
            assertEquals(0, quote.getItems().size());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void removeItem_invalidInput_throwException() {
        Quote quote = new Quote("test8", "customer8");
        try {
            assertThrows(QuotelyException.class, () -> quote.removeItem("item1"));
            quote.addItem("item1", 1.0, 48, NO_TAX);
            assertThrows(QuotelyException.class, () -> quote.removeItem("item2"));
            quote.addItem("item2", 9.86, 1, TEST_TAX_RATE_1);
            assertThrows(QuotelyException.class, () -> quote.removeItem("item3"));
            quote.addItem("item3", 1328.1, 6, TEST_TAX_RATE_2);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void hasItem_validInput_returnBoolean() {
        Quote quote = new Quote("test9", "customer9");
        try {
            quote.addItem("item1", 1.0, 48, NO_TAX);
            assertTrue(quote.hasItem("item1"));
            assertFalse(quote.hasItem("item2"));
            quote.addItem("item2", 9.86, 1, TEST_TAX_RATE_1);
            assertTrue(quote.hasItem("item2"));
            assertFalse(quote.hasItem("item3"));
            quote.addItem("item3", 1328.1, 6, TEST_TAX_RATE_2);
            assertTrue(quote.hasItem("item3"));
            assertFalse(quote.hasItem("item4"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void toString_validInput_returnQuoteNameAsString() {
        Quote quote = new Quote("test10", "customer10");
        try {
            assertEquals("test10", quote.toString());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
