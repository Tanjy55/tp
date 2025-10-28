# Developer Guide

- [Developer Guide](#developer-guide)
  - [Acknowledgements](#acknowledgements)
  - [Design](#design)
    - [Architecture](#architecture)
    - [Parser Component](#parser-component)
    - [Command Component](#command-component)
    - [Ui Component](#ui-component)
    - [Data Component](#data-component)
    - [File storage Component](#file-storage-component)
    - [PDF export Component](#pdf-export-component)
  - [Implementation](#implementation)
    - [QuotelyState feature](#quotelystate-feature)
    - [export feature](#export-feature)
  - [Overview](#overview)
  - [User-facing behaviour](#user-facing-behaviour)
  - [Example (full workflow)](#example-full-workflow)
  - [Developer notes (implementation)](#developer-notes-implementation)
  - [Implementation considerations \& TODOs](#implementation-considerations--todos)
    - [next feature 2](#next-feature-2)
    - [next feature 3](#next-feature-3)
  - [Product scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
    - [User Stories](#user-stories)
  - [Logging](#logging)
    - [Configuration Files](#configuration-files)
      - [1. `src/main/resources/logging.properties`](#1-srcmainresourcesloggingproperties)
      - [2. `src/main/java/seedu/quotely/util/LoggerConfig.java`](#2-srcmainjavaseeduquotelyutilloggerconfigjava)
    - [How to Use Logging in Your Classes](#how-to-use-logging-in-your-classes)
      - [1. Import and Get Logger](#1-import-and-get-logger)
      - [2. Logging Levels (from most to least verbose)](#2-logging-levels-from-most-to-least-verbose)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
  - [Instructions for manual testing](#instructions-for-manual-testing)
  - [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design

### Architecture

[Architecture diagram (to be implemented)]

The architecture diagram above explains the high-level design of the App.

Main components of the Architecture

`Quotely` is in charge of program launch.

* At app launch, it initialises the logger, parser and instantiate data objects

The program work is done by the following main components:

* `Parser`: Parse user CLI inputs
* `Command`: Perform data mutation, UI navigation
* `UI`: Print CLI text for user
* `Data`: Store quote and item data
* `File storage`: to be implemented
* `Util`: logger configuration

Component interaction is modelled using a sequence diagram for the `run()` method in Quotely, where the bulk of program
execution occurs.

!['sequence diagram'](./src/sequenceDiagram.png)

Setup is performed once in `main()` upon running the program.
User input and corresponding work done by the program is run in a loop using the `run()` method

Loop sequence explanation:

1) User input is fetched from `UI`
2) input is fed into `Parser`
3) `Parser` determines appropriate `Command` type to create. Returns new command object with appropriate parameters set
   to Quotely
4) Quotely runs the execute method in `Commmand`

The above process runs until `Exit` is read from the user.

The sections below give more details of each component.

### Parser Component

Here’s a (partial) class diagram of the `Parser` component:

!['Parser diagram'](./src/ParserDiagram.png)

The sequence diagram below illustrates the interactions within the Logic component, taking user input "add n/01
c/joe" as an example.

[Sequence diagram (to be implemented)]

How the `Parser` component works:

1. When user inputs "add n/01 c/joe", the input is passed from Ui Component to Parser Component.
2. `Parser` checks for valid command format and runs method to parse command based on command keyword, which is "add"
   for this example.
3. The respective method is run to parse the command and set up attributes for the corresponding Command type
4. This results in a `Command` object created (more precisely, an object of one of its subclasses e.g., AddQuoteCommand)
   which is executed in Quotely.
5. The command can communicate with `Data` when it is executed (e.g. to add a quote). Note that although this is shown
   as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the
   command object and the Data) to achieve.
6. The result of the `Parser` execution is encapsulated as a Command object which is returned back from `Parser`.

### Command Component

Here’s a (partial) class diagram of the `Command` component:

!['Command diagram'](./src/CommandDiagram.png)

All `Command` subtypes inherit from the abstract `Command` class which defines a command word and execute method

The `Command` component,

> Implements the Command design pattern and is central to the application's execution logic,
> allowing the user input to trigger specific actions.

How the `Command` component works:

1) At it's core is the abstract class, `Command.java`, which all specific subtypes must
   extend. It enforces the execution of the `execute()` method.

(Further explanation)

### Ui Component

!['Ui diagram'](./src/UiDiagram.png)

The `UI` component consists of

(Further explanation)

### Data Component

!['Data diagram'](./src/DataDiagram.png)

The `Data` component,

* stores the quote data i.e., all Quote objects (which are contained in a QuoteList object).
* stores the item data i.e., all Item objects (which are contained in a Quote object).
* stores the company name in a CompanyName object
* stores the state using a QuotelyState object (e.g., inside quote + quote reference)

### File storage Component

To be implemented.

### PDF export Component

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### QuotelyState feature

The QuotelyState feature is a helper for user interaction, implemented using a simple class. The application and
explanation is covered below.

The purpose of this feature is to fix the anticipated issue of user confusion by facilitating UI elements for the user
to navigate between the `main menu` and`quote` state.

* In previous versions, if the user is working on `quote1`, there is no UI element for the user to reference that the
  current situation is indeed "editing quote 1".
* The user may be in no quotes, or a different quote.
* This may become extremely messy and almost unusable if the number of quotes are large.

To solve this problem, QuotelyState was introduced to allow additional UI elements for the user to distinguish the
current situation.

* If the user is not editing any quote, it is considered as `main menu state`
* If the user is editing a quote, it is considered as `quote state`, whereby the QuotelyState object stores the quote
  reference
* In addition, we determine the type of user inputs allowable in each state. For example, finishing a quote is not
  allowed in `main menu state`

The following sequence diagram shows how an `add` operation uses the QuotelyState

!['quotelystate-implementation'](./src/quotelystate-implementation.png)

The commands depend on QuotelyState in this manner:

* `register` : available in all state
* `quote` : main menu only
* `unquote` : can use no quote name if inside a quote
* `show` : available in all state as of v1.0
* `finish` : inside quote only
* `delete` : can use without quote name if inside a quote
* `add` : can use without quote name if inside a quote
* `total` : can use without quote name if inside a quote
* `nav` : available in all states, but need to specify target location e.g. 'main' or quoteName
* `exit` : available in all state as of v1.0

### export feature

The export feature generates a PDF invoice/quotation from a Quote object. It is intended to let users produce a printable, shareable PDF of the quote they have composed in the CLI.

Overview
--------
- Triggered by the `export` command. When executed, the application delegates formatting and file creation to the PDF writer component.
- Current implementation writes a file named `invoice.pdf` to the working directory (see implementation notes below).

User-facing behaviour
---------------------
- If the user is inside a quote (editing a specific quote), they may run the command without arguments to export the active quote:

```
export
```

- If the user is in the main menu, they must specify the quote name to export:

```
export n/QUOTE_NAME
```

- On success the UI prints a confirmation message (e.g. "Exporting quote: <QUOTE_NAME>") and `invoice.pdf` is created/overwritten in the directory where the program was started.

Example (full workflow)
-----------------------
1. Create a quote and add items:

```
quote n/office chairs c/Acme Ltd
add i/Chair p/45.00 q/10
add i/Wheels p/2.50 q/40
```

2. Export the active quote (inside quote) or use the named export from main:

```
export
```
or
```
export n/office chairs
```

The sequence diagram below illustrates the steps taken when the `export` command is executed.

!['export-feature'](./src/ExportFeature.png)

When the export completes, the application generates a PDF file named `invoice.pdf` in the working directory. The PDF uses an invoice-style layout that includes header information and an itemised table showing each item's description, quantity, unit price, tax, and computed amounts (subtotal, tax, and grand total).

Preview of the generated PDF:

!['invoice'](./src/invoice.png)

Developer notes (implementation)
--------------------------------
- Command: `seedu.quotely.command.ExportQuoteCommand` (parses the `export` command and constructs the command object). See `src/main/java/seedu/quotely/command/ExportQuoteCommand.java`.
- Writer: `seedu.quotely.writer.PDFWriter` handles PDF generation. The current method `writeQuoteToPDF(Quote, CompanyName)` formats and writes `invoice.pdf`. See `src/main/java/seedu/quotely/writer/PDFWriter.java`.
- Logging: the command logs via the centralized `LoggerConfig` utility.

Implementation considerations & TODOs
-----------------------------------
- Make output filename configurable (e.g., `export f/FILE.pdf`) so users can choose a name/location.
- Add a UI confirmation with the full path of the created file.
- Improve templates and styling (header/footer, company logo, multiple page handling).
- Add tests around the command parsing and delegate behaviour; avoid asserting file contents in unit tests (use integration tests or file-existence checks).



### next feature 2

### next feature 3

## Product scope

### Target user profile

1) a user who can accomplish most tasks faster via a command line interface (CLI)
2) sales worker who handles quotation and invoicing
3) small business owner starting out and does not use paid quotation software yet
4) wants to send quotation as text or PDF, instead of using chat text

### Value proposition

Current methods of quotation by small businesses use Excel or other manual methods (for
example: type manually on whatsapp, telegram chat) to generate quotations.

Quotely is a free CLI based Quotation generator that allows users in sales to handle quotations for free without using
paid quotation generator software. Quotations are stored, and offers ability to manage quotations faster than using
informal tools such as social media
chat.

### User Stories

| Version | As a ...                                                                 | I want to ...                                   | So that I can ...                                                      |
|---------|--------------------------------------------------------------------------|-------------------------------------------------|------------------------------------------------------------------------|
| v1.0    | sales worker                                                             | add items to quote                              | keep track of items in the quote                                       | 
| v1.0    | sales worker                                                             | delete item from quote                          | keep track of items in the quote and get rid of wrong or outdated info |
| v1.0    | small online merchant that uses whatsapp and telegram to quote customers | generate invoices in text form                  | save time typing the full format                                       |
| v1.0    | new user                                                                 | view my quotations and sales                    | have better oversight of my own work                                   |
| v1.0    | sales worker                                                             | auto sum the total amount and calculate the tax | send a finished quote                                                  |
| v2.0    | business owner or accountant                                             | set customised Tax rate for each item           | add non taxed items to quote                                           |
| v2.0    | user                                                                     | save quotes on to local drive                   | can save data between sessions of use                                  |
| v2.0    | user                                                                     | export quote as PDF                             | send quotes to clients as PDF                                          |
| v2.0    | user                                                                     | show particular quote                           | save time searching when I have many quotes                            |
| v2.0    | user                                                                     | add business/customer address                   | include more info in the quotation                                     |
| v2.1    | user or accountant                                                       | calculate installments                          | save time from manually calculating                                    |
| v2.1    | user or accountant                                                       | perform currency conversions                    | quote internationally                                                  |
| v2.1    | user                                                                     | have different PDF and text templates           | have different quotation formats                                       |

## Logging

We use the centralized logging configuration for the Quotely application.

### Configuration Files

#### 1. `src/main/resources/logging.properties`
This file contains the global logging configuration:
- Log levels for different packages/classes
- File output settings (location, size, rotation)
- Console output settings
- Formatter settings

#### 2. `src/main/java/seedu/quotely/util/LoggerConfig.java`
Centralized logging utility class that:
- Loads configuration from `logging.properties`
- Provides fallback programmatic configuration
- Manages logger instances
- Allows runtime configuration changes

### How to Use Logging in Your Classes

#### 1. Import and Get Logger
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

#### 2. Logging Levels (from most to least verbose)
- `FINEST` - Most detailed tracing
- `FINER` - Detailed tracing  
- `FINE` - Debug information
- `CONFIG` - Configuration messages
- `INFO` - General information (default console level)
- `WARNING` - Warning messages
- `SEVERE` - Error messages

For more detail refer to the [logging guide](./Logging.md)


## Non-Functional Requirements

* Data: non-volatile, does not get corrupted with use
* Data: user should be able to store at least 50 quotes
* Data: total sum and tax calculations must be accurate
* Technical requirements: should be able to work on Windows and Mac as (common OSes)
* Performance requirements: the responses to user input should not take more than 500ms
* Debugging: the admin or developer should be able to view a log of activities

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

## Documentation, logging, testing, configuration, dev-ops

- [Logging guide](./Logging.md)
- [Diagram guide](./DiagramGuide.md)