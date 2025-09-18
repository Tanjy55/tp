package seedu.quotely;

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
}
