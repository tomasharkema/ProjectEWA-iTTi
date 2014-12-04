SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `Dryves` ;
CREATE SCHEMA IF NOT EXISTS `Dryves` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Dryves` ;

-- -----------------------------------------------------
-- Table `Dryves`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dryves`.`user` ;

CREATE TABLE IF NOT EXISTS `Dryves`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `voorletters` VARCHAR(45) NOT NULL,
  `woonplaats` VARCHAR(45) NOT NULL,
  `userAvatar` BLOB NULL,
  `geslacht` VARCHAR(1) NOT NULL,
  `adres` VARCHAR(45) NULL,
  `postcode` VARCHAR(6) NULL,
  `telefoon` VARCHAR(11) NULL,
  `email` VARCHAR(45) NOT NULL,
  `fbid` INT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `iduser_UNIQUE` (`iduser` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dryves`.`Voertuig`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dryves`.`Voertuig` ;

CREATE TABLE IF NOT EXISTS `Dryves`.`Voertuig` (
  `kenteken` INT NOT NULL AUTO_INCREMENT,
  `merk` VARCHAR(45) NOT NULL,
  `kleur` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `aantalZitplaatsen` INT NOT NULL,
  `user_iduser` INT NOT NULL,
  UNIQUE INDEX `idVoertuig_UNIQUE` (`kenteken` ASC),
  PRIMARY KEY (`kenteken`),
  CONSTRAINT `fk_Voertuig_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `Dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dryves`.`evenement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dryves`.`evenement` ;

CREATE TABLE IF NOT EXISTS `Dryves`.`evenement` (
  `idevenement` INT NOT NULL AUTO_INCREMENT,
  `evenementNaam` VARCHAR(45) NOT NULL,
  `evenementLocatie` VARCHAR(45) NOT NULL,
  `evenementDatum` DATETIME NOT NULL,
  `evenementLogo` BLOB NULL,
  PRIMARY KEY (`idevenement`),
  UNIQUE INDEX `idevenement_UNIQUE` (`idevenement` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dryves`.`user_has_evenement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dryves`.`user_has_evenement` ;

CREATE TABLE IF NOT EXISTS `Dryves`.`user_has_evenement` (
  `user_iduser` INT NOT NULL,
  `evenement_idevenement` INT NOT NULL,
  `Voertuig_kenteken` INT NOT NULL,
  PRIMARY KEY (`user_iduser`, `evenement_idevenement`),
  INDEX `fk_user_has_evenement_evenement1_idx` (`evenement_idevenement` ASC),
  INDEX `fk_user_has_evenement_user_idx` (`user_iduser` ASC),
  INDEX `fk_user_has_evenement_Voertuig1_idx` (`Voertuig_kenteken` ASC),
  CONSTRAINT `fk_user_has_evenement_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `Dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_evenement_evenement1`
    FOREIGN KEY (`evenement_idevenement`)
    REFERENCES `Dryves`.`evenement` (`idevenement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_evenement_Voertuig1`
    FOREIGN KEY (`Voertuig_kenteken`)
    REFERENCES `Dryves`.`Voertuig` (`kenteken`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Dryves`.`friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dryves`.`friends` ;

CREATE TABLE IF NOT EXISTS `Dryves`.`friends` (
  `user_iduser` INT NOT NULL,
  `user_iduser1` INT NOT NULL,
  `relatie` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_iduser`, `user_iduser1`),
  INDEX `fk_user_has_user_user2_idx` (`user_iduser1` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `Dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`user_iduser1`)
    REFERENCES `Dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
