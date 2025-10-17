package seedu.quotely.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Lightweight tests for LoggerConfig that avoid brittle assertions on file
 * contents.
 * Focus areas:
 * - Logger caching returns same instance for same name
 * - Console/file log levels are updated when requested
 * - Initialization is idempotent (no duplicate handlers added)
 */
class LoggerConfigTest {

    private List<Level> originalConsoleLevels;

    @BeforeEach
    void setUp() {
        // Ensure logging is initialized once before each test
        LoggerConfig.initializeGlobalLogging();
        // Snapshot console handler levels to restore later
        originalConsoleLevels = new ArrayList<>();
        for (Handler h : Logger.getLogger("").getHandlers()) {
            if (h instanceof ConsoleHandler) {
                originalConsoleLevels.add(h.getLevel());
            }
        }
    }

    @AfterEach
    void tearDown() {
        // Restore console handler levels to avoid test-order coupling
        int idx = 0;
        for (Handler h : Logger.getLogger("").getHandlers()) {
            if (h instanceof ConsoleHandler && idx < originalConsoleLevels.size()) {
                h.setLevel(originalConsoleLevels.get(idx++));
            }
        }
    }

    @Test
    void getLogger_returnsCachedLoggerForSameName() {
        Logger a1 = LoggerConfig.getLogger("test.logger");
        Logger a2 = LoggerConfig.getLogger("test.logger");
        Logger b = LoggerConfig.getLogger("another.logger");

        assertSame(a1, a2, "Expected same Logger instance for the same name");
        assertNotSame(a1, b, "Expected different Logger instances for different names");
    }

    @Test
    void setConsoleLogLevel_updatesAllConsoleHandlers() {
        Logger root = Logger.getLogger("");
        boolean hasConsole = false;
        for (Handler h : root.getHandlers()) {
            if (h instanceof ConsoleHandler) {
                hasConsole = true;
                break;
            }
        }
        assumeTrue(hasConsole, "No ConsoleHandler present to verify");

        LoggerConfig.setConsoleLogLevel(Level.WARNING);

        for (Handler h : root.getHandlers()) {
            if (h instanceof ConsoleHandler) {
                assertEquals(Level.WARNING, h.getLevel(), "Console handler level should be updated");
            }
        }
    }

    @Test
    void initializeGlobalLogging_isIdempotent_doesNotDuplicateHandlers() {
        Logger root = Logger.getLogger("");
        int before = root.getHandlers().length;

        // Call initialize again; it should early-return and not add handlers
        LoggerConfig.initializeGlobalLogging();
        int after = root.getHandlers().length;

        assertEquals(before, after, "Re-initialization should not add duplicate handlers");
    }

    @Test
    void setFileLogLevel_updatesFileHandlersIfPresent() {
        Logger root = Logger.getLogger("");
        boolean hasFile = false;
        for (Handler h : root.getHandlers()) {
            if (h instanceof FileHandler) {
                hasFile = true;
                break;
            }
        }
        assumeTrue(hasFile, "No FileHandler present; skipping file-level assertion");

        LoggerConfig.setFileLogLevel(Level.FINE);

        for (Handler h : root.getHandlers()) {
            if (h instanceof FileHandler) {
                assertEquals(Level.FINE, h.getLevel(), "File handler level should be updated");
            }
        }
    }
}
