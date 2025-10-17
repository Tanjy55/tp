package seedu.quotely.data;

public class QuotelyState {
    private static QuotelyState state = null;
    private boolean isInsideQuote;
    private Quote quoteReference;

    private QuotelyState() {
        isInsideQuote = false;
        quoteReference = null;
    }

    public static QuotelyState getInstance() {
        if (state == null) {
            state = new QuotelyState();
        }
        return state;
    }

    public boolean isInsideQuote() {
        return isInsideQuote;
    }

    public void setQuoteReference(Quote quoteReference) {
        this.quoteReference = quoteReference;
    }

    public void setInsideQuote(Quote quoteReference) {
        this.isInsideQuote = true;
        this.quoteReference = quoteReference;
    }

    public void setOutsideQuote() {
        this.isInsideQuote = false;
        this.quoteReference = null;
    }

    public Quote getQuoteReference() {
        return quoteReference;
    }
}
