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
  `idevennt` INT(11) NOT NULL AUTO_INCREMENT,
  `evenDate` DATETIME NOT NULL,
  `eventLogo` TEXT NULL DEFAULT NULL,
  `eventname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idevennt`),
<<<<<<< HEAD
  UNIQUE INDEX `idevenement_UNIQUE` (`idevennt` ASC))
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
  `event_idevennt` INT(11) NOT NULL,
  `eventDate` DATETIME NULL,
  PRIMARY KEY (`location_idlocation`, `event_idevennt`),
  INDEX `fk_location_has_event_event1_idx` (`event_idevennt` ASC),
  INDEX `fk_location_has_event_location1_idx` (`location_idlocation` ASC),
  CONSTRAINT `fk_location_has_event_location1`
    FOREIGN KEY (`location_idlocation`)
    REFERENCES `dryves`.`location` (`idlocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_location_has_event_event1`
    FOREIGN KEY (`event_idevennt`)
    REFERENCES `dryves`.`event` (`idevennt`)
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
  `location_has_event_event_idevennt` INT(11) NOT NULL,
  `subscriptiondate` DATETIME NOT NULL,
  `car_registration` INT(11) NOT NULL,
  PRIMARY KEY (`user_iduser`, `location_has_event_location_idlocation`, `location_has_event_event_idevennt`),
  INDEX `fk_user_has_location_has_event_location_has_event1_idx` (`location_has_event_location_idlocation` ASC, `location_has_event_event_idevennt` ASC),
  INDEX `fk_user_has_location_has_event_user1_idx` (`user_iduser` ASC),
  INDEX `fk_user_has_location_hat_event_car1_idx` (`car_registration` ASC),
  CONSTRAINT `fk_user_has_location_has_event_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `dryves`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_location_has_event_location_has_event1`
    FOREIGN KEY (`location_has_event_location_idlocation` , `location_has_event_event_idevennt`)
    REFERENCES `dryves`.`location_has_event` (`location_idlocation` , `event_idevennt`)
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
=======
  UNIQUE KEY `idevenement_UNIQUE` (`idevennt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table friends
# ------------------------------------------------------------

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `user_iduser` int(11) NOT NULL,
  `user_iduser1` int(11) NOT NULL,
  `relation` varchar(45) NOT NULL,
  PRIMARY KEY (`user_iduser`,`user_iduser1`),
  KEY `fk_user_has_user_user2_idx` (`user_iduser1`),
  KEY `fk_user_has_user_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_user_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2` FOREIGN KEY (`user_iduser1`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `town` varchar(45) NOT NULL,
  `userAvatar` text,
  `gender` varchar(1) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `fbid` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`iduser`, `name`, `lastName`, `town`, `userAvatar`, `gender`, `address`, `zipcode`, `phone`, `email`, `fbid`)
VALUES
	(5,'Tomas','Harkema','Zaandam','https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/v/t1.0-1/p480x480/1533921_10205602659936715_956316193636095_n.jpg?oh=3befcbbaa815eaac885f1dede3139a07&oe=54FC5F6C&__gda__=1430299513_42b0afaf8cf6093afb5976b60f67dd0c','m',NULL,NULL,NULL,'tomas@harkema.in',10205680827170847);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_has_event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_has_event`;

CREATE TABLE `user_has_event` (
  `user_iduser` int(11) NOT NULL,
  `event_idevent` int(11) NOT NULL,
  `car_registration` int(11) NOT NULL,
  PRIMARY KEY (`user_iduser`,`event_idevent`),
  KEY `fk_user_has_evenement_evenement1_idx` (`event_idevent`),
  KEY `fk_user_has_evenement_user_idx` (`user_iduser`),
  KEY `fk_user_has_evenement_Voertuig1_idx` (`car_registration`),
  CONSTRAINT `fk_user_has_evenement_Voertuig1` FOREIGN KEY (`car_registration`) REFERENCES `car` (`registration`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_evenement_evenement1` FOREIGN KEY (`event_idevent`) REFERENCES `event` (`idevennt`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_evenement_user` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
>>>>>>> 8def7aaa6503a782382e59f834dc4882ae6e9b36
