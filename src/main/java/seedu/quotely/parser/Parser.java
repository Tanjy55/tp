package seedu.quotely.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

import seedu.quotely.exception.QuotelyException;
import seedu.quotely.command.Command;
import seedu.quotely.command.ExitCommand;
import seedu.quotely.command.AddQuoteCommand;
import seedu.quotely.command.RegisterCommand;
import seedu.quotely.command.DeleteQuoteCommand;
import seedu.quotely.command.ShowQuotesCommand;
import seedu.quotely.command.FinishQuoteCommand;
import seedu.quotely.command.AddItemCommand;
import seedu.quotely.command.DeleteItemCommand;
import seedu.quotely.command.CalculateTotalCommand;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.util.LoggerConfig;

public class Parser {
    private static final Logger logger = LoggerConfig.getLogger(Parser.class);

    private static final String ADD_QUOTE_COMMAND_PATTERN = "n/(.*?)\\s+c/(.*)";
    private static final String DELETE_QUOTE_COMMAND_PATTERN = "n/(.*)";

    private static final String REGISTER_COMMAND_PATTERN = "c/(.*)";
    private static final String ADD_ITEM_COMMAND_PATTERN = "i/(.*?)\\s+(?:n/(.*?)\\s+)?p/(.*?)\\s+q/(.*)";
    private static final String DELETE_ITEM_COMMAND_PATTERN = "i/(\\S+)(?:\\s+n/(.*))?";
    private static final String CALCULATE_QUOTE_TOTAL_COMMAND_PATTERN = "n/(.*)";

