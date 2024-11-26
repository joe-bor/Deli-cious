DROP DATABASE IF EXISTS deli;

CREATE DATABASE IF NOT EXISTS deli;

USE deli;

CREATE TABLE `Toppings` (
    `toppingID` INTEGER NOT NULL AUTO_INCREMENT,
    `toppingName` VARCHAR(25) NOT NULL,
    CONSTRAINT `PK_Toppings` PRIMARY KEY (`toppingID`)
);

CREATE TABLE `Sandwiches` (
    `sandwichID` INTEGER NOT NULL AUTO_INCREMENT,
    `breadType` ENUM("WHITE", "WHEAT", "RYE", "WRAP") NOT NULL,
    `sandwichSize` ENUM("SMALL", "MEDIUM", "LARGE") NOT NULL,
    `toasted` BOOLEAN DEFAULT FALSE,
    CONSTRAINT `PK_Sandwiches` PRIMARY KEY (`sandwichID`)
);

CREATE TABLE `Sandwich_Toppings` (
    `sandwichID` INTEGER NOT NULL,
    `toppingID` INTEGER NOT NULL,
    `extra` BOOLEAN DEFAULT FALSE,
    CONSTRAINT `FK_Sandwich` FOREIGN KEY (`sandwichID`) REFERENCES `Sandwiches` (`sandwichID`),
    CONSTRAINT `FK_Topping` FOREIGN KEY (`toppingID`) REFERENCES `Toppings` (`toppingID`)
);