package seedu.quotely.util;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogManager;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Centralized logging configuration for the Quotely application.
 * This class manages all logging settings globally.
 */
public class LoggerConfig {
    private static boolean isInitialized = false;
    private static final Map<String, Logger> loggerCache = new HashMap<>();

    // Default configuration values
    private static final String DEFAULT_LOG_DIR = "logs";
    private static final String DEFAULT_LOG_FILE = "quotely.log";
    private static final Level DEFAULT_FILE_LEVEL = Level.ALL;
    private static final Level DEFAULT_CONSOLE_LEVEL = Level.INFO;

    /**
     * Initialize global logging configuration.
     * This should be called once at application startup.
     */
    public static void initializeGlobalLogging() {
        if (isInitialized) {
            return;
        }

        try {
            // Create logs directory first (for both approaches)
            File logsDir = new File(DEFAULT_LOG_DIR);
            if (!logsDir.exists()) {
                boolean created = logsDir.mkdirs();
                if (!created) {
                    System.err.println("Warning: Could not create logs directory");
                }
            }

            // Try to load logging.properties from resources
            InputStream configStream = LoggerConfig.class.getClassLoader()
                    .getResourceAsStream("logging.properties");

            if (configStream != null) {
                LogManager.getLogManager().readConfiguration(configStream);
                System.out.println("Loaded logging configuration from logging.properties");
                configStream.close();
            } else {
                // Fallback to programmatic configuration
                setupDefaultLogging();
                System.out.println("Using default logging configuration");
            }

        } catch (IOException e) {
            System.err.println("Failed to load logging configuration: " + e.getMessage());
            setupDefaultLogging();
        }

        isInitialized = true;
    }

    /**
     * Get a logger for a specific class with global configuration applied.
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * Get a logger for a specific class name with global configuration applied.
     */
    public static Logger getLogger(String className) {
        if (!isInitialized) {
            initializeGlobalLogging();
        }

        return loggerCache.computeIfAbsent(className, k -> {
            Logger logger = Logger.getLogger(k);
            // Additional class-specific configuration can be added here
            return logger;
        });
    }

    /**
     * Setup default logging configuration programmatically.
     */
    private static void setupDefaultLogging() {
        try {
            // Get root logger
            Logger rootLogger = Logger.getLogger("");

            // Clear existing handlers
            java.util.logging.Handler[] handlers = rootLogger.getHandlers();
            for (java.util.logging.Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // Set up file handler with better error handling
            try {
                FileHandler fileHandler = new FileHandler(
                        DEFAULT_LOG_DIR + File.separator + DEFAULT_LOG_FILE
                );
                fileHandler.setLevel(DEFAULT_FILE_LEVEL);
                rootLogger.addHandler(fileHandler);
                System.out.println("File logging enabled: " + DEFAULT_LOG_DIR + File.separator + DEFAULT_LOG_FILE);
            } catch (IOException e) {
                System.err.println("Failed to set up file logging: " + e.getMessage());
                System.err.println("Continuing with console logging only");
            }

            // Set up console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(DEFAULT_CONSOLE_LEVEL);
            consoleHandler.setFormatter(new SimpleFormatter());

            // Add console handler to root logger
            rootLogger.addHandler(consoleHandler);
            rootLogger.setLevel(Level.ALL);

        } catch (Exception e) {
            System.err.println("Failed to set up logging: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Update logging level for a specific package or class.
     */
    public static void setLogLevel(String loggerName, Level level) {
        Logger logger = Logger.getLogger(loggerName);
        logger.setLevel(level);
    }

    /**
     * Update console logging level globally.
     */
    public static void setConsoleLogLevel(Level level) {
        Logger rootLogger = Logger.getLogger("");
        for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(level);
            }
        }
    }

    /**
     * Update file logging level globally.
     */
    public static void setFileLogLevel(Level level) {
        Logger rootLogger = Logger.getLogger("");
        for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof FileHandler) {
                handler.setLevel(level);
            }
        }
    }
}
