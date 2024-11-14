# Deli-cious

This project is a command-line interface (CLI) application that allows users to browse through stores, customize
orders (mostly sandwiches), and confirm orders. Think doordash, but much less complex and only caters to sandwich shops.

[Link to GitHub Project](https://github.com/users/joe-bor/projects/7/views/1)

---

## Table of Contents

- [Requirements](#requirements)
- [Usage](#usage)
- [How it Works](#how-it-works)
- [Features](#features)
- [Screenshots](#screenshots)
- [File `transactions.csv` Format](#file-transactionscsv-format)
- [Custom Search Capabilities](#custom-search-capabilities)

---

## Requirements

- [Git](https://git-scm.com/downloads)
- [Java 21](https://www.oracle.com/th/java/technologies/downloads/) or Higher
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

---

---

## How It Works

![overview](README-Images/flow_overview.png)

- **Customer**: Represents the User of the application.
- **User Interface**: Manages the interaction flow, including store selections and order creation.
- **Order**: Contains details about the customer's order.
- **SandwichShop**: Handles the order fulfillment and stores the `Receipt` object.
- **Receipt**: Generated once the order is confirmed, and can be used for future references.

---

## Features

1. **Store Selection**
    - Customers can select a store.
    - Some stores offer `toppings` exclusive to them.
    - Others have `pre-built` sandwiches you can customize.
2. **Order Building**
    - Customers can build and customize their orders(e.g. choose bread type, toppings, etc.)
3. **Receipt Management**
   - Each store maintains a record of confirmed orders.
   - For every transaction (Receipt), a corresponding directory is created in the root of the repository, where a text file representation of the transaction is stored.

---

## Screenshots

***Project Structure***

![Project Structure](README-Images/project-structure.png)


***First Draft of UML***

![First Draft of UML](README-Images/UML-firsDraft.png)
