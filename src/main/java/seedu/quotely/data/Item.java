package seedu.quotely.data;

public class Item {
    private String itemName;
    private double price;
    private int quantity;
    private boolean isTax;

    public Item(String itemName, double price, int quantity, boolean isTax) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.isTax = isTax;
    }

    public String getItemName() {
        return itemName;
    }

    /**
     * Unused getters and setters for future use
     */

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isTax() {
        return isTax;
    }

    public void setTax(boolean tax) {
        isTax = tax;
    }
}
