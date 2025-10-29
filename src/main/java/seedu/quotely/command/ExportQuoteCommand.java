package seedu.quotely.command;

import seedu.quotely.ui.Ui;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.Quote;
import seedu.quotely.exception.QuotelyException;
import seedu.quotely.util.LoggerConfig;
import seedu.quotely.writer.PDFWriter;

import java.util.logging.Logger;

public class ExportQuoteCommand extends Command {
    private static final Logger logger = LoggerConfig.getLogger(ExportQuoteCommand.class);
    private Quote quote;
    private String filename;

    public ExportQuoteCommand(Quote quote, String filename) {
        super("export");
        this.quote = quote;
        this.filename = filename;
    }

    @Override
    public void execute(Ui ui,
                        QuoteList quoteList,
                        CompanyName companyName,
                        QuotelyState state) throws QuotelyException {

        logger.fine(String.format("Executing ExportQuoteCommand of quote %s to %s", quote.getQuoteName(), filename));
        ui.showMessage("Exporting quote: " + quote.getQuoteName() + " to " + filename + ".pdf");
        PDFWriter pdfWriter = PDFWriter.getInstance();
        pdfWriter.writeQuoteToPDF(quote, companyName, filename);
        logger.fine(String.format("Successfully export quote: %s to %s.pdf", quote.getQuoteName(), filename));
    }
}
