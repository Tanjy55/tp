package seedu.quotely.data;

import java.util.ArrayList;
import java.util.List;

public class QuoteList {
    private List<Quote> quotes = new ArrayList<>();

    public QuoteList() {
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    public void removeQuote(String quoteName) {
        int index = getQuoteIndex(quoteName);
        quotes.remove(index);
    }

    private int getQuoteIndex(String quoteName) {
        int index = 0;
        for (Quote q : quotes) {
            if (q.getQuoteName().equals(quoteName)) {
                return index;
            }
            index++;
        }
        return -1; //exception to be implemented
    }

    public double calculateQuoteTotal(String quoteName, String customerName) {
        for (Quote q : quotes) {
            if (q.getQuoteName().equals(quoteName) && q.getCustomerName().equals(customerName)) {
                return q.getQuoteTotal();
            }
        }
        return -1;
    }

    public void removeItem(String quoteName, String itemName) {
        int index = getQuoteIndex(quoteName);
        quotes.get(index).removeItem(itemName);
    }

    public void addItem(String quoteName, String itemName, double price, int quantity) {
        int index = getQuoteIndex(quoteName);
        quotes.get(index).addItem(itemName, price, quantity);
    }
}
