# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.22)
# Database: dryves
# Generation Time: 2015-01-04 16:07:01 +0000
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
	(4,'Peugeot','GRAY','2008',6,2),
	(5,'Peogeot','BLACK','5008',8,1);

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
	(1,'2015-06-05 00:00:00','https://scontent-a-ams.xx.fbcdn.net/hphotos-xfa1/v/t1.0-9/10868223_712006772247621_2310229468790641873_n.png?oh=420cd44554890ee2ac44316296f9a904&oe=553BD038','http://2013.jsconf.us/img/2013-JSConfUS-Family.jpg','JSConf',NULL,'hjhbjhbjh',NULL),
	(2,'2015-06-27 00:00:00','http://www.guestzone.nl/gfx/user_photos/12146/company_logo_12146.png','http://www.prg.com/wp-content/uploads/2013/07/Awakenings-4.jpg','AWAKENINGS Festival 2015',0,'Awakenings Festival 2015 will be held on Saturday June 27th and Sunday June 28th. Are you joining us for our 15th edition?\n\nFor Awakenings indoor editions visit www.awakenings.nl.','https://www.facebook.com/events/336121726512902/'),
	(3,'2015-06-14 00:00:00','http://ep.id-t.com/dam/asset/idt/sensation/news/netherlands/newsarticle.jpg?image=500x500-C.jpg&uuid=qxkpfx7p35a4xbmqek7dkuxs2a','http://www.djvibe.com/content/wp-content/uploads/Sensation_Celebrate-Life_6.jpg','SENSATION AMSTERDAM 2015',1,'15 years ago it all started with a dream.\nA dream to gather people to celebrate life.\n \nAfter 2001 that dream spread over the world.\nMillions of people united in white and became one.\n \nWe are still overwhelmed by the success Sensation has become.\nAnd we are thankful that so many of you have joined us on our journey.\n \nOn the 4th of July 2015 we will celebrate our 15th anniversary.\nWe are planning to do this in an extraordinary way with a unique show that will only take place in Amsterdam.\n \nTickets will not go on sale until March 2015, but you can already secure your entrance right now by booking one of our Sensation travel packages. Make your Sensation weekend complete and get:\n- Your Sensation ticket;\n- One or more overnight stays in a hotel of your choice;\n- Transportation between the hotel and the Amsterdam ArenA;\n- A special Sensation gift.\n \nWould you like your experience to be even bigger and better? You can fully customize your package with all kinds of extras and/or book a KLM flight.\n \nCheck out the options here: www.Sensation.com/travel\n \nGet your friends together and celebrate with us!',NULL),
	(4,'2014-06-14 00:00:00','http://ep.id-t.com/dam/asset/idt/sensation/news/netherlands/newsarticle.jpg?image=500x500-C.jpg&uuid=qxkpfx7p35a4xbmqek7dkuxs2a','http://www.djvibe.com/content/wp-content/uploads/Sensation_Celebrate-Life_6.jpg','SENSATION AMSTERDAM 2014',1,'15 years ago it all started with a dream.\nA dream to gather people to celebrate life.\n \nAfter 2001 that dream spread over the world.\nMillions of people united in white and became one.\n \nWe are still overwhelmed by the success Sensation has become.\nAnd we are thankful that so many of you have joined us on our journey.\n \nOn the 4th of July 2015 we will celebrate our 15th anniversary.\nWe are planning to do this in an extraordinary way with a unique show that will only take place in Amsterdam.\n \nTickets will not go on sale until March 2015, but you can already secure your entrance right now by booking one of our Sensation travel packages. Make your Sensation weekend complete and get:\n- Your Sensation ticket;\n- One or more overnight stays in a hotel of your choice;\n- Transportation between the hotel and the Amsterdam ArenA;\n- A special Sensation gift.\n \nWould you like your experience to be even bigger and better? You can fully customize your package with all kinds of extras and/or book a KLM flight.\n \nCheck out the options here: www.Sensation.com/travel\n \nGet your friends together and celebrate with us!',NULL);

/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table friends
# ------------------------------------------------------------

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `user_iduser` int(11) NOT NULL,
  `user_iduser1` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `approved` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_iduser`,`user_iduser1`),
  KEY `fk_user_has_user_user2_idx` (`user_iduser1`),
  KEY `fk_user_has_user_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_user_has_user_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2` FOREIGN KEY (`user_iduser1`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;

INSERT INTO `friends` (`user_iduser`, `user_iduser1`, `date`, `approved`)
VALUES
	(1,2,'2015-01-04 16:38:09',0),
	(3,1,'2015-01-04 16:44:06',0),
	(4,1,'2015-01-04 17:06:41',1);

/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `idlocation` int(11) NOT NULL AUTO_INCREMENT,
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
	(0,'Spaawnwoude','Deelplan Houtrak',NULL,'Recreatiegebied Spaarnwoude'),
	(1,'Amsterdam','Arena Park',NULL,'ArenA');

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
	(2,'Tomas Harkema Test','Zaandam','https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/t31.0-1/p960x960/10633483_10205602659936715_956316193636095_o.jpg','m',NULL,NULL,NULL,'tomas@harkema.in',102056808271708478,NULL),
	(3,'Tomas Harkema Test2','Zaandam','https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/t31.0-1/p960x960/10633483_10205602659936715_956316193636095_o.jpg','m',NULL,NULL,NULL,'tomas@harkema.in',102056808271708478,NULL),
	(4,'Tomas Harkema Test3','Zaandam','https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/t31.0-1/p960x960/10633483_10205602659936715_956316193636095_o.jpg','m',NULL,NULL,NULL,'tomas@harkema.in',102056808271708478,NULL);

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
	(1,1,3,'2014-12-31 15:48:15'),
	(1,2,3,'2014-12-29 21:09:30'),
	(1,3,3,'2014-12-30 14:23:42'),
	(1,4,3,'2015-01-03 18:29:48'),
	(2,2,3,'2014-12-29 18:27:20');

/*!40000 ALTER TABLE `user_has_event` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
