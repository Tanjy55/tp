package seedu.quotely;

import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Item;
import seedu.quotely.data.Quote;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____  _     ____ _____ _____ _    ___  _\n" + //
                "/  _ \\/ \\ /\\/  _ Y__ __Y  __// \\   \\  \\//\n" + //
                "| / \\|| | ||| / \\| / \\ |  \\  | |    \\  / \n" + //
                "| \\_\\|| \\_/|| \\_/| | | |  /_ | |_/\\ / /  \n" + //
                "\\____\\\\____/\\____/ \\_/ \\____\\\\____//_/   \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void promptCompanyName() {
        System.out.println("Please enter your company name: ");
        // to be updated to prompt user if companyName is default String
    }

    public void showQuote(CompanyName companyName, Quote q, double gstRate) {
        // ===== choose ONE inner width for the whole box =====
        final int BOX_INNER = 60;      // chars between the two side pipes

        // items table columns (these MUST sum to BOX_INNER - 8)
        final int W_QTY  = 3;
        final int W_UNIT = 10;
        final int W_DESC = BOX_INNER - W_QTY - W_UNIT - 8; // 8 accounts for " | ", " | ", leading/trailing spaces

        // totals block (left margin + label + space + amount = BOX_INNER)
        final int AMT_W   = 10;             // width for "$xx.xx" (right aligned)
        final int INDENT  = 28;             // left margin you want before totals
        final int LABEL_W = BOX_INNER - INDENT - AMT_W - 2; // -2 for the single space before amount

        // reusable strings
        final String DASH = "-".repeat(BOX_INNER);
        final String UND  = "_".repeat(BOX_INNER);

        StringBuilder sb = new StringBuilder();

        // ===== header (all rows exactly BOX_INNER wide) =====
        sb.append("______QUOTE").append("_".repeat(Math.max(0, BOX_INNER - "QUOTE".length()))).append("\n");
        sb.append(String.format("| %-"+BOX_INNER+"s |%n", "Company name: " + companyName.getCompanyName()));
        sb.append(String.format("| %-"+BOX_INNER+"s |%n", "Quote ID: " + q.getQuoteName()));
        sb.append(String.format("| %-"+BOX_INNER+"s |%n", "Customer name: " + q.getCustomerName()));
        sb.append(String.format("|%s|%n", DASH));

        // ===== table header + items (same formats & computed separator) =====
        final String HEADER_FMT = "| %-"+W_DESC+"s | %"+W_QTY+"s | %"+W_UNIT+"s |%n";
        final String ITEM_FMT   = "| %-"+W_DESC+"s | %"+W_QTY+"d | $%"+(W_UNIT-1)+".2f |%n";

        String headerLine = String.format(HEADER_FMT, "Description", "QTY", "Unit cost");
        sb.append(headerLine);
        sb.append(String.format("|%s|%n", "-".repeat(BOX_INNER)));

        double subtotal = 0.0;
        if (q.getItems().isEmpty()) {
            sb.append(String.format(HEADER_FMT, "(no items)", "-", "-"));
        } else {
            for (Item it : q.getItems()) {
                String name = it.getItemName();
                if (name.length() > W_DESC) name = name.substring(0, W_DESC);
                subtotal += it.getQuantity() * it.getPrice();
                sb.append(String.format(ITEM_FMT, name, it.getQuantity(), it.getPrice()));
            }
        }

        sb.append(String.format("|%s|%n", "-".repeat(BOX_INNER)));

        // ===== totals (left indent + aligned amounts) =====
        double gst = subtotal * gstRate;
        double total = subtotal + gst;

        sb.append(String.format("| %-"+BOX_INNER+"s |%n", "")); // blank spacer row

        String subStr = String.format("$%.2f", subtotal);
        String gstStr = String.format("$%.2f", gst);
        String totStr = String.format("$%.2f", total);

        // pipe + space + INDENT + label (LABEL_W left) + space + amount (AMT_W right) + pipe
        sb.append(String.format("| %"+INDENT+"s%-"+LABEL_W+"s %"+AMT_W+"s |%n", "", "Subtotal:", subStr));
        sb.append(String.format("| %"+INDENT+"s%-"+LABEL_W+"s %"+AMT_W+"s |%n", "", "GST:",      gstStr));
        sb.append(String.format("| %"+INDENT+"s%-"+LABEL_W+"s %"+AMT_W+"s |%n", "", "Total:",    totStr));

        sb.append(String.format("|%s|%n", UND));

        showMessage(sb.toString());
    }
}
