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
  `town` VARCHAR(45) NOT NULL,
  `userAvatar` TEXT NULL,
  `gender` VARCHAR(1) NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `zipcode` VARCHAR(6) NULL DEFAULT NULL,
  `phone` VARCHAR(11) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `fbid` BIGINT NULL DEFAULT NULL,
  `admin` TINYINT(1) NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `iduser_UNIQUE` (`iduser` ASC))
ENGINE = InnoDB;


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
  `idevent` INT(11) NOT NULL AUTO_INCREMENT,
  `eventDate` DATETIME NOT NULL,
  `eventLogo` TEXT NULL DEFAULT NULL,
  `eventName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idevent`),
  UNIQUE INDEX `idevenement_UNIQUE` (`idevent` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dryves`.`friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`friends` ;

CREATE TABLE IF NOT EXISTS `dryves`.`friends` (
  `user_iduser` INT(11) NOT NULL,
  `user_iduser1` INT(11) NOT NULL,
  `relation` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dryves`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`location` ;

CREATE TABLE IF NOT EXISTS `dryves`.`location` (
  `idlocation` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `locationpicture` TEXT NULL,
  `locationname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idlocation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dryves`.`location_has_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`location_has_event` ;

CREATE TABLE IF NOT EXISTS `dryves`.`location_has_event` (
  `location_idlocation` INT NOT NULL,
  `event_idevent` INT(11) NOT NULL,
  `eventDate` DATETIME NULL,
  PRIMARY KEY (`location_idlocation`, `event_idevent`),
  INDEX `fk_location_has_event_event1_idx` (`event_idevent` ASC),
  INDEX `fk_location_has_event_location1_idx` (`location_idlocation` ASC),
  CONSTRAINT `fk_location_has_event_location1`
    FOREIGN KEY (`location_idlocation`)
    REFERENCES `dryves`.`location` (`idlocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_has_event_event1`
    FOREIGN KEY (`event_idevent`)
    REFERENCES `dryves`.`event` (`idevent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dryves`.`user_has_event_at_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dryves`.`user_has_event_at_location` ;

CREATE TABLE IF NOT EXISTS `dryves`.`user_has_event_at_location` (
  `user_iduser` INT(11) NOT NULL,
  `location_has_event_location_idlocation` INT NOT NULL,
  `location_has_event_event_idevent` INT(11) NOT NULL,
  `subscriptiondate` DATETIME NOT NULL,
  `car_registration` INT(11) NOT NULL,
  PRIMARY KEY (`user_iduser`, `location_has_event_location_idlocation`, `location_has_event_event_idevent`),
  INDEX `fk_user_has_location_has_event_location_has_event1_idx` (`location_has_event_location_idlocation` ASC, `location_has_event_event_idevent` ASC),
  INDEX `fk_user_has_location_has_event_user1_idx` (`user_iduser` ASC),
  INDEX `fk_user_has_location_hat_event_car1_idx` (`car_registration` ASC),
  CONSTRAINT `fk_user_has_location_has_event_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_location_has_event_location_has_event1`
    FOREIGN KEY (`location_has_event_location_idlocation` , `location_has_event_event_idevent`)
    REFERENCES `dryves`.`location_has_event` (`location_idlocation` , `event_idevent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_location_hat_event_car1`
    FOREIGN KEY (`car_registration`)
    REFERENCES `dryves`.`car` (`registration`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
