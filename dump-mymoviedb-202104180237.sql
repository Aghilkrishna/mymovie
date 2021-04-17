-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: mymoviedb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking_detail`
--

DROP TABLE IF EXISTS `booking_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `booking_status` varchar(255) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `screening_end_time` datetime(6) DEFAULT NULL,
  `screening_start_time` datetime(6) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  `movie_screen_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `processed` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4dur5bgso19eshf1uq68tsi4f` (`employee_id`),
  KEY `FKph8le1v14ugk5aydqrptto5bq` (`movie_id`),
  KEY `FKj5q2r32ym4ay39mwiiuwl5usk` (`movie_screen_id`),
  KEY `FKjuy693uj83e4hpww7jbmt49pd` (`user_id`),
  CONSTRAINT `FK4dur5bgso19eshf1uq68tsi4f` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKj5q2r32ym4ay39mwiiuwl5usk` FOREIGN KEY (`movie_screen_id`) REFERENCES `movie_screen` (`id`),
  CONSTRAINT `FKjuy693uj83e4hpww7jbmt49pd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKph8le1v14ugk5aydqrptto5bq` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_detail`
--

LOCK TABLES `booking_detail` WRITE;
/*!40000 ALTER TABLE `booking_detail` DISABLE KEYS */;
INSERT INTO `booking_detail` VALUES (1,'CANCELLED',1,'2021-04-18 00:42:36.555000',NULL,'2021-04-18 00:58:26.545000',NULL,NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(4,'CANCELLED',1,'2021-04-18 00:59:06.078000',NULL,'2021-04-18 01:03:26.395000',NULL,NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(5,'CANCELLED',1,'2021-04-18 01:03:56.281000',NULL,'2021-04-18 01:08:33.843000','PAID',NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(6,'TIMEDOUT',1,'2021-04-18 01:18:44.732000',NULL,'2021-04-18 01:19:05.075000',NULL,NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(7,'SHOWEND',1,'2021-04-18 01:48:39.988000',NULL,'2021-04-18 01:50:39.628000','PAID',NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(8,'TIMEDOUT',1,'2021-04-18 01:54:32.680000',NULL,'2021-04-18 01:54:52.866000',NULL,NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(9,'SHOWEND',1,'2021-04-18 01:57:24.515000',NULL,'2021-04-18 01:58:02.493000','PAID',NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(10,'CANCELLED',1,'2021-04-18 01:58:23.757000',NULL,'2021-04-18 01:58:32.668000',NULL,NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(11,'CANCELLED',1,'2021-04-18 02:00:00.636000',NULL,'2021-04-18 02:00:21.780000','PAID',NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(12,'TIMEDOUT',1,'2021-04-18 02:08:17.196000',NULL,'2021-04-18 02:08:37.320000',NULL,NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(13,'CANCELLED',1,'2021-04-18 02:08:52.302000',NULL,'2021-04-18 02:09:42.487000','PAID',NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary ''),(14,'TIMEDOUT',1,'2021-04-18 02:24:04.627000',NULL,'2021-04-18 02:24:24.716000',NULL,NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(15,'CANCELLED',1,'2021-04-18 02:24:52.364000',NULL,'2021-04-18 02:25:07.751000','PAID',NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '\0'),(16,'SHOWEND',1,'2021-04-18 02:25:39.554000',NULL,'2021-04-18 02:26:35.948000','PAID',NULL,'2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',NULL,1,1,1,_binary '');
/*!40000 ALTER TABLE `booking_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_detail_seats`
--

DROP TABLE IF EXISTS `booking_detail_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_detail_seats` (
  `booking_detail_id` bigint NOT NULL,
  `seats_id` bigint NOT NULL,
  PRIMARY KEY (`booking_detail_id`,`seats_id`),
  UNIQUE KEY `UK_q985iwxeiij3dgw351q67jlix` (`seats_id`),
  CONSTRAINT `FK2icf9h2p4g12sjit1nejmdtqk` FOREIGN KEY (`booking_detail_id`) REFERENCES `booking_detail` (`id`),
  CONSTRAINT `FK2x5mig7scdehhl5b8m7gmt56i` FOREIGN KEY (`seats_id`) REFERENCES `seat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_detail_seats`
--

LOCK TABLES `booking_detail_seats` WRITE;
/*!40000 ALTER TABLE `booking_detail_seats` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_detail_seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `threatre_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fopic1oh5oln2khj8eat6ino0` (`email`),
  UNIQUE KEY `UK_4ts03wxs8exmr93khm543lt4x` (`mobile`),
  KEY `FKsi2829ctg4ov3pu2gyyaydnfm` (`threatre_id`),
  CONSTRAINT `FKsi2829ctg4ov3pu2gyyaydnfm` FOREIGN KEY (`threatre_id`) REFERENCES `theatre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'1991-01-01 00:00:00.000000','arun@ags.com','9999999999','Arun',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `duration_in_mins` int NOT NULL,
  `movie_status` varchar(255) DEFAULT NULL,
  `movie_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `theatre_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda1gmcmwoppk06ddoa31k2eej` (`theatre_id`),
  CONSTRAINT `FKda1gmcmwoppk06ddoa31k2eej` FOREIGN KEY (`theatre_id`) REFERENCES `theatre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,NULL,'Jordan Vogt-Roberts',120,'AVAILABLE','ENGLISH','Kong: Skull Island',1),(2,NULL,'Mari Selvaraj',180,'AVAILABLE','TAMIL','Karnan',1);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_screen`
--

DROP TABLE IF EXISTS `movie_screen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_screen` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `screening_end_time` datetime(6) DEFAULT NULL,
  `screening_start_time` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3idqrrm8pjrtjve2bfuaryi93` (`movie_id`),
  CONSTRAINT `FK3idqrrm8pjrtjve2bfuaryi93` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_screen`
--

LOCK TABLES `movie_screen` WRITE;
/*!40000 ALTER TABLE `movie_screen` DISABLE KEYS */;
INSERT INTO `movie_screen` VALUES (1,1,'2021-04-16 15:12:46.440000',NULL,NULL,'Screen 1','2021-04-16 18:00:00.000000','2021-04-16 16:00:00.000000',_binary '',1),(2,1,'2021-04-16 15:12:46.440000',NULL,NULL,'Screen 2','2021-04-16 19:00:00.000000','2021-04-16 16:00:00.000000',_binary '',2);
/*!40000 ALTER TABLE `movie_screen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_screen_aud`
--

DROP TABLE IF EXISTS `movie_screen_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_screen_aud` (
  `id` bigint NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  `modified_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `screening_end_time` datetime(6) DEFAULT NULL,
  `screening_start_time` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKx0aypob46eb2t29wavb5ayi7` (`rev`),
  CONSTRAINT `FKx0aypob46eb2t29wavb5ayi7` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_screen_aud`
--

LOCK TABLES `movie_screen_aud` WRITE;
/*!40000 ALTER TABLE `movie_screen_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_screen_aud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revinfo` (
  `rev` int NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint DEFAULT NULL,
  PRIMARY KEY (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revinfo`
--

LOCK TABLES `revinfo` WRITE;
/*!40000 ALTER TABLE `revinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `revinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `seat_number` int NOT NULL,
  `seat_row` int NOT NULL,
  `movie_screen_id` bigint DEFAULT NULL,
  `seat_status` varchar(255) DEFAULT NULL,
  `seat_type` varchar(255) DEFAULT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnnnwar5krv7nsvquy7u2as47k` (`movie_screen_id`),
  CONSTRAINT `FKnnnwar5krv7nsvquy7u2as47k` FOREIGN KEY (`movie_screen_id`) REFERENCES `movie_screen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,1,1,'OPEN','NORMAL',35),(2,2,1,1,'OPEN','NORMAL',35),(3,3,1,1,'OPEN','NORMAL',0),(4,4,1,1,'OPEN','NORMAL',0),(5,5,1,1,'OPEN','NORMAL',0),(6,6,1,1,'OPEN','NORMAL',0),(7,7,1,1,'OPEN','NORMAL',0),(8,8,1,1,'OPEN','NORMAL',0),(9,9,1,1,'OPEN','NORMAL',0),(10,10,1,1,'OPEN','NORMAL',0),(11,1,2,1,'OPEN','EXECUTIVE',0),(12,2,2,1,'OPEN','EXECUTIVE',0),(13,3,2,1,'OPEN','EXECUTIVE',0),(14,4,2,1,'OPEN','EXECUTIVE',0),(15,5,2,1,'OPEN','EXECUTIVE',0),(16,6,2,1,'OPEN','EXECUTIVE',0),(17,7,2,1,'OPEN','EXECUTIVE',0),(18,8,2,1,'OPEN','EXECUTIVE',0),(19,9,2,1,'OPEN','EXECUTIVE',0),(20,10,2,1,'OPEN','EXECUTIVE',0),(21,1,1,2,'OPEN','NORMAL',0),(22,2,1,2,'OPEN','NORMAL',0),(23,3,1,2,'OPEN','NORMAL',0),(24,4,1,2,'OPEN','NORMAL',0),(25,5,1,2,'OPEN','NORMAL',0),(26,6,1,2,'OPEN','NORMAL',0),(27,7,1,2,'OPEN','NORMAL',0),(28,8,1,2,'OPEN','NORMAL',0),(29,9,1,2,'OPEN','NORMAL',0),(30,10,1,2,'OPEN','NORMAL',0),(31,1,2,2,'OPEN','EXECUTIVE',0),(32,2,2,2,'OPEN','EXECUTIVE',0),(33,3,2,2,'OPEN','EXECUTIVE',0),(34,4,2,2,'OPEN','EXECUTIVE',0),(35,5,2,2,'OPEN','EXECUTIVE',0),(36,6,2,2,'OPEN','EXECUTIVE',0),(37,7,2,2,'OPEN','EXECUTIVE',0),(38,8,2,2,'OPEN','EXECUTIVE',0),(39,9,2,2,'OPEN','EXECUTIVE',0),(40,10,2,2,'OPEN','EXECUTIVE',0);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre`
--

DROP TABLE IF EXISTS `theatre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rating` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre`
--

LOCK TABLES `theatre` WRITE;
/*!40000 ALTER TABLE `theatre` DISABLE KEYS */;
INSERT INTO `theatre` VALUES (1,'Villivakkam','AGS Cinemas',4);
/*!40000 ALTER TABLE `theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre_employees`
--

DROP TABLE IF EXISTS `theatre_employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatre_employees` (
  `theatre_id` bigint NOT NULL,
  `employees_id` bigint NOT NULL,
  UNIQUE KEY `UK_op6xh7jhutnbr0agqx8fr1i5n` (`employees_id`),
  KEY `FKg15b2haupf7prw6alkmniw7p8` (`theatre_id`),
  CONSTRAINT `FKac6vwbrck5fh2ohk6pxe6w0ha` FOREIGN KEY (`employees_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKg15b2haupf7prw6alkmniw7p8` FOREIGN KEY (`theatre_id`) REFERENCES `theatre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre_employees`
--

LOCK TABLES `theatre_employees` WRITE;
/*!40000 ALTER TABLE `theatre_employees` DISABLE KEYS */;
INSERT INTO `theatre_employees` VALUES (1,1);
/*!40000 ALTER TABLE `theatre_employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre_movie_screens`
--

DROP TABLE IF EXISTS `theatre_movie_screens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatre_movie_screens` (
  `theatre_id` bigint NOT NULL,
  `movie_screens_id` bigint NOT NULL,
  UNIQUE KEY `UK_bqajbtyp58it584ihk0tefo3m` (`movie_screens_id`),
  KEY `FKrmbnijg9dtg244p0407g3mb3g` (`theatre_id`),
  CONSTRAINT `FK8d9qi1uw0vgsa6dofrjnvm6qq` FOREIGN KEY (`movie_screens_id`) REFERENCES `movie_screen` (`id`),
  CONSTRAINT `FKrmbnijg9dtg244p0407g3mb3g` FOREIGN KEY (`theatre_id`) REFERENCES `theatre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre_movie_screens`
--

LOCK TABLES `theatre_movie_screens` WRITE;
/*!40000 ALTER TABLE `theatre_movie_screens` DISABLE KEYS */;
INSERT INTO `theatre_movie_screens` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `theatre_movie_screens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_cnjwxx5favk5ycqajjt17fwy1` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'aghilkrishna@gmail.com','9003121507','aghil','ddz9f8Y3JaBdXwi4MEIRTV4uEblAu88aKApwN0CvTSbGYxVkLLZmTS8E');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mymoviedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-18  2:37:36
