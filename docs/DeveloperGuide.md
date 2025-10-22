# Developer Guide

- [Acknowledgements](#Acknowledgements)
- [Design](#Design)
    - [Architecture](#Architecture)
    - [Parser Component](#Parser-Component)
    - [Command Component](#Command-Component)
    - [Ui Component](#Ui-Component)
    - [Data Component](#Data-Component)
- [Implementation](#Implementation)
- [Product Scope](#Product-scope)
    - [Target user profile](#Target-user-profile)
    - [Value proposition](#Value-proposition)
    - [User Stories](#User-Stories)
- [Non-Functional Requirements](#Non-Functional-Requirements)
- [Glossary](#Glossary)
- [Instructions for manual testing](#Instructions-for-manual-testing)
- [Documentation, logging, testing, configuration, dev-ops](#Documentation,logging,testing,configuration,dev-ops)

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

!['Parser diagram'](./src/ParserDiagram.png)

How the parsing works...
(maybe more sequence diagram)

### Command Component

!['Command diagram'](./src/CommandDiagram.png)

All `Command` subtypes inherit from the abstract `Command` class which defines a command word and execute method

(more explanation)

### Ui Component

!['Ui diagram'](./src/UiDiagram.png)

The `UI` component consists of

* (method) (explanation)

### Data Component

!['Data diagram'](./src/DataDiagram.png)

(sequence diagram)


### File storage Component

To be implemented.

## Implementation

(future implementations, V2.0/V2.1)

[Proposed] xxx feature

The proposed mechanism is facilitated by... it does ....
The following operations shall be implemented...

(state diagrams)

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

## Non-Functional Requirements

{Give non-functional requirements}

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