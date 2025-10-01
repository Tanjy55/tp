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
        final int boxInner = 60; // chars between the two side pipes

        // items table columns (these MUST sum to boxInner - 8)
        final int wQty  = 3;
        final int wUnit = 10;
        final int wDesc = boxInner - wQty - wUnit - 8; // 8 accounts for " | ", " | ", leading/trailing spaces

        // totals block (left margin + label + space + amount = boxInner)
        final int amtW   = 10;             // width for "$xx.xx" (right aligned)
        final int indent = 28;             // left margin you want before totals
        final int labelW = boxInner - indent - amtW - 2; // -2 for the single space before amount

        // reusable strings
        final String dash = "-".repeat(boxInner);
        final String und  = "_".repeat(boxInner);

        StringBuilder sb = new StringBuilder();

        // ===== header (all rows exactly boxInner wide) =====
        sb.append("______QUOTE").append("_".repeat(Math.max(0, boxInner - "QUOTE".length()))).append("\n");
        sb.append(String.format("| %-"+boxInner+"s |%n", "Company name: " + companyName.getCompanyName()));
        sb.append(String.format("| %-"+boxInner+"s |%n", "Quote ID: " + q.getQuoteName()));
        sb.append(String.format("| %-"+boxInner+"s |%n", "Customer name: " + q.getCustomerName()));
        sb.append(String.format("|%s|%n", dash));

        // ===== table header + items (same formats & computed separator) =====
        final String headerFmt = "| %-"+wDesc+"s | %"+wQty+"s | %"+wUnit+"s |%n";
        final String itemFmt   = "| %-"+wDesc+"s | %"+wQty+"d | $%"+(wUnit-1)+".2f |%n";

        String headerLine = String.format(headerFmt, "Description", "QTY", "Unit cost");
        sb.append(headerLine);
        sb.append(String.format("|%s|%n", "-".repeat(boxInner)));

        double subtotal = 0.0;
        if (q.getItems().isEmpty()) {
            sb.append(String.format(headerFmt, "(no items)", "-", "-"));
        } else {
            for (Item it : q.getItems()) {
                String name = it.getItemName();
                if (name.length() > wDesc) {
                    name = name.substring(0, wDesc);
                }
                subtotal += it.getQuantity() * it.getPrice();
                sb.append(String.format(itemFmt, name, it.getQuantity(), it.getPrice()));
            }
        }

        sb.append(String.format("|%s|%n", "-".repeat(boxInner)));

        // ===== totals (left indent + aligned amounts) =====
        double gst = subtotal * gstRate;
        double total = subtotal + gst;

        sb.append(String.format("| %-"+boxInner+"s |%n", "")); // blank spacer row

        String subStr = String.format("$%.2f", subtotal);
        String gstStr = String.format("$%.2f", gst);
        String totStr = String.format("$%.2f", total);

        // pipe + space + indent + label (labelW left) + space + amount (amtW right) + pipe
        sb.append(String.format("| %"+indent+"s%-"+labelW+"s %"+amtW+"s |%n", "", "Subtotal:", subStr));
        sb.append(String.format("| %"+indent+"s%-"+labelW+"s %"+amtW+"s |%n", "", "GST:",      gstStr));
        sb.append(String.format("| %"+indent+"s%-"+labelW+"s %"+amtW+"s |%n", "", "Total:",    totStr));

        sb.append(String.format("|%s|%n", und));

        showMessage(sb.toString());
    }
}
