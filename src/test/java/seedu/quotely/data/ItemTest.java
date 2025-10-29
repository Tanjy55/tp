package seedu.quotely.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    private static final double NO_TAX = 0.0;
    private static final double TEST_TAX_RATE = 5.0;

    @Test
    void item_validInput_constructorSuccess() {
        Item item = new Item("test", 1.0, 1, NO_TAX);
        try {
            assertInstanceOf(Item.class, item);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getItemName_validInput_returnItemName() {
        Item item = new Item("test2", 2.0, 2, NO_TAX);
        try {
            assertEquals("test2", item.getItemName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setItemName_validInput_success() {
        Item item = new Item("default", 3.0, 3, NO_TAX);
        try {
            item.setItemName("test3");
            assertEquals("test3", item.getItemName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getPrice_validInput_returnPrice() {
        Item item = new Item("test4", 4.0, 4, NO_TAX);
        try {
            assertEquals(4.0, item.getPrice());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setPrice_validInput_success() {
        Item item = new Item("test5", 0.0, 5, NO_TAX);
        try {
            item.setPrice(5.0);
            assertEquals(5.0, item.getPrice());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getQuantity_validInput_success() {
        Item item = new Item("test6", 6.0, 6, NO_TAX);
        try {
            assertEquals(6, item.getQuantity());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setQuantity_validInput_success() {
        Item item = new Item("test7", 7.0, 0, NO_TAX);
        try {
            item.setQuantity(7);
            assertEquals(7, item.getQuantity());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void hasTax_validInput_success() {
        Item item1 = new Item("test8", 8.0, 8, NO_TAX);
        Item item2 = new Item("test9", 8.0, 8, TEST_TAX_RATE);
        try {
            assertFalse(item1.hasTax());
            assertTrue(item2.hasTax());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setTax_validInput_success() {
        Item item = new Item("test10", 8.0, 8, NO_TAX);
        try {
            assertFalse(item.hasTax());
            assertEquals(NO_TAX, item.getTaxRate());

            item.setTax(TEST_TAX_RATE);
            assertTrue(item.hasTax());
            assertEquals(TEST_TAX_RATE, item.getTaxRate());

            item.setTax(NO_TAX);
            assertFalse(item.hasTax());
            assertEquals(NO_TAX, item.getTaxRate());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getItemTotalPriceWithoutTax_validInput_correctCalculation() {
        Item item = new Item("calc1", 10.0, 3, NO_TAX);
        try {
            // Expected: 10.0 * 3 = 30.0
            assertEquals(30.0, item.getItemTotalPriceWithoutTax(), 0.001);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getItemTotalTax_validInput_correctCalculation() {
        Item item = new Item("calc2", 100.0, 2, 10.0);
        try {
            assertEquals(20.0, item.getItemTotalTax(), 0.001);

            Item item2 = new Item("calc3", 100.0, 2, NO_TAX);
            assertEquals(0.0, item2.getItemTotalTax(), 0.001);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
