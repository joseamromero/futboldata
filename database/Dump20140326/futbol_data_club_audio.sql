CREATE DATABASE  IF NOT EXISTS `futbol_data` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `futbol_data`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: spontedev32    Database: futbol_data
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `club_audio`
--

DROP TABLE IF EXISTS `club_audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_audio` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `club_id` int(10) unsigned NOT NULL,
  `audio_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`club_id`,`audio_id`),
  KEY `fk_club_has_audio_audio1_idx` (`audio_id`),
  KEY `fk_club_has_audio_club1_idx` (`club_id`),
  CONSTRAINT `fk_club_has_audio_audio1` FOREIGN KEY (`audio_id`) REFERENCES `audio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_club_has_audio_club1` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_audio`
--

LOCK TABLES `club_audio` WRITE;
/*!40000 ALTER TABLE `club_audio` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_audio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-26  9:08:20