package seedu.quotely.data;

public class QuotelyState {
    private boolean isInsideQuote;
    private Quote quoteReference;

    public QuotelyState() {
        isInsideQuote = false;
    }

    public boolean isInsideQuote() {
        return isInsideQuote;
    }

    public void setInsideQuote(boolean insideQuote) {
        this.isInsideQuote = insideQuote;
    }

    public Quote getQuoteReference() {
        return quoteReference;
    }

    public void setQuoteReference(Quote quoteReference) {
        this.quoteReference = quoteReference;
    }
}
