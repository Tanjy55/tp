package seedu.quotely.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.quotely.exception.QuotelyException;
import seedu.quotely.util.LoggerConfig;

public class QuoteList {
    private static final Logger logger = LoggerConfig.getLogger(QuoteList.class);
    private List<Quote> quotes = new ArrayList<>();

    public QuoteList() {
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    public void removeQuote(Quote quote) throws QuotelyException {
        for (int i = 0; i < quotes.size(); i++) {
            Quote currentQuote = quotes.get(i);

            if (currentQuote.getQuoteName().equalsIgnoreCase(quote.getQuoteName())) {
                quotes.remove(i);
                logger.info("Successfully removed quote: " + currentQuote.getQuoteName());
                return;
            }
        }

        logger.warning("Attempted to remove quote that does not exist: " + quote.getQuoteName());
        throw new QuotelyException(QuotelyException.ErrorType.QUOTE_NOT_FOUND);
    }

    public Quote getQuoteByName(String quoteName) throws QuotelyException {
        for (Quote q : quotes) {
            if (q.getQuoteName().equalsIgnoreCase(quoteName)) {
                return q;
            }
        }
        logger.warning("Failed to find quote with name: " + quoteName);
        throw new QuotelyException(QuotelyException.ErrorType.QUOTE_NOT_FOUND);
    }

    public ArrayList<Quote> searchQuote(String searchTerm) throws QuotelyException {
        ArrayList<Quote> result = new ArrayList<>();
        for (Quote q : quotes) {
            if (q.getQuoteName().toLowerCase().contains(searchTerm.toLowerCase())) {
                result.add(q);
            }
        }
        return result;
    }

    public boolean hasQuote(String quoteName) {
        for (Quote q : quotes) {
            if (q.getQuoteName().equalsIgnoreCase(quoteName)) {
                return true;
            }
        }
        return false;
    }

    public void validate() {
        List<Quote> validQuotes = new ArrayList<>();
        for (Quote q : quotes) {
            q.ensureValid();
            if (q.isValid()) {
                validQuotes.add(q);
            } else {
                logger.warning("Invalid quote found and removed during validation: " + 
                    (q.getQuoteName() != null ? q.getQuoteName() : "<null>"));
            }
        }
        this.quotes = validQuotes;
    }
}
