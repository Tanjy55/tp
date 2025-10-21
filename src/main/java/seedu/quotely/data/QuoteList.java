package seedu.quotely.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.quotely.exception.QuotelyException;

public class QuoteList {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
//        quotes.remove(quote);
        for (Quote q : quotes) {
            if (q.getQuoteName().equals(quote.getQuoteName())) {
                quotes.remove(q);
                logger.info("Successfully removed quote: " + quote.getQuoteName());
                return;
            }
        }

        logger.warning("Attempted to remove quote that does not exist: " + quote.getQuoteName());
        throw new QuotelyException(QuotelyException.ErrorType.QUOTE_NOT_FOUND);
    }

    public Quote getQuoteByName(String quoteName) throws QuotelyException {
        for (Quote q : quotes) {
            if (q.getQuoteName().equals(quoteName)) {
                return q;
            }
        }
        logger.warning("Failed to find quote with name: " + quoteName);
        throw new QuotelyException(QuotelyException.ErrorType.QUOTE_NOT_FOUND);
    }
}
