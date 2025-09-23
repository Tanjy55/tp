package seedu.quotely;

import seedu.quotely.command.Command;
import seedu.quotely.exception.QuotelyException;

public class Quotely {
    private Ui ui;

    public Quotely() {
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        ui.showLine();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(ui);
                isExit = c.isExit();
            } catch (QuotelyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Quotely().run();
    }
}
