package seedu.quotely.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {
    @Test
    void item_validInput_constructorSuccess() {
        Item item = new Item("test", 1.0, 1, false);
        try {
            assertInstanceOf(Item.class, item);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getItemName_validInput_returnItemName() {
        Item item = new Item("test2", 2.0, 2, false);
        try {
            assertEquals("test2", item.getItemName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setItemName_validInput_success() {
        Item item = new Item("default", 3.0, 3, false);
        try {
            item.setItemName("test3");
            assertEquals("test3", item.getItemName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getPrice_validInput_returnPrice() {
        Item item = new Item("test4", 4.0, 4, false);
        try {
            assertEquals(4.0, item.getPrice());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setPrice_validInput_success() {
        Item item = new Item("test5", 0.0, 5, false);
        try {
            item.setPrice(5.0);
            assertEquals(5.0, item.getPrice());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getQuantity_validInput_success() {
        Item item = new Item("test6", 6.0, 6, false);
        try {
            assertEquals(6, item.getQuantity());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setQuantity_validInput_success() {
        Item item = new Item("test7", 7.0, 0, false);
        try {
            item.setQuantity(7);
            assertEquals(7, item.getQuantity());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void isTax_validInput_success() {
        Item item1 = new Item("test8", 8.0, 8, false);
        Item item2 = new Item("test9", 8.0, 8, true);
        try {
            assertFalse(item1.isTax());
            assertTrue(item2.isTax());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setTax_validInput_success() {
        Item item = new Item("test10", 8.0, 8, false);
        try {
            assertFalse(item.isTax());
            item.setTax(true);
            assertTrue(item.isTax());
            item.setTax(false);
            assertFalse(item.isTax());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