    public static Command parse(String fullCommand, QuotelyState state, QuoteList quoteList)
            throws QuotelyException {

        logger.info("Parsing command: " + fullCommand);
        logger.fine("Current state - isInside quote: " + state.isInsideQuote() +
            " QuoteReference: " + (state.getQuoteReference() != null ? 
            state.getQuoteReference().toString() : "null"));

        /*
         * edit parse method to allow command input depending on isInsideState
         * add exception handling in parser
         */

        // System.out.println("Parsing command: " + fullCommand);
        String command = fullCommand.split(" ")[0];
        logger.fine("Extracted command: '" + command + "'");

        String arguments = "";
        if (fullCommand.split(" ").length > 1) {
            arguments = fullCommand.split(" ", 2)[1];
            logger.fine("Extracted arguments: '" + arguments + "'");
        }
        switch (command) {
        case "register":
            // available in all state
            return parseRegisterCommand(arguments);
        case "quote":
            // main menu only
            return parseAddQuoteCommand(arguments, state);
        case "unquote":
            // can use no quote name if inside a quote
            return parseDeleteQuoteCommand(arguments, state, quoteList);
        case "show":
            // available in all state, for now?
            return new ShowQuotesCommand();
        case "finish":
            // inside quote only
            return parseFinishQuoteCommand(state);
        case "delete":
            // can use without quote name if inside a quote
            return parseDeleteItemCommand(arguments, state, quoteList);
        case "add":
            // can use without quote name if inside a quote
            return parseAddItemCommand(arguments, state, quoteList);
        case "total":
            // can use without quote name if inside a quote
            return parseCalculateTotalCommand(arguments, state, quoteList);
        case "exit":
            // available in all state, for now
            return new ExitCommand();
        default:
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_COMMAND);
        }
    }

    private static Command parseAddQuoteCommand(String arguments, QuotelyState state)
            throws QuotelyException {
        logger.fine("parseAddQuoteCommand called with arguments: " + arguments);
        if (state.isInsideQuote()) {
            logger.warning("Attempted to add quote while inside a quote");
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_STATE);
        }
        Pattern p = Pattern.compile(ADD_QUOTE_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String quoteName = m.group(1).trim();
            String customerName = m.group(2).trim();
            logger.info("Successfully parsed add quote command - Quote: '" + quoteName + "', Customer: '" + customerName
                    + "'");
            return new AddQuoteCommand(quoteName, customerName);
        } else {
            logger.warning("Invalid format for add quote command: " + arguments);
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "quote n/QUOTE_NAME c/COMPANY_NAME");
        }
    }

    private static Command parseDeleteQuoteCommand(String arguments, QuotelyState state,
            QuoteList quoteList) throws QuotelyException {
        logger.fine("parseDeleteQuoteCommand called with arguments: " + arguments);
        Pattern p = Pattern.compile(DELETE_QUOTE_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        String quoteName = null;
        if (m.find()) {
            quoteName = m.group(1).trim();
        }
        Quote quote = getQuoteFromStateAndName(quoteName, state, quoteList);
        if (quote != null) {
            logger.info("Successfully parsed delete quote command for quote: " + quote.getQuoteName());
            return new DeleteQuoteCommand(quote);
        } else {
            logger.warning("Failed to find quote for deletion with name: " + quoteName);
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "unquote [n/QUOTE_NAME]");
        }
    }

    private static Command parseRegisterCommand(String arguments) throws QuotelyException {
        logger.fine("parseRegisterCommand called with arguments: " + arguments);
        Pattern p = Pattern.compile(REGISTER_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String name = m.group(1).trim();
            logger.info("Successfully parsed register command for company: " + name);
            return new RegisterCommand(name);
        } else {
            logger.warning("Invalid format for register command: " + arguments);
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "register c/COMPANY_NAME");
        }
    }

    private static Command parseAddItemCommand(String arguments,
            QuotelyState state, QuoteList quoteList) throws QuotelyException {
        logger.fine("parseAddItemCommand called with arguments: " + arguments);
        Pattern p = Pattern.compile(ADD_ITEM_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String itemName = m.group(1).trim();
            String quoteName = m.group(2) != null ? m.group(2).trim() : null;
            String priceStr = m.group(3).trim();
            String quantityStr = m.group(4).trim();
            logger.fine("Extracted - Item: '" + itemName + "', Quote: '" + 
                (quoteName != null ? quoteName : "<none>") + "', Price: '" + 
                priceStr + "', Quantity: '" + quantityStr + "'");

            double price;
            int quantity;
            try {
                price = Double.parseDouble(priceStr);
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0 || price < 0) {
                    logger.warning("Invalid price or quantity values - Price: " + price + ", Quantity: " + quantity);
                    throw new QuotelyException(QuotelyException.ErrorType.INVALID_NUMBER_FORMAT);
                }
            } catch (NumberFormatException e) {
                logger.warning("Failed to parse price or quantity: " + e.getMessage());
                throw new QuotelyException(QuotelyException.ErrorType.INVALID_NUMBER_FORMAT);
            }

            Quote quote = getQuoteFromStateAndName(quoteName, state, quoteList);
            logger.info("Successfully parsed add item command - Item: '" +
                    itemName + "' Price: " + price + " Quantity: " + quantity +
                    " for quote: '" + quote.getQuoteName() + "'");
            return new AddItemCommand(itemName, quote, price, quantity);
        } else {
            logger.warning("Invalid format for add item command: " + arguments);
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "add i/ITEM_NAME [n/QUOTE_NAME] p/PRICE q/QUANTITY");
        }
    }

    private static Command parseDeleteItemCommand(String arguments, QuotelyState state,
            QuoteList quoteList) throws QuotelyException {
        logger.fine("parseDeleteItemCommand called with arguments: " + arguments);
        Pattern p = Pattern.compile(DELETE_ITEM_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String itemName = m.group(1).trim();
            String quoteName = m.group(2) != null ? m.group(2).trim() : null;
            Quote quote = getQuoteFromStateAndName(quoteName, state, quoteList);
            if (!quote.hasItem(itemName)) {
                logger.warning("Item not found in quote - Item: '" + itemName +
                        "', Quote: '" + quote.getQuoteName() + "'");
                throw new QuotelyException(QuotelyException.ErrorType.ITEM_NOT_FOUND);
            }
            logger.info("Successfully parsed delete item command - Item: '" +
                    itemName + " for quote: '" + quote.getQuoteName() + "'");
            return new DeleteItemCommand(itemName, quote);
        } else {
            logger.warning("Invalid format for delete item command: " + arguments);
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "delete i/ITEM_NAME [n/QUOTE_NAME]");
        }
    }

    private static Command parseCalculateTotalCommand(String arguments,
            QuotelyState state, QuoteList quoteList) throws QuotelyException {
        logger.fine("parseCalculateTotalCommand called with arguments: " + arguments);
        Pattern p = Pattern.compile(CALCULATE_QUOTE_TOTAL_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        String quoteName = null;
        if (m.find()) {
            quoteName = m.group(1).trim();
        }
        Quote quote = getQuoteFromStateAndName(quoteName, state, quoteList);
        if (quote != null) {
            return new CalculateTotalCommand(quote);
        } else {
            logger.warning("Invalid format for calculate total command: " + arguments);
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "total [n/QUOTE_NAME]");
        }
    }

    private static Command parseFinishQuoteCommand(QuotelyState state)
            throws QuotelyException {
        logger.fine("parseFinishQuoteCommand called");
        if (state.isInsideQuote()) {
            logger.info("Successfully parsed finish quote command");
            return new FinishQuoteCommand();
        } else {
            logger.warning("Attempted to finish quote while not inside a quote");
            throw new QuotelyException(QuotelyException.ErrorType.NO_ACTIVE_QUOTE);
        }
    }

    private static Quote getQuoteFromStateAndName(String quoteName, 
            QuotelyState state, QuoteList quoteList) throws QuotelyException {
        logger.fine("getQuoteFromStateAndName called");
        if (quoteName == null && state.getQuoteReference() == null) {
            logger.warning("No quote name provided and no active quote in state");
            throw new QuotelyException(QuotelyException.ErrorType.NO_ACTIVE_QUOTE);
        } else if (quoteName != null) {
            logger.fine("Looking up quote by name: " + quoteName);
            return quoteList.getQuoteByName(quoteName);
        } else {
            logger.fine("Using current quote from state");
            return state.getQuoteReference();
        }
    }
}
