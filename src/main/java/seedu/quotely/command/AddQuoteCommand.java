package seedu.quotely.command;

import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.util.LoggerConfig;

import java.util.logging.Logger;

public class AddQuoteCommand extends Command {
    private static final String COMMAND_WORD = "add";
    private static final Logger logger = LoggerConfig.getLogger(AddQuoteCommand.class);
    private String quoteName;
    private String customerName;


    public AddQuoteCommand(String quoteName, String customerName) {
        super(COMMAND_WORD);
        this.quoteName = quoteName;
        this.customerName = customerName;
    }

    @Override
    public void execute(Ui ui,
            QuoteList quoteList,
            CompanyName companyName,
            QuotelyState state) throws QuotelyException {
      
        assert !state.isInsideQuote() : "Invalid state for addQuoteCommand execution";
        logger.fine(String.format("Executing AddQuoteCommand using quote %s for %s", quoteName, customerName));

        // check for duplicate quote names
        if (quoteList.hasQuote(quoteName)) {
            logger.warning(String.format("Duplicate quote name detected: %s", quoteName));
            throw new QuotelyException(QuotelyException.ErrorType.DUPLICATE_QUOTE_NAME);
        }

        Quote quoteToAdd = new Quote(quoteName, customerName);
        quoteList.addQuote(quoteToAdd);

        logger.info(String.format("State set to inside quote with reference %s",
                quoteToAdd.getQuoteName()));
        state.setInsideQuote(quoteToAdd);

        ui.showMessage(String.format("Adding quote: %s for %s", quoteName, customerName));
        logger.fine(String.format("Successfully added quote: %s", quoteName));
    }
}
