package seedu.quotely.data;

import java.util.ArrayList;
import java.util.List;

import seedu.quotely.exception.QuotelyException;

public class Quote {
    private String quoteName;
    private String customerName;
    private List<Item> items = new ArrayList<>();

    public Quote(String quoteName, String customerName) {
        this.customerName = customerName;
        this.quoteName = quoteName;
    }

    public String getQuoteName() {
        return quoteName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getQuoteTotal()  {
        double total = 0;
        for (Item item : items) {
            double itemTotal = (double) item.getQuantity() * item.getPrice();
            total += itemTotal;
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public void removeItem(String itemName) throws QuotelyException {
        int index = getItemIndex(itemName);
        items.remove(index);
    }

    public void addItem(String itemName, double price, int quantity) {
        items.add(new Item(itemName, price, quantity));
    }

    private int getItemIndex(String itemName) throws QuotelyException {
        int index = 0;
        for (Item i : items) {
            if (i.getItemName().equals(itemName)) {
                return index;
            }
            index++;
        }
        throw new QuotelyException(QuotelyException.ErrorType.ITEM_NOT_FOUND);
    }

    public boolean hasItem(String itemName) {
        for (Item i : items) {
            if (i.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return quoteName;
    }
}
