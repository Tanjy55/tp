package seedu.quotely;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class Parser {
    private static final String ADD_QUOTE_COMMAND_PATTERN = "n/(.*?)\\s+c/(.*)";
    private static final String DELETE_QUOTE_COMMAND_PATTERN = "n/(.*)";

    private static final String REGISTER_COMMAND_PATTERN = "c/(.*)";
    private static final String ADD_ITEM_COMMAND_PATTERN = "i/(.*?)\\s+n/(.*)\\s+p/(.*)\\s+q/(.*)";
    private static final String DELETE_ITEM_COMMAND_PATTERN = "i/(.*?)\\s+n/(.*)";

    private static final String CALCULATE_QUOTE_TOTAL_COMMAND_PATTERN = "n/(.*?)\\s+c/(.*)";

    public static Command parse(String fullCommand, boolean isInsideState) throws QuotelyException {

        //edit parse method to allow command input depending on isInsideState

        System.out.println("Parsing command: " + fullCommand);
        String command = fullCommand.split(" ")[0];
        String arguments = "";
        if (fullCommand.split(" ").length > 1) {
            arguments = fullCommand.split(" ", 2)[1];
        }
        switch (command) {
        case "register":
            //available in all state
            return parseRegisterCommand(arguments);
        case "quote":
            //main menu only
            return parseAddQuoteCommand(arguments);
        case "unquote":
            //main menu only
            return parseDeleteQuoteCommand(arguments);
        case "show":
            //main menu only
            return new ShowQuotesCommand();
        case "finish":
            //inside quote only
            return new FinishQuoteCommand();
        case "delete":
            //inside quote only
            //needs state reference in execute
            return parseDeleteItemCommand(arguments);
        case "add":
            //inside quote only
            //needs state reference in execute
            return parseAddItemCommand(arguments);
        case "total":
            //main menu only?
            return parseCalculateTotalCommand(arguments);
        case "exit":
            //available in all state, for now
            return new ExitCommand();
        default:
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_COMMAND);
        }
    }

    private static Command parseAddQuoteCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile(ADD_QUOTE_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String quoteName = m.group(1).trim();
            String customerName = m.group(2).trim();
            return new AddQuoteCommand(quoteName, customerName);
        } else {
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "quote n/QUOTE_NAME c/COMPANY_NAME"
            );
        }
    }

    private static Command parseDeleteQuoteCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile(DELETE_QUOTE_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String quoteName = m.group(1).trim();
            return new DeleteQuoteCommand(quoteName);
        } else {
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "unquote n/QUOTE_NAME");
        }
    }

    private static Command parseRegisterCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile(REGISTER_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String name = m.group(1).trim();
            return new RegisterCommand(name);
        } else {
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "register c/COMPANY_NAME"
            );
        }
    }

    private static Command parseAddItemCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile(ADD_ITEM_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String itemName = m.group(1).trim();
            String quoteName = m.group(2).trim();
            String priceStr = m.group(3).trim();
            String quantityStr = m.group(4).trim();
            double price;
            int quantity;
            try {
                price = Double.parseDouble(priceStr);
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                throw new QuotelyException(QuotelyException.ErrorType.INVALID_NUMBER_FORMAT);
            }
            return new AddItemCommand(itemName, quoteName, price, quantity);
        } else {
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "add i/ITEM_NAME n/QUOTE_NAME p/PRICE q/QUANTITY"
            );
        }
    }

    private static Command parseDeleteItemCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile(DELETE_ITEM_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String itemName = m.group(1).trim();
            String quoteName = m.group(2).trim();
            return new DeleteItemCommand(itemName, quoteName);
        } else {
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "delete i/ITEM_NAME n/QUOTE_NAME"
            );
        }
    }

    private static Command parseCalculateTotalCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile(CALCULATE_QUOTE_TOTAL_COMMAND_PATTERN);
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String quoteName = m.group(1).trim();
            String customerName = m.group(2).trim();
            return new CalculateTotalCommand(quoteName, customerName);
        } else {
            throw new QuotelyException(
                    QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                    "total n/QUOTE_NAME c/COMPANY_NAME"
            );
        }
    }
}
