-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: capstone
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `borrowing_bikes`
--

DROP TABLE IF EXISTS `borrowing_bikes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowing_bikes` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `bike_id` int NOT NULL,
  `total_time` timestamp NULL DEFAULT NULL,
  `borrowed_at` timestamp NULL DEFAULT NULL,
  `borrowed_at_dock_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_borrowing_user_id` (`user_id`),
  KEY `fk_borrowing_bike_id` (`bike_id`),
  KEY `fk_borrowing_dock_id` (`borrowed_at_dock_id`),
  CONSTRAINT `fk_borrowing_bike_id` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`id`),
  CONSTRAINT `fk_borrowing_dock_id` FOREIGN KEY (`borrowed_at_dock_id`) REFERENCES `dock` (`id`),
  CONSTRAINT `fk_borrowing_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowing_bikes`
--

LOCK TABLES `borrowing_bikes` WRITE;
/*!40000 ALTER TABLE `borrowing_bikes` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrowing_bikes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-08 21:34:10
