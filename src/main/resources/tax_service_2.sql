-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tax_service_2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tax_service_2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tax_service_2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `tax_service_2` ;

-- -----------------------------------------------------
-- Table `tax_service_2`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`company` (
                                                         `idcompany` INT NOT NULL AUTO_INCREMENT,
                                                         `idUser` VARCHAR(45) NULL DEFAULT NULL,
    `count_employee` VARCHAR(45) NULL DEFAULT NULL,
    `city` VARCHAR(45) NULL DEFAULT NULL,
    `street` VARCHAR(45) NULL DEFAULT NULL,
    `number_of_building` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`idcompany`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_service_2`.`entity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`entity` (
                                                        `identity` INT NOT NULL AUTO_INCREMENT,
                                                        `entity` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`identity`),
    UNIQUE INDEX `entity_UNIQUE` (`entity` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_service_2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`user` (
                                                      `idUser` INT NOT NULL AUTO_INCREMENT,
                                                      `email` VARCHAR(45) NOT NULL,
    `password` VARCHAR(145) NOT NULL,
    `entity` INT NOT NULL,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `TIN` BIGINT NOT NULL,
    `city` VARCHAR(45) NOT NULL,
    `street` VARCHAR(45) NOT NULL,
    `number_of_building` INT NOT NULL,
    PRIMARY KEY (`idUser`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `entity_idx` (`entity` ASC) VISIBLE,
    CONSTRAINT `entity`
    FOREIGN KEY (`entity`)
    REFERENCES `tax_service_2`.`entity` (`identity`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 16
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_service_2`.`companys_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`companys_users` (
                                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                                `id_user` INT NULL DEFAULT NULL,
                                                                `id_company` INT NULL DEFAULT NULL,
                                                                `id_user_entity` INT NULL DEFAULT NULL,
                                                                PRIMARY KEY (`id`),
    INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
    INDEX `id_company_idx` (`id_company` ASC) VISIBLE,
    INDEX `id_user_entity_idx` (`id_user_entity` ASC) VISIBLE,
    CONSTRAINT `id_company`
    FOREIGN KEY (`id_company`)
    REFERENCES `tax_service_2`.`company` (`idcompany`),
    CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `tax_service_2`.`user` (`idUser`),
    CONSTRAINT `id_user_entity`
    FOREIGN KEY (`id_user_entity`)
    REFERENCES `tax_service_2`.`entity` (`identity`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_service_2`.`inspector`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`inspector` (
                                                           `idinspector` INT NOT NULL AUTO_INCREMENT,
                                                           `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idinspector`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_service_2`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`status` (
                                                        `idstatus` INT NOT NULL AUTO_INCREMENT,
                                                        `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idstatus`),
    UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_service_2`.`report_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`report_type` (
                                                             `idreportType` INT NOT NULL AUTO_INCREMENT,
                                                             `type` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`idreportType`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 7
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_service_2`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tax_service_2`.`report` (
                                                        `idReport` INT NOT NULL AUTO_INCREMENT,
                                                        `idUser` INT NOT NULL,
                                                        `idInspector` INT NULL DEFAULT NULL,
                                                        `idType` INT NOT NULL,
                                                        `idStatus` INT NOT NULL,
                                                        `created` DATE NULL DEFAULT NULL,
                                                        `total_income` DOUBLE NULL DEFAULT NULL,
                                                        `total_deductions` DOUBLE NULL DEFAULT NULL,
                                                        `taxable_income` DOUBLE NULL DEFAULT NULL,
                                                        `total_tax_owned` DOUBLE NULL DEFAULT NULL,
                                                        `total_paid` DOUBLE NULL DEFAULT NULL,
                                                        `commentUser` VARCHAR(1000) NULL DEFAULT NULL,
    `commentInspector` VARCHAR(1000) NULL DEFAULT NULL,
    PRIMARY KEY (`idReport`),
    INDEX `idUser_idx` (`idUser` ASC) VISIBLE,
    INDEX `reportType_idx` (`idType` ASC) VISIBLE,
    INDEX `idInspector_idx` (`idInspector` ASC) VISIBLE,
    INDEX `idStatus_idx` (`idStatus` ASC) VISIBLE,
    CONSTRAINT `idInspector`
    FOREIGN KEY (`idInspector`)
    REFERENCES `tax_service_2`.`inspector` (`idinspector`),
    CONSTRAINT `idStatus`
    FOREIGN KEY (`idStatus`)
    REFERENCES `tax_service_2`.`status` (`idstatus`),
    CONSTRAINT `idUser`
    FOREIGN KEY (`idUser`)
    REFERENCES `tax_service_2`.`user` (`idUser`),
    CONSTRAINT `reportType`
    FOREIGN KEY (`idType`)
    REFERENCES `tax_service_2`.`report_type` (`idreportType`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 34
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
