# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.22)
# Database: dryves
# Generation Time: 2014-12-29 17:48:47 +0000
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

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;

INSERT INTO `car` (`registration`, `brand`, `color`, `type`, `numberSeats`, `user_iduser`)
VALUES
	(3,'Peugeot','BLUE','107',4,1),
	(4,'Peugeot','GRAY','2008',6,2);

/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `idevent` int(11) NOT NULL AUTO_INCREMENT,
  `eventDate` datetime NOT NULL,
  `eventLogo` text,
  `eventWall` text,
  `eventName` varchar(45) NOT NULL,
  `locationid` int(11) DEFAULT NULL,
  `description` text,
  `fbevent` text,
  PRIMARY KEY (`idevent`),
  UNIQUE KEY `idevenement_UNIQUE` (`idevent`),
  KEY `fk_event_location1_idx` (`locationid`),
  CONSTRAINT `fk_event_location1` FOREIGN KEY (`locationid`) REFERENCES `location` (`idlocation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;

INSERT INTO `event` (`idevent`, `eventDate`, `eventLogo`, `eventWall`, `eventName`, `locationid`, `description`, `fbevent`)
VALUES
	(1,'2015-06-05 00:00:00','http://jsconf.com/images/jsconf_ar.png','http://2013.jsconf.us/img/2013-JSConfUS-Family.jpg','JSConf',NULL,'hjhbjhbjh',NULL),
	(2,'2015-06-27 00:00:00','http://www.guestzone.nl/gfx/user_photos/12146/company_logo_12146.png','https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xpa1/v/t1.0-9/10451690_10152516915834153_8756390416509656584_n.jpg?oh=50e04067ed7cc3fc94c82d3515803a90&oe=54FF281E&__gda__=1425939464_50125263e8f9b7c6a00c793ac5db4009','AWAKENINGS Festival 2015',0,'AWAAAAKUUNIIINGGS','https://www.facebook.com/events/336121726512902/');

/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table friends
# ------------------------------------------------------------

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `user_iduser` int(11) NOT NULL,
  `user_iduser1` int(11) NOT NULL,
  `relation` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_iduser`,`user_iduser1`),
  KEY `fk_user_has_user_user2_idx` (`user_iduser1`),
  KEY `fk_user_has_user_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_user_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2` FOREIGN KEY (`user_iduser1`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;

INSERT INTO `friends` (`user_iduser`, `user_iduser1`, `relation`, `date`, `approved`)
VALUES
	(1,2,'','2014-12-29 16:57:06',1);

/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `idlocation` int(11) NOT NULL,
  `city` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `locationpicture` text,
  `locationname` varchar(45) NOT NULL,
  PRIMARY KEY (`idlocation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;

INSERT INTO `location` (`idlocation`, `city`, `address`, `locationpicture`, `locationname`)
VALUES
	(0,'Spaawnwoude','Deelplan Houtrak',NULL,'Recreatiegebied Spaarnwoude');

/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `town` varchar(45) NOT NULL,
  `userAvatar` text,
  `gender` varchar(1) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `fbid` bigint(20) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`iduser`, `name`, `town`, `userAvatar`, `gender`, `address`, `zipcode`, `phone`, `email`, `fbid`, `admin`)
VALUES
	(1,'Tomas Harkema','Zaandam','https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/t31.0-1/p960x960/10633483_10205602659936715_956316193636095_o.jpg','m',NULL,NULL,NULL,'tomas@harkema.in',10205680827170847,NULL),
	(2,'Tomas Harkema','Zaandam','https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/t31.0-1/p960x960/10633483_10205602659936715_956316193636095_o.jpg','m',NULL,NULL,NULL,'tomas@harkema.in',102056808271708478,NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_has_event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_has_event`;

CREATE TABLE `user_has_event` (
  `user_iduser` int(11) NOT NULL,
  `event_idevent` int(11) NOT NULL,
  `car_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_iduser`,`event_idevent`),
  KEY `fk_user_has_event_event1_idx` (`event_idevent`),
  KEY `fk_user_has_event_user1_idx` (`user_iduser`),
  KEY `fk_user_has_event_car1_idx` (`car_id`),
  CONSTRAINT `fk_user_has_event_car1` FOREIGN KEY (`car_id`) REFERENCES `car` (`registration`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event1` FOREIGN KEY (`event_idevent`) REFERENCES `event` (`idevent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_has_event` WRITE;
/*!40000 ALTER TABLE `user_has_event` DISABLE KEYS */;

INSERT INTO `user_has_event` (`user_iduser`, `event_idevent`, `car_id`, `date`)
VALUES
	(1,2,3,'2014-12-29 18:26:54'),
	(2,2,3,'2014-12-29 18:27:20');

/*!40000 ALTER TABLE `user_has_event` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
