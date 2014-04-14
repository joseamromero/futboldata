CREATE DATABASE  IF NOT EXISTS `futbol_data` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `futbol_data`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 128.32.16.3    Database: futbol_data
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
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugador` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `persona_id` int(10) unsigned NOT NULL,
  `posicion_id` int(10) unsigned DEFAULT NULL,
  `camiseta` int(11) DEFAULT NULL,
  `zurdo` int(11) DEFAULT NULL COMMENT 'zurdo: 0, para derecho; 1; para zurdo; 2 ambidiestro',
  `altura` double DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `equipo_id` int(10) unsigned DEFAULT NULL,
  `tipo_contrato_id` int(10) unsigned NOT NULL,
  `status` int(11) DEFAULT NULL,
  `agente_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_persona1_idx` (`persona_id`),
  KEY `fk_jugador_posicion1_idx` (`posicion_id`),
  KEY `fk_jugador_equipo1_idx` (`equipo_id`),
  KEY `fk_jugador_tipo_contrato1_idx` (`tipo_contrato_id`),
  KEY `fk_jugador_agente1_idx` (`agente_id`),
  CONSTRAINT `fk_jugador_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_posicion1` FOREIGN KEY (`posicion_id`) REFERENCES `posicion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_equipo1` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_tipo_contrato1` FOREIGN KEY (`tipo_contrato_id`) REFERENCES `tipo_contrato` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_agente1` FOREIGN KEY (`agente_id`) REFERENCES `agente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-08 17:26:13
