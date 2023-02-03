CREATE DATABASE  IF NOT EXISTS `doranco_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `doranco_db`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: doranco_db
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adresse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `rue` varchar(45) NOT NULL,
  `ville` varchar(45) NOT NULL,
  `code_postal` varchar(45) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_adresse_utilisateur_id_idx` (`user_id`),
  CONSTRAINT `FK_adresse_utilisateur_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresse`
--

LOCK TABLES `adresse` WRITE;
/*!40000 ALTER TABLE `adresse` DISABLE KEYS */;
INSERT INTO `adresse` VALUES (1,20,'Bouleavrd de Charonne','Paris','75020',9),(2,21,'Rue des Artistes','Lyon','69000',9),(3,20,'Bouleavrd de Charonne','Paris','75020',10),(4,21,'Rue des Artistes','Lyon','69000',10);
/*!40000 ALTER TABLE `adresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salaire`
--

DROP TABLE IF EXISTS `salaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salaire` (
  `id` int NOT NULL AUTO_INCREMENT,
  `janvier` float DEFAULT '0',
  `fevrier` float DEFAULT '0',
  `mars` float DEFAULT '0',
  `avril` float DEFAULT '0',
  `mai` float DEFAULT '0',
  `juin` float DEFAULT '0',
  `juillet` float DEFAULT '0',
  `aout` float DEFAULT '0',
  `septembre` float DEFAULT '0',
  `octobre` float DEFAULT '0',
  `novembre` float DEFAULT '0',
  `decembre` float DEFAULT '0',
  `annee` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_salaire_annee_user_id` (`annee`,`user_id`),
  KEY `FK_salaire_user_id_idx` (`user_id`),
  CONSTRAINT `FK_salaire_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salaire`
--

LOCK TABLES `salaire` WRITE;
/*!40000 ALTER TABLE `salaire` DISABLE KEYS */;
INSERT INTO `salaire` VALUES (2,4000,4000,3000,0,0,0,0,0,0,0,0,0,2022,9),(3,0,0,3000,0,0,4000,5000,3000,3000,0,0,0,2019,9);
/*!40000 ALTER TABLE `salaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(10) DEFAULT NULL,
  `prenom` varchar(100) NOT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `genre` varchar(45) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `date_entree` date NOT NULL,
  `date_sortie` date DEFAULT NULL,
  `titre` varchar(45) NOT NULL,
  `salaire_de_base` float NOT NULL,
  `email` varchar(100) NOT NULL,
  `statut` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'hugo@gmail.com','Développeur'),(3,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'higo@gmail.com','Développeur'),(5,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'higo@gmail.com','Développeur'),(6,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'higo@gmail.com','Développeur'),(7,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'higo@gmail.com','Développeur'),(8,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'higo@gmail.com','Développeur'),(9,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'higo@gmail.com','Développeur'),(10,'HUGO','Victor','003312344567','H','1960-02-15','2019-11-23',NULL,'CDA',2300,'higo@gmail.com','Développeur');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-03 15:51:41
