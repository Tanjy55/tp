package seedu.quotely;

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
        case "delete_item":
            return parseDeleteItemCommand(arguments);
        case "add_item":
            return parseAddItemCommand(arguments);
        case "exit":
            return new ExitCommand();
        default:
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_COMMAND);
        }
    }

    private static Command parseAddQuoteCommand(String arguments) throws QuotelyException {
        String[] parts = arguments.split(" n/| c/");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "quote n/QUOTE_NAME c/COMPANY_NAME");
        }
        String quoteName = parts[0].replaceFirst("^n/", "").trim();
        String companyName = parts[1].replaceFirst("^c/", "").trim();
        return new AddQuoteCommand(quoteName, companyName);
    }

    private static Command parseDeleteQuoteCommand(String arguments) throws QuotelyException {
        String[] parts = arguments.split("n/", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "unquote n/QUOTE_NAME");
        }
        String companyName = parts[1].trim();
        return new DeleteQuoteCommand(companyName);
    }

    private static Command parseRegisterCommand(String arguments) throws QuotelyException {
        String[] parts = arguments.split("c/", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "register c/COMPANY_NAME");
        }
        String companyName = parts[1].trim();
        return new RegisterCommand(companyName);
    }

    private static Command parseAddItemCommand(String arguments) throws QuotelyException {
        String[] parts = arguments.split(" i/| n/| p/| q/");
        if (parts.length < 4 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[1].trim().isEmpty() || parts[3].trim().isEmpty()) {
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "add_item i/ITEM_NAME n/QUOTE_NAME p/PRICE q/QUANTITY");
        }
        String itemName = parts[0].trim();
        String quoteName = parts[1].trim();
        String priceStr = parts[2].trim();
        String quantityStr = parts[3].trim();
        double price;
        int quantity;
        try {
            price = Double.parseDouble(priceStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw new QuotelyException(QuotelyException.ErrorType.INVALID_NUMBER_FORMAT);
        }
        return new AddItemCommand(itemName, quoteName, price, quantity);
    }

    private static Command parseDeleteItemCommand(String arguments) throws QuotelyException {
        String[] parts = arguments.split(" i/| q/");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new QuotelyException(QuotelyException.ErrorType.WRONG_COMMAND_FORMAT, "delete_item i/ITEM_NAME q/QUOTE_NAME");
        }
        String itemName = parts[0].replaceFirst("^i/", "").trim();
        String quoteName = parts[1].replaceFirst("^q/", "").trim();
        return new DeleteItemCommand(itemName, quoteName);
    }
}
