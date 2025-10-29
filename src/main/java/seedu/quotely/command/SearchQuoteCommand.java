package seedu.quotely.command;

import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.ui.Ui;
import seedu.quotely.util.LoggerConfig;

import java.util.ArrayList;
import java.util.logging.Logger;

public class SearchQuoteCommand extends Command {
    private static final Logger logger = LoggerConfig.getLogger(SearchQuoteCommand.class);
    private String quoteName;

    public SearchQuoteCommand(String quoteName) {
        super("search");
        this.quoteName = quoteName;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {
        logger.fine(String.format("Executing SearchQuoteCommand for: %s", quoteName));

        try {
            ArrayList<Quote> searchFoundQuotes = quoteList.searchQuote(quoteName);
            if (searchFoundQuotes.isEmpty()) {
                ui.showMessage("No matching quote found");
                logger.info("SearchQuoteCommand executed with no matching quote found");
                return;
            }
            //gst rate parameter is temporary value
            for (Quote q : searchFoundQuotes) {
                ui.showQuote(companyName, q, 0.00);
                logger.fine("SearchQuoteCommand executed and matching quote is shown to user");
            }
            ui.showMessage("Successfully found quotes containing: " + quoteName);
            logger.info("SearchQuoteCommand executed with matching quotes found");
        } catch (QuotelyException e) {
            logger.severe("SearchQuoteCommand executed with unexpected exception: " + e.getMessage());
        }
    }
}
