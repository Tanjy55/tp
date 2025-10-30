package seedu.quotely.data;

public class Item {
    private String itemName;
    private double price;
    private int quantity;
    private double taxRate;

    public Item(String itemName, double price, int quantity, double taxRate) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.taxRate = taxRate;
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

    public boolean hasTax() {
        return taxRate > 0;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTax(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getItemTotalPriceWithoutTax() {
        return quantity * price;
    }

    public double getItemTotalTax() {
        return quantity * price * taxRate / 100.0;
    }

    public boolean isValid() {
        return itemName != null && !itemName.trim().isEmpty()
                && price >= 0
                && quantity > 0
                && taxRate >= 0;
    }
}
