# Quotely Global Logging Configuration

## Overview
This document explains how to use the centralized logging configuration for the Quotely application.

## Configuration Files

### 1. `src/main/resources/logging.properties`
This file contains the global logging configuration:
- Log levels for different packages/classes
- File output settings (location, size, rotation)
- Console output settings
- Formatter settings

### 2. `src/main/java/seedu/quotely/util/LoggerConfig.java`
Centralized logging utility class that:
- Loads configuration from `logging.properties`
- Provides fallback programmatic configuration
- Manages logger instances
- Allows runtime configuration changes

## How to Use Logging in Your Classes

### 1. Import and Get Logger
```java
import seedu.quotely.util.LoggerConfig;
import java.util.logging.Logger;

public class YourClass {
    private static final Logger logger = LoggerConfig.getLogger(YourClass.class);
    
    public void yourMethod() {
        logger.info("This is an info message");
        logger.fine("This is a debug message");
        logger.warning("This is a warning");
        logger.severe("This is an error");
    }
}
```

### 2. Logging Levels (from most to least verbose)
- `FINEST` - Most detailed tracing
- `FINER` - Detailed tracing  
- `FINE` - Debug information
- `CONFIG` - Configuration messages
- `INFO` - General information (default console level)
- `WARNING` - Warning messages
- `SEVERE` - Error messages

## Log File Locations

### Default Configuration:
- **Log Directory**: `logs/` (created automatically)
- **Main Log File**: `logs/quotely.log`
- **File Rotation**: 5 files × 1MB each
- **Append Mode**: Yes (logs persist between runs)

## Configuration Options

### Change Log Levels at Runtime:
```java
// Set specific package to DEBUG level
LoggerConfig.setLogLevel("seedu.quotely.parser", Level.FINE);

// Set console output to show only warnings
LoggerConfig.setConsoleLogLevel(Level.WARNING);

// Set file output to capture everything
LoggerConfig.setFileLogLevel(Level.ALL);
```

### Package-Specific Configuration in `logging.properties`:
```properties
# Parser package logs everything
seedu.quotely.parser.level = FINE

# Command package logs info and above
seedu.quotely.command.level = INFO

# UI package logs warnings only
seedu.quotely.ui.level = WARNING
```

## Examples

### Adding Logging to a Command Class:
```java
package seedu.quotely.command;

import seedu.quotely.util.LoggerConfig;
import java.util.logging.Logger;

public class AddQuoteCommand extends Command {
    private static final Logger logger = LoggerConfig.getLogger(AddQuoteCommand.class);
    
    @Override
    public void execute(Ui ui, QuoteList quoteList, CompanyName companyName, QuotelyState state) {
        logger.info("Executing add quote command for: " + quoteName);
        
        try {
            // Command logic here
            logger.fine("Quote added successfully");
        } catch (Exception e) {
            logger.severe("Failed to add quote: " + e.getMessage());
            throw e;
        }
    }
}
```

### Adding Logging to a Data Class:
```java
package seedu.quotely.data;

import seedu.quotely.util.LoggerConfig;
import java.util.logging.Logger;

public class QuoteList {
    private static final Logger logger = LoggerConfig.getLogger(QuoteList.class);
    
    public void addQuote(Quote quote) {
        logger.fine("Adding quote: " + quote.getQuoteName());
        quotes.add(quote);
        logger.info("Quote added. Total quotes: " + quotes.size());
    }
}
```

## Benefits of This Approach

1. **Centralized Configuration**: All logging settings in one place
2. **Consistent Formatting**: Same log format across all classes
3. **Easy Level Management**: Change log levels globally or per package
4. **File Rotation**: Automatic log file management
5. **Performance**: Cached logger instances for efficiency
6. **Flexibility**: Can switch between file-based and programmatic config

## Troubleshooting

### If logs aren't appearing:
1. Check if `LoggerConfig.initializeGlobalLogging()` is called in main()
2. Verify the `logs/` directory is created
3. Check log levels - console shows INFO+ by default
4. Look for `logging.properties` in `src/main/resources/`

### To see more detailed logs:
```java
// In main() or at startup:
LoggerConfig.setConsoleLogLevel(Level.FINE);
```

### To change log file location:
Modify the `pattern` property in `logging.properties`:
```properties
java.util.logging.FileHandler.pattern = /custom/path/app_%u.log
```