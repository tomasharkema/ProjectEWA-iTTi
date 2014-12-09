SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `dryves` ;
CREATE SCHEMA IF NOT EXISTS `dryves` DEFAULT CHARACTER SET utf8 ;
USE `dryves` ;

-- -----------------------------------------------------
-- Table `dryves`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`user` ;

CREATE TABLE IF NOT EXISTS `dryves`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `town` VARCHAR(45) NOT NULL,
  `userAvatar` BLOB NULL DEFAULT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `zipcode` VARCHAR(6) NULL DEFAULT NULL,
  `phone` VARCHAR(11) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `fbid` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `iduser_UNIQUE` (`iduser` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dryves`.`car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`car` ;

CREATE TABLE IF NOT EXISTS `dryves`.`car` (
  `registration` INT(11) NOT NULL AUTO_INCREMENT,
  `brand` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `numberSeats` INT(11) NOT NULL,
  `user_iduser` INT(11) NOT NULL,
  PRIMARY KEY (`registration`),
  UNIQUE INDEX `idVoertuig_UNIQUE` (`registration` ASC),
  INDEX `fk_Voertuig_user1` (`user_iduser` ASC),
  CONSTRAINT `fk_Voertuig_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dryves`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`event` ;

CREATE TABLE IF NOT EXISTS `dryves`.`event` (
  `idevennt` INT(11) NOT NULL AUTO_INCREMENT,
  `eventName` VARCHAR(45) NOT NULL,
  `eventLocation` VARCHAR(45) NOT NULL,
  `evenDate` DATETIME NOT NULL,
  `eventLogo` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`idevennt`),
  UNIQUE INDEX `idevenement_UNIQUE` (`idevennt` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dryves`.`friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`friends` ;

CREATE TABLE IF NOT EXISTS `dryves`.`friends` (
  `user_iduser` INT(11) NOT NULL,
  `user_iduser1` INT(11) NOT NULL,
  `relation` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_iduser`, `user_iduser1`),
  INDEX `fk_user_has_user_user2_idx` (`user_iduser1` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`user_iduser1`)
    REFERENCES `dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dryves`.`user_has_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`user_has_event` ;

CREATE TABLE IF NOT EXISTS `dryves`.`user_has_event` (
  `user_iduser` INT(11) NOT NULL,
  `event_idevent` INT(11) NOT NULL,
  `car_registration` INT(11) NOT NULL,
  PRIMARY KEY (`user_iduser`, `event_idevent`),
  INDEX `fk_user_has_evenement_evenement1_idx` (`event_idevent` ASC),
  INDEX `fk_user_has_evenement_user_idx` (`user_iduser` ASC),
  INDEX `fk_user_has_evenement_Voertuig1_idx` (`car_registration` ASC),
  CONSTRAINT `fk_user_has_evenement_evenement1`
    FOREIGN KEY (`event_idevent`)
    REFERENCES `dryves`.`event` (`idevennt`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_evenement_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_evenement_Voertuig1`
    FOREIGN KEY (`car_registration`)
    REFERENCES `dryves`.`car` (`registration`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
