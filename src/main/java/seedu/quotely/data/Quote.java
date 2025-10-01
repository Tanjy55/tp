package seedu.quotely.data;

import java.util.ArrayList;
import java.util.List;

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

    public void removeItem(String itemName) {
        int index = getItemIndex(itemName);
        items.remove(index);
    }

    public void addItem(String itemName, double price, int quantity) {
        items.add(new Item(itemName, price, quantity));
    }

    private int getItemIndex(String itemName) {
        int index = 0;
        for (Item i : items) {
            if (i.getItemName().equals(itemName)) {
                return index;
            }
            index++;
        }
        return -1; //exception to be implemented
    }
}
