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

public class Parser {

    public static Command parse(String fullCommand) throws QuotelyException {
        System.out.println("Parsing command: " + fullCommand);
        String command = fullCommand.split(" ")[0];
        String arguments = "";
        if (fullCommand.split(" ").length > 1) {
            arguments = fullCommand.split(" ", 2)[1];
        }
        switch (command) {
        case "register":
            return parseRegisterCommand(arguments);
        case "quote":
            return parseAddQuoteCommand(arguments);
        case "unquote":
            return parseDeleteQuoteCommand(arguments);
        case "show":
            return new ShowQuotesCommand();
        case "finish":
            return new FinishQuoteCommand();
        case "delete":
            return parseDeleteItemCommand(arguments);
        case "add":
            return parseAddItemCommand(arguments);
        case "exit":
            return new ExitCommand();
        default:
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_COMMAND);
        }
    }

    private static Command parseAddQuoteCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile("n/(.*?)\\s+c/(.*)");
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String quoteName = m.group(1).trim();
            String companyName = m.group(2).trim();
            return new AddQuoteCommand(quoteName, companyName);
        } else {
            throw new QuotelyException(
                QuotelyException.ErrorType.WRONG_COMMAND_FORMAT,
                "quote n/QUOTE_NAME c/COMPANY_NAME"
            );
        }
    }

    private static Command parseDeleteQuoteCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile("n/(.*)");
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String quoteName = m.group(1).trim();
            return new DeleteQuoteCommand(quoteName);
        } else {
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "unquote n/QUOTE_NAME");
        }
    }

    private static Command parseRegisterCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile("c/(.*)");
        Matcher m = p.matcher(arguments);
        if (m.find()) {
            String companyName = m.group(1).trim();
            return new RegisterCommand(companyName);
        } else {
            throw new QuotelyException(
                QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, 
                "register c/COMPANY_NAME"
            );
        }
    }

    private static Command parseAddItemCommand(String arguments) throws QuotelyException {
        Pattern p = Pattern.compile("i/(.*?)\\s+n/(.*)\\s+p/(.*)\\s+q/(.*)");
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
        Pattern p = Pattern.compile("i/(.*?)\\s+n/(.*)");
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
}
