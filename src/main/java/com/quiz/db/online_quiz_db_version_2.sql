-- MySQL Script generated by MySQL Workbench
-- Wed Feb 19 18:04:54 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OnlineQuizDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema OnlineQuizDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OnlineQuizDB` DEFAULT CHARACTER SET utf8;
USE `OnlineQuizDB`;

-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`USER`
(
    `ID`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `FIRST_NAME`    VARCHAR(255) NOT NULL,
    `LAST_NAME`     VARCHAR(255) NOT NULL,
    `USERNAME`      VARCHAR(255) NULL,
    `PASSWORD`      VARCHAR(255) NOT NULL,
    `EMAIL`         VARCHAR(255) NOT NULL,
    `PHONE_NUMBER`  VARCHAR(255) NULL,
    `STATUS`        TINYINT(1)   NULL,
    `CREATION_DATE` BIGINT(20)   NULL,
    PRIMARY KEY (`ID`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`CONFIRMATION_TOKEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`CONFIRMATION_TOKEN`
(
    `ID`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`            BIGINT(20) NOT NULL,
    `CONFIRMATION_TOKEN` INT        NULL,
    PRIMARY KEY (`ID`),
    INDEX `USER_ID_CONFIRMATION_TOKEN_idx` (`USER_ID` ASC) VISIBLE,
    CONSTRAINT `USER_ID_CONFIRMATION_TOKEN`
        FOREIGN KEY (`USER_ID`)
            REFERENCES `OnlineQuizDB`.`USER` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`AUTHENTICATION_TOKEN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`AUTHENTICATION_TOKEN`
(
    `ID`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `USER_ID`       BIGINT(20)   NULL,
    `TOKEN`         VARCHAR(255) NULL,
    `CREATION_DATE` BIGINT(20)   NULL,
    PRIMARY KEY (`ID`),
    INDEX `USER_ID_AUTHENTICATION_TOKEN_idx` (`USER_ID` ASC) VISIBLE,
    CONSTRAINT `USER_ID_AUTHENTICATION_TOKEN`
        FOREIGN KEY (`USER_ID`)
            REFERENCES `OnlineQuizDB`.`USER` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`ROLE`
(
    `ID`   INT          NOT NULL AUTO_INCREMENT,
    `ROLE` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`USER_ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`USER_ROLE`
(
    `ID`      BIGINT(20) NOT NULL AUTO_INCREMENT,
    `USER_ID` BIGINT(20) NOT NULL,
    `ROLE_ID` INT        NOT NULL,
    PRIMARY KEY (`ID`),
    INDEX `ROLE_ID_USER_ROLE_idx` (`ROLE_ID` ASC) VISIBLE,
    INDEX `USER_ID_USER_ROLE_idx` (`USER_ID` ASC) VISIBLE,
    CONSTRAINT `USER_ID_USER_ROLE`
        FOREIGN KEY (`USER_ID`)
            REFERENCES `OnlineQuizDB`.`USER` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `ROLE_ID_USER_ROLE`
        FOREIGN KEY (`ROLE_ID`)
            REFERENCES `OnlineQuizDB`.`ROLE` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`QUIZ`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`QUIZ`
(
    `ID`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `NAME`          VARCHAR(255) NULL,
    `CREATION_DATE` BIGINT(20)   NULL,
    `CATEGORY_ID`   BIGINT(20)   NULL,
    PRIMARY KEY (`ID`),
    INDEX `CATEGORY_ID_QUIZ_idx` (`CATEGORY_ID` ASC) VISIBLE,
    CONSTRAINT `CATEGORY_ID_QUIZ`
        FOREIGN KEY (`CATEGORY_ID`)
            REFERENCES `OnlineQuizDB`.`QUIZ_CATEGORY` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`QUESTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`QUESTION`
(
    `ID`            BIGINT(20) NOT NULL AUTO_INCREMENT,
    `QUIZ_ID`       BIGINT(20) NULL,
    `QUESTION`      TEXT(5000) NULL,
    `CREATION_DATE` BIGINT(20) NULL,
    PRIMARY KEY (`ID`),
    INDEX `QUIZ_ID_QUESTION_idx` (`QUIZ_ID` ASC) VISIBLE,
    CONSTRAINT `QUIZ_ID_QUESTION`
        FOREIGN KEY (`QUIZ_ID`)
            REFERENCES `OnlineQuizDB`.`QUIZ` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`ANSWER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`ANSWER`
(
    `ID`            BIGINT(20) NOT NULL AUTO_INCREMENT,
    `QUESTION_ID`   BIGINT(20) NULL,
    `ANSWER`        TEXT(5000) NULL,
    `SCORE`         INT        NULL,
    `STATUS`        TINYINT(1) NULL,
    `CREATION_DATE` BIGINT(20) NULL,
    PRIMARY KEY (`ID`),
    INDEX `QUESTION_ID_ANSWER_idx` (`QUESTION_ID` ASC) VISIBLE,
    CONSTRAINT `QUESTION_ID_ANSWER`
        FOREIGN KEY (`QUESTION_ID`)
            REFERENCES `OnlineQuizDB`.`QUESTION` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`USER_INFO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`USER_INFO`
(
    `ID`         BIGINT(20) NOT NULL AUTO_INCREMENT,
    `USER_ID`    BIGINT(20) NULL,
    `GENDER`     TINYINT(1) NULL,
    `BIRTH_DATE` BIGINT(20) NULL,
    PRIMARY KEY (`ID`),
    INDEX `USER_ID_USER_INFO_idx` (`USER_ID` ASC) VISIBLE,
    CONSTRAINT `USER_ID_USER_INFO`
        FOREIGN KEY (`USER_ID`)
            REFERENCES `OnlineQuizDB`.`USER` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`PASSED_QUIZ`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`PASSED_QUIZ`
(
    `ID`      BIGINT(20) NOT NULL AUTO_INCREMENT,
    `USER_ID` BIGINT(20) NULL,
    `QUIZ_ID` BIGINT(20) NULL,
    `SCORE`   BIGINT(20) NULL,
    PRIMARY KEY (`ID`),
    INDEX `USER_ID_PASSED_QUIZ_idx` (`USER_ID` ASC) VISIBLE,
    INDEX `QUIZ_ID_PASSED_QUIZ_idx` (`QUIZ_ID` ASC) VISIBLE,
    CONSTRAINT `USER_ID_PASSED_QUIZ`
        FOREIGN KEY (`USER_ID`)
            REFERENCES `OnlineQuizDB`.`USER` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `QUIZ_ID_PASSED_QUIZ`
        FOREIGN KEY (`QUIZ_ID`)
            REFERENCES `OnlineQuizDB`.`QUIZ` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`PASSED_QUIZ_ANSWER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`PASSED_QUIZ_ANSWER`
(
    `PASSED_QUIZ_ID` BIGINT(20) NULL,
    `ANSWER_ID`      BIGINT(20) NULL,
    INDEX `ANSWER_ID_PASSED_QUIZ_ANSWER_idx` (`ANSWER_ID` ASC) VISIBLE,
    INDEX `PASSED_QUIZ_ID_PASSED_QUIZ_ANSWER_idx` (`PASSED_QUIZ_ID` ASC) VISIBLE,
    CONSTRAINT `ANSWER_ID_PASSED_QUIZ_ANSWER`
        FOREIGN KEY (`ANSWER_ID`)
            REFERENCES `OnlineQuizDB`.`ANSWER` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `PASSED_QUIZ_ID_PASSED_QUIZ_ANSWER`
        FOREIGN KEY (`PASSED_QUIZ_ID`)
            REFERENCES `OnlineQuizDB`.`PASSED_QUIZ` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`QUIZ_COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`QUIZ_COMMENT`
(
    `ID`            BIGINT(20) NOT NULL AUTO_INCREMENT,
    `QUIZ_ID`       BIGINT(20) NULL,
    `USER_ID`       BIGINT(20) NULL,
    `COMMENT`       TEXT(5000) NULL,
    `CREATION_DATE` BIGINT(20) NULL,
    PRIMARY KEY (`ID`),
    INDEX `USER_ID_QUIZ_COMMENT_idx` (`USER_ID` ASC) VISIBLE,
    INDEX `QUIZ_ID_QUIZ_COMMENT_idx` (`QUIZ_ID` ASC) VISIBLE,
    CONSTRAINT `USER_ID_QUIZ_COMMENT`
        FOREIGN KEY (`USER_ID`)
            REFERENCES `OnlineQuizDB`.`USER` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `QUIZ_ID_QUIZ_COMMENT`
        FOREIGN KEY (`QUIZ_ID`)
            REFERENCES `OnlineQuizDB`.`QUIZ` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineQuizDB`.`QUIZ_CATEGORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OnlineQuizDB`.`QUIZ_CATEGORY`
(
    `ID`               BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `CATEGORY`         VARCHAR(255) NOT NULL,
    `DESCRIPTION`      VARCHAR(255) NOT NULL,
    `ROOT_CATEGORY_ID` BIGINT(20)   NULL,
    PRIMARY KEY (`ID`),
    INDEX `ROOT_CATEGORY_ID_QUIZ_CATEGORY_idx` (`ROOT_CATEGORY_ID` ASC) VISIBLE,
    CONSTRAINT `ROOT_CATEGORY_ID_QUIZ_CATEGORY`
        FOREIGN KEY (`ROOT_CATEGORY_ID`)
            REFERENCES `OnlineQuizDB`.`QUIZ_CATEGORY` (`ID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
