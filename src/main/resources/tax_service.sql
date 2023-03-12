-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema taxService
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema taxService
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taxService` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `taxService` ;

-- -----------------------------------------------------
-- Table `taxService`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxService`.`company` (
  `idcompany` INT NOT NULL AUTO_INCREMENT,
  `idUser` INT NOT NULL,
  `companyName` VARCHAR(45) NULL DEFAULT NULL,
  `countEmployee` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `numberOfBuilding` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idcompany`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxService`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxService`.`users` (
  `idUsers` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(145) NOT NULL,
  `entity` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `company` VARCHAR(45) NULL DEFAULT NULL,
  `TIN` BIGINT NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Street` VARCHAR(45) NOT NULL,
  `NumberOfBuilding` INT NOT NULL,
  PRIMARY KEY (`idUsers`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxService`.`reporttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxService`.`reporttype` (
  `idreportType` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idreportType`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taxService`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxService`.`report` (
  `idReport` INT NOT NULL AUTO_INCREMENT,
  `idUser` INT NOT NULL,
  `idInspector` INT NULL DEFAULT NULL,
  `idType` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `profitSum` DOUBLE NULL DEFAULT NULL,
  `taxSum` DOUBLE NULL DEFAULT NULL,
  `fine` DOUBLE NULL DEFAULT NULL,
  `penny` DOUBLE NULL DEFAULT NULL,
  `commentUser` VARCHAR(1000) NULL DEFAULT NULL,
  `commentInspector` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`idReport`),
  INDEX `idUser_idx` (`idUser` ASC) VISIBLE,
  INDEX `reportType_idx` (`idType` ASC) VISIBLE,
  CONSTRAINT `idUser`
    FOREIGN KEY (`idUser`)
    REFERENCES `taxService`.`users` (`idUsers`),
  CONSTRAINT `reportType`
    FOREIGN KEY (`idType`)
    REFERENCES `taxService`.`reporttype` (`idreportType`))
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
