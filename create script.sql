# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.21)
# Database: dryves
# Generation Time: 2014-12-11 20:19:49 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table car
# ------------------------------------------------------------

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `registration` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `numberSeats` int(11) NOT NULL,
  `user_iduser` int(11) NOT NULL,
  PRIMARY KEY (`registration`),
  UNIQUE KEY `idVoertuig_UNIQUE` (`registration`),
  KEY `fk_Voertuig_user1` (`user_iduser`),
  CONSTRAINT `fk_Voertuig_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `idevennt` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(45) NOT NULL,
  `eventLocation` varchar(45) NOT NULL,
  `evenDate` datetime NOT NULL,
  `eventLogo` blob,
  PRIMARY KEY (`idevennt`),
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

INSERT INTO `user` (`iduser`, `name`, `town`, `userAvatar`, `gender`, `address`, `zipcode`, `phone`, `email`, `fbid`)
VALUES
	(5,'Tomas Harkema','Zaandam','https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/v/t1.0-1/p480x480/1533921_10205602659936715_956316193636095_n.jpg?oh=3befcbbaa815eaac885f1dede3139a07&oe=54FC5F6C&__gda__=1430299513_42b0afaf8cf6093afb5976b60f67dd0c','m',NULL,NULL,NULL,'tomas@harkema.in',10205680827170847);

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
