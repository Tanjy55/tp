package seedu.quotely.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * General output tests for Ui class.
 */
public class UiGeneralTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void showWelcome_printsLogoAndPrompt() {
        try {
            Ui.getInstance().showWelcome();
            String s = out.toString();
            assertTrue(s.contains("Hello from"));
            assertTrue(s.contains("What can I do for you?"));
            assertTrue(s.contains("____")); // ascii art present
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void showLine_printsSeparator() {
        try {
            Ui.getInstance().showLine();
            assertTrue(out.toString().contains("____________________________________________________________"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void showExitMessage_printsGoodbye() {
        try {
            Ui.getInstance().showExitMessage();
            assertTrue(out.toString().contains("Bye. Hope to see you again soon!"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void showError_printsErrorPrefix() {
        try {
            Ui.getInstance().showError("oops");
            assertTrue(out.toString().contains("Error: oops"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void showMessage_printsRawMessage() {
        try {
            Ui.getInstance().showMessage("hello there");
            assertTrue(out.toString().contains("hello there"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void promptCompanyName_printsPrompt() {
        try {
            Ui.getInstance().promptCompanyName();
            assertTrue(out.toString().contains("Please enter your company name:"));
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
