package seedu.quotely.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ItemTest {
    @Test
    void item_validInput_constructorSuccess() {
        Item item = new Item("test", 1.0, 1);
        try {
            assertInstanceOf(Item.class, item);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getItemName_validInput_returnItemName() {
        Item item = new Item("test2", 2.0, 2);
        try {
            assertEquals("test2", item.getItemName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setItemName_validInput_success() {
        Item item = new Item("default", 3.0, 3);
        try {
            item.setItemName("test3");
            assertEquals("test3", item.getItemName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getPrice_validInput_returnPrice() {
        Item item = new Item("test4", 4.0, 4);
        try {
            assertEquals(4.0, item.getPrice());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setPrice_validInput_success() {
        Item item = new Item("test5", 0.0, 5);
        try {
            item.setPrice(5.0);
            assertEquals(5.0, item.getPrice());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getQuantity_validInput_success() {
        Item item = new Item("test6", 6.0, 6);
        try {
            assertEquals(6, item.getQuantity());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void setQuantity_validInput_success() {
        Item item = new Item("test7", 7.0, 0);
        try {
            item.setQuantity(7);
            assertEquals(7, item.getQuantity());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
