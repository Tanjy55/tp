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
    - [Finish the Quote `finish`](#finish-the-quote-finish)
    - [Show all Quotes: `show`](#show-all-quotes-show)
    - [Exit `exit`](#exit-exit)
  - [FAQ](#faq)
  - [Command Summary](#command-summary)

## Introduction

This user guide provides step-by-step instructions with examples on how to use Quotely.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Quotely` from [here](http://link.to/Quotely).


## Features 

{Give detailed description of each feature}

### Register Company Name: `register`
Register company name.

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
Delete Quote and all items inside for a given quote name

**Format:**
```
unquote {n/QUOTE_NAME}
```

* The `QUOTE_NAME` can be in a natural language format.
* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, that quote will be deleted instead of the current one)

**Example:** 
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

**Format:**
```
add i/ITEM_NAME {n/QUOTE_NAME} p/PRICE q/QUANTITY
```

* The `ITEM_NAME` and `QUOTE_NAME` can be in a natural language format.
* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, that quote will be deleted instead of the current one)
* The `PRICE` should be decimal.
* The `QUANTITY` should be integer.

**Example:** 
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

**Format:**
```
delete i/ITEM_NAME {n/QUOTE_NAME}
```

* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, that quote will be deleted instead of the current one)


**Example:** 
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

**Format:**
```
total {n/QUOTE_NAME}
```

* `{n/QUOTE_NAME}` - Quote name (optional when inside a quote; if specified, that quote will be deleted instead of the current one)


**Example:** 
```
total n/quote 1
```

**Expected output:**
```
Total cost of quote quote 1 for c: 1023.4
```

### Finish the Quote `finish`
Finalise the current quote that the user is working on.

* This command can only be used inside a quote.

**Example:** 
```
finish
```

**Expected output:**
```
Finishing quote process.
```

### Show all Quotes: `show`
Show the current state of all quotes.

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

**A**: {your answer here}

## Command Summary

* Register company name `register c/CUSTOMER_NAME`
* Create a quote `quote n/QUOTE_NAME c/CUSTOMER_NAME`
* Delete a quote `unquote n/QUOTE_NAME`
* Add an item `add i/ITEM_NAME n/QUOTE_NAME p/PRICE q/QUANTITY`
* Delete an item `delete i/ITEM_NAME n/QUOTE_NAME`
* Finish the Quote `finish`
* Show all quotes `show`
* Exit the program `exit`
