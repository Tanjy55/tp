package seedu.quotely.data;

import java.util.ArrayList;
import java.util.List;

import seedu.quotely.exception.QuotelyException;

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

    public void removeQuote(String quoteName) throws QuotelyException {
        int index = getQuoteIndex(quoteName);
        quotes.remove(index);
    }

    private int getQuoteIndex(String quoteName) throws QuotelyException {
        int index = 0;
        for (Quote q : quotes) {
            if (q.getQuoteName().equals(quoteName)) {
                return index;
            }
            index++;
        }
        throw new QuotelyException(QuotelyException.ErrorType.QUOTE_NOT_FOUND);
    }

    public Quote getQuoteByName(String quoteName) throws QuotelyException {
        for (Quote q : quotes) {
            if (q.getQuoteName().equals(quoteName)) {
                return q;
            }
        }
        throw new QuotelyException(QuotelyException.ErrorType.QUOTE_NOT_FOUND);
    }
}
