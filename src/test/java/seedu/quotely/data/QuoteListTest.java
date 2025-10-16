package seedu.quotely.data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class QuoteListTest {
    @Test
    void QuoteList_validInput_constructorSuccess() {
        QuoteList quoteList = new QuoteList();
        try {
            assertInstanceOf(QuoteList.class, quoteList);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void addQuote_validInput_success() {
        QuoteList quoteList = new QuoteList();
        try {
            assertEquals(0, quoteList.getQuotes().size());
            quoteList.addQuote(new Quote("quote1", "customer1"));
            assertInstanceOf(Quote.class, quoteList.getQuotes().get(0));
            assertEquals(1, quoteList.getQuotes().size());
            quoteList.addQuote(new Quote("quote2", "customer2"));
            assertInstanceOf(Quote.class, quoteList.getQuotes().get(1));
            assertEquals(2, quoteList.getQuotes().size());
            quoteList.addQuote(new Quote("quote3", "customer3"));
            assertInstanceOf(Quote.class, quoteList.getQuotes().get(2));
            assertEquals(3, quoteList.getQuotes().size());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getQuotes_validInput_success() {
        QuoteList quoteList = new QuoteList();
        try {
            List<Quote> quotes = new ArrayList<Quote>();
            Quote quote1 = new Quote("quote1", "customer1");
            Quote quote2 = new Quote("quote2", "customer2");
            Quote quote3 = new Quote("quote3", "customer3");
            quotes.add(quote1);
            quotes.add(quote2);
            quotes.add(quote3);
            quoteList.addQuote(quote1);
            quoteList.addQuote(quote2);
            quoteList.addQuote(quote3);
            for (int i = 0; i < 3; i++) {
                assertEquals(quotes.get(i).getQuoteName(),
                        quoteList.getQuotes().get(i).getQuoteName());
            }
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void removeItem_validInput_success() {
        QuoteList quoteList = new QuoteList();
        try {
            Quote quote1 = new Quote("quote1", "customer1");
            Quote quote2 = new Quote("quote2", "customer2");
            Quote quote3 = new Quote("quote3", "customer3");
            quoteList.addQuote(quote1);
            quoteList.addQuote(quote2);
            quoteList.addQuote(quote3);
            assertEquals(3, quoteList.getQuotes().size());
            quoteList.removeQuote(quote1);
            assertEquals(2, quoteList.getQuotes().size());
            quoteList.removeQuote(quote2);
            assertEquals(1, quoteList.getQuotes().size());
            quoteList.removeQuote(quote3);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    void getQuoteByName_validInput_returnQuote() {
        QuoteList quoteList = new QuoteList();
        try {
            quoteList.addQuote(new Quote("quote176", "customer3"));
            assertEquals("quote176",
                    quoteList.getQuoteByName("quote176").getQuoteName());
            quoteList.addQuote(new Quote("009361", "customer3"));
            assertEquals("009361",
                    quoteList.getQuoteByName("009361").getQuoteName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
