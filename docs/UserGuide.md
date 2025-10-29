# User Guide

## Table of Content

- [User Guide](#user-guide)
  - [Table of Content](#table-of-content)
  - [Introduction](#introduction)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Register Company Name: `register`](#register-company-name-register)
    - [Creating a Quote: `quote`](#creating-a-quote-quote)
    - [Deleting a Quote: `unquote`](#deleting-a-quote-unquote)
    - [Adding an item: `add`](#adding-an-item-add)
    - [Delete an item `delete`](#delete-an-item-delete)
    - [Calculate the total `total`](#calculate-the-total-total)
    - [Export a quote: `export`](#export-a-quote-export)
    - [Finish the Quote `finish`](#finish-the-quote-finish)
    - [Navigate: `nav`](#navigate-nav)
    - [Show all Quotes: `show`](#show-all-quotes-show)
    - [Exit `exit`](#exit-exit)
  - [FAQ](#faq)
  - [Command Summary](#command-summary)

## Introduction

This user guide provides step-by-step instructions with examples on how to use Quotely, a CLI based application to help
you create quotes efficiently and cleanly.

V1.0 usage starts from the main menu, and enters the quotation menu when creating or editing a quote. The user may
return to the main menu by finishing or deleting the quote.

## Quick Start

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Quotely`
   from [here](https://github.com/AY2526S1-CS2113-W10-1/tp/releases/download/v1.0/quotely.jar).

## Features

### Register Company Name: `register`

Register your company name. This is the name of your company billing the customer.

Command is available in both main menu and during quotation.

**Format:**

```
register c/COMPANY_NAME
```

* The `COMPANY_NAME` can be in a natural language format.

**Example:**

```
register c/NTU
register c/NUS
```

**Expected output:**

```
Registering company: NUS
```

### Creating a Quote: `quote`

Create a new quote for a customer.

Command is available only in the main menu (i.e. new quote cannot be created concurrently with editing another quote).

**Format:**

```
quote n/QUOTE_NAME c/CUSTOMER_NAME
```

* The `QUOTE_NAME` and `CUSTOMER_NAME` can be in a natural language format.
* This command can only be used in main menu.

**Example:**

```
quote n/007 c/NTU
quote n/new quote c/NUS
```

**Expected output:**

```
Adding quote: new quote for NUS
```

### Deleting a Quote: `unquote`

Delete Quote and all items inside for a given quote name.

Command is available in both main menu and during quotation.

* Deleting a quote from main menu requires user to specify quote name.
* Deleting a quote from within a quotation makes quote name optional.

**Format:**

```
unquote {n/QUOTE_NAME}
```

* The `QUOTE_NAME` can be in a natural language format.
* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, that quote will be deleted instead of the
  current one)

**Example:**

During quotation (deletes current quote):

```
unquote
```

In main menu or during quotation (deletes specified quote):

```
unquote n/007
unquote n/new quote
```

**Expected output:**

```
Deleting quote: new quote
```

### Adding an item: `add`

Add item to the quote with the unit cost and quantity.

Command is available in both main menu and during quotation.

* Adding item from main menu requires user to specify quote name.
* Adding item from within a quotation makes quote name optional.

**Format:**

```
add i/ITEM_NAME {n/QUOTE_NAME} p/PRICE q/QUANTITY {t/TAX_RATE}
```

* The `ITEM_NAME` and `QUOTE_NAME` can be in a natural language format.
* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, item will be added to that quote instead of
  the current one)
* The `PRICE` should be decimal.
* The `QUANTITY` should be integer.
* The `TAX_RATE` should be decimal and between 0, 100.

**Example:**

During quotation (adds item to current quote):

```
add i/apple p/20.3 q/20
add i/book p/10.5 q/3
```

In main menu or during quotation (add item to specified quote):

```
add i/apple n/quote_1 p/20.3 q/20
add i/book n/quote_100 p/10.5 q/3
```

**Expected output:**

```
Adding book to quote quote_100 with price 10.50, quantity 3
```

### Delete an item `delete`

Deletes an item of matching name completely from the list of items in the quote, regardless of quantity.

Command is available in both main menu and during quotation.

* Deleting item from main menu requires user to specify quote name.
* Deleting item from within a quotation makes quote name optional.

**Format:**

```
delete i/ITEM_NAME {n/QUOTE_NAME}
```

* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, item will be deleted from that quote
  instead of the current one)

**Example:**

During quotation (deletes item to current quote):

```
delete i/apple
delete i/book
```

In main menu or during quotation (deletes item to specified quote):

```
delete i/apple n/quote_1
delete i/book n/quote_2
```

**Expected output:**

```
Deleting book from quote quote_100
```

### Calculate the total `total`

Calculate the total of specific quote.

Command is available in both main menu and during quotation.

* Calculating quote total from main menu requires user to specify quote name.
* Calculating quote total from within a quotation makes quote name optional.

**Format:**

```
total {n/QUOTE_NAME}
```

* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, total will be calculated for that quote
  instead of the current one)

**Example:**

During quotation (calculates total amount of current quote):

```
total
```

In main menu or during quotation (calculates total amount of specified quote):

```
total n/quote 1
```

**Expected output:**

```
Total cost of quote quote 1 for c: 1023.4
```

### Export a quote: `export`

Export a quote to PDF (invoice-style). The command formats the selected Quote and writes a PDF file into the current working directory.

Key behaviours
- If run while editing a quote (inside quote), `export` with no `n/` argument exports the active quote.
- If run from the main menu, you must provide the quote name via `n/QUOTE_NAME`.
- Use `f/FILE_NAME` to explicitly set the output file name; otherwise the quote name is used.

**Format:**

```
export {n/QUOTE_NAME} {f/FILE_NAME}
```

Notes on filenames
- `FILE_NAME` may be provided with or without the `.pdf` extension. The application will ensure the final file uses the `.pdf` extension.

**Example:**

Export the active quote (inside quote):

```
export
```

Export a named quote from the main menu:

```
export n/quote_1
```

Export a named quote and save it as `Quote.pdf`:

```
export n/quote_1 f/Quote
```

### Finish the Quote `finish`

Finalise the current quote that the user is working on and exit to the main menu.

Command is available only during quotation.

**Example:**

```
finish
```

**Expected output:**

```
Finishing quote process.
```

### Navigate: `nav`

Navigate to other states. 

Command is available in both main menu and during quotation.
* If in main menu, go to specific quote
* If quoting, go to main menu, or other quote

**Format:**

```
nav main
nav {n/QUOTE_NAME}
```

**Example:**

```
nav main
nav n/quote1
```

**Expected output**

The expected output shows navigation from quote1 to main menu then back to quote1.
```
quote1 > nav main
____________________________________________________________
Navigating to the main menu.
____________________________________________________________
main > nav n/quote1
____________________________________________________________
Navigating to quote: quote1
____________________________________________________________
quote1 > 
```

### Show all Quotes: `show`

Show the current state of all quotes.

Command is available in both main menu and during quotation.

**Example:**

```
show
```

**Expected output:**

```
______QUOTE_____________________________________________________
| Company name: NUS                                            |
| Quote ID: quote_100                                          |
| Customer name: company                                       |
|--------------------------------------------------------------|
| Description                               | QTY |  Unit cost |
|--------------------------------------------------------------|
| book                                      | 100 | $    12.40 |
|--------------------------------------------------------------|
|                                                              |
|                             Subtotal:               $1240.00 |
|                             GST:                     $111.60 |
|                             Total:                  $1351.60 |
|______________________________________________________________|
```

### Exit `exit`

Exit the program.

Command is available in both main menu and during quotation.

**Example:**

```
exit
```

**Expected output:**

```
Bye. Hope to see you again soon!
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Feature is not available in V1.0, addition is planned for future versions. 

## Command Summary

* Register company name `register c/CUSTOMER_NAME`
* Create a quote `quote n/QUOTE_NAME c/CUSTOMER_NAME`
* Delete a quote `unquote n/QUOTE_NAME`
* Add an item `add i/ITEM_NAME n/QUOTE_NAME p/PRICE q/QUANTITY`
* Delete an item `delete i/ITEM_NAME n/QUOTE_NAME`
* Finish the Quote `finish`
* Show all quotes `show`
* Exit the program `exit`
