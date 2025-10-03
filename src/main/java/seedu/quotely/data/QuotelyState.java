package seedu.quotely.data;

public class QuotelyState {
    private boolean isInsideQuote;
    private Quote quoteReference;

    public QuotelyState() {
        isInsideQuote = false;
        quoteReference = null;
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
