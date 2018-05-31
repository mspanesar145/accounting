-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: accounting
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `document_ratings`
--

DROP TABLE IF EXISTS `document_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_ratings` (
  `document_rating_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `user_document_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`document_rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_ratings`
--

LOCK TABLES `document_ratings` WRITE;
/*!40000 ALTER TABLE `document_ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_accounts`
--

DROP TABLE IF EXISTS `my_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_accounts` (
  `my_account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `is_notification_on` bit(1) DEFAULT NULL,
  `main_cource_id` bigint(20) DEFAULT NULL,
  `news_letter_subscribed` bit(1) DEFAULT NULL,
  `secondry_cource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`my_account_id`),
  KEY `FK_ahmt550b8yd8ogsk8tiotm6j4` (`created_by_id`),
  CONSTRAINT `FK_ahmt550b8yd8ogsk8tiotm6j4` FOREIGN KEY (`created_by_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_accounts`
--

LOCK TABLES `my_accounts` WRITE;
/*!40000 ALTER TABLE `my_accounts` DISABLE KEYS */;
INSERT INTO `my_accounts` VALUES (19,'2018-05-14 18:10:33','2018-05-14 18:11:27',23,'',2,'\0',12);
/*!40000 ALTER TABLE `my_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_categories`
--

DROP TABLE IF EXISTS `profile_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_categories` (
  `profile_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`profile_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_categories`
--

LOCK TABLES `profile_categories` WRITE;
/*!40000 ALTER TABLE `profile_categories` DISABLE KEYS */;
INSERT INTO `profile_categories` VALUES (1,'CPT',NULL),(2,'IPCC',NULL),(3,'Final',NULL),(4,'Professional',NULL),(5,'Others',NULL),(6,'Accounts',1),(7,'Economics',1),(8,'Mercantile Law',1),(9,'Quantitative Aptitude',1),(10,'Accounts',2),(11,'Taxation',2),(12,'Cost & Financial Management',2),(13,'Law',2),(14,'Advanced Accounts',2),(15,'Auditing & Assurance',2),(16,'ITSM',2),(17,'Financial Reporting',3),(18,'Strategic Financial Management',3),(19,'Corporate And Allied Law',3),(20,'Advanced Auditing & Assurance',3),(21,'Advanced Management Accounting',3),(22,'ISCA',3),(23,'Direct Taxes',3),(24,'Indirect Taxes',3),(25,'Income Tax',4),(26,'Companies Act. 2013',4),(27,'GST',4),(28,'Service Tax',4),(29,'VAT',4),(30,'Accounting & Book Keeping',4),(31,'FEMA',4),(32,'Customs',4),(33,'Excise',4),(34,'Other Misc.',4),(35,'Others',5);
/*!40000 ALTER TABLE `profile_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratings` (
  `rating_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `rated_by_id` bigint(20) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `user_document_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (1,'2018-04-29 11:07:33','2018-04-29 11:07:33',11,2,22),(2,'2018-04-29 12:31:59','2018-04-29 12:31:59',11,3,20),(3,'2018-04-30 15:28:20','2018-04-30 15:28:20',15,4,26),(4,'2018-04-30 16:47:19','2018-04-30 16:47:19',15,4,26),(5,'2018-05-02 04:55:58','2018-05-02 04:55:58',19,3,27),(6,'2018-05-02 17:06:20','2018-05-02 17:06:20',20,5,26);
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_devices`
--

DROP TABLE IF EXISTS `user_devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_devices` (
  `user_token_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `device_token` varchar(255) DEFAULT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_devices`
--

LOCK TABLES `user_devices` WRITE;
/*!40000 ALTER TABLE `user_devices` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_documents`
--

DROP TABLE IF EXISTS `user_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_documents` (
  `user_document_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `cover_image_url` varchar(255) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `sub_category_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `video_link` varchar(255) DEFAULT NULL,
  `contains_video` bit(1) DEFAULT NULL,
  `content_link_url` varchar(255) DEFAULT NULL,
  `overall_rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_documents`
--

LOCK TABLES `user_documents` WRITE;
/*!40000 ALTER TABLE `user_documents` DISABLE KEYS */;
INSERT INTO `user_documents` VALUES (26,'2018-04-30 15:27:30','2018-04-30 15:30:00',1,'Nee Scooty in town','http://159.203.95.8:8181/assets/uploads/61951909-ccdf-4804-a36d-3e5258471ea0.png',15,NULL,2,'New Dcooty',NULL,'\0',NULL,4),(27,'2018-04-30 15:32:42','2018-05-02 05:00:00',1,'Warrior Gym','http://159.203.95.8:8181/assets/uploads/67c38eb4-bae9-4a46-a4ec-b2353fc244eb.jpg',15,NULL,1,'New Scooty ','https://youtu.be/yl43NJgwx0o','',NULL,3),(28,'2018-04-30 18:13:16','2018-04-30 18:15:00',2,'this is nugget upload cover image','http://159.203.95.8:8181/assets/uploads/47ae9b80-1870-45b5-a8ce-706e0edb2b35.jpg',11,NULL,4,'nugget upload',NULL,'\0',NULL,0),(29,'2018-04-30 18:27:39','2018-04-30 18:30:00',2,'','http://159.203.95.8:8181/assets/uploads/bbd0fa7c-325d-4cf7-bec3-6ad69572a486.jpg',11,NULL,5,'uploading nugget content',NULL,'\0','http://159.203.95.8:8181/assets/uploads/e36e576b-52bb-4fc1-b6b2-3dbdee7d8f02.png',0),(30,'2018-05-02 04:54:47','2018-05-02 05:00:00',3,'hello one test abc','http://159.203.95.8:8181/assets/uploads/2419f3f0-6fc4-4e0d-b5df-55f8dc56392d.jpg',19,NULL,8,'this is test',NULL,'\0','http://159.203.95.8:8181/assets/uploads/ad5124c7-f27f-41e3-96b4-3754cae9109a.jpg',0),(31,'2018-05-04 04:41:16','2018-05-04 04:45:00',1,'test mandt','http://159.203.95.8:8181/assets/uploads/19dc72f2-26a5-4dd4-b954-c9ac715affd0.jpg',21,NULL,6,'test mandy',NULL,'\0','http://159.203.95.8:8181/assets/uploads/28eec2c1-6a9d-44d7-b876-53732c1a4d47.jpg',0),(32,'2018-05-04 04:43:32','2018-05-04 04:45:00',3,'thfhj','http://159.203.95.8:8181/assets/uploads/20976a39-9c2d-4b7a-a6c2-5c82780c810d.jpg',21,NULL,12,'test',NULL,'\0','http://159.203.95.8:8181/assets/uploads/6912575d-3192-4c56-b702-e9caa4170068.jpg',0),(33,'2018-05-04 04:44:54','2018-05-04 04:45:00',3,'uhfvhs gyfjk ffjfd hfvng ghjdchgv gfjhf fhbft gfjhf high jgvjj ghj','http://159.203.95.8:8181/assets/uploads/bb82aec7-fca0-4e0f-b0b2-08bb4938ebd4.jpg',21,NULL,12,'thestgh',NULL,'\0',NULL,0),(34,'2018-05-10 09:44:45','2018-05-10 09:45:00',1,NULL,NULL,NULL,NULL,1,NULL,NULL,'',NULL,0),(35,'2018-05-10 10:26:44','2018-05-10 10:30:00',1,NULL,NULL,NULL,NULL,4,NULL,NULL,'',NULL,0),(36,'2018-05-10 10:27:22','2018-05-10 10:30:00',1,NULL,NULL,NULL,NULL,4,NULL,NULL,'',NULL,0),(37,'2018-05-10 10:28:57','2018-05-10 10:30:00',2,NULL,'http://159.203.95.8:8181/assets/uploads/ea6fb807-b78c-4bfe-bf08-92f6d9e629c9.jpg',NULL,NULL,4,NULL,NULL,'\0','http://159.203.95.8:8181/assets/uploads/68cb7d3c-fcef-4b22-a605-a88ffd38ecf7.mp4',0),(38,'2018-05-10 10:31:38','2018-05-10 10:45:00',1,NULL,'http://159.203.95.8:8181/assets/uploads/4bb303f0-984f-4273-b306-502197cf0c58.jpg',NULL,NULL,4,NULL,NULL,'\0','http://159.203.95.8:8181/assets/uploads/4a347902-5ac6-448f-819d-537a056d0351.mp4',0),(39,'2018-05-13 11:36:51','2018-05-13 11:45:00',1,'cdhrtt','http://159.203.95.8:8181/assets/uploads/58945fcd-127a-4ff6-abb6-60e8e0183d2e.jpg',25,NULL,2,'new image ',NULL,'\0','http://159.203.95.8:8181/assets/uploads/491439f1-d6c3-488c-a942-4a7c6d4e30aa.jpg',0),(40,'2018-05-13 11:42:46','2018-05-13 11:45:00',2,' z x','http://159.203.95.8:8181/assets/uploads/45f8aae4-331c-49d4-952f-2d6a725e8928.jpg',25,NULL,3,'zadqfww',NULL,'\0','http://159.203.95.8:8181/assets/uploads/2c1cf6db-8765-42d2-97bd-e00f0be7901b.jpg',0),(41,'2018-05-13 12:21:23','2018-05-13 12:30:00',1,NULL,'http://159.203.95.8:8181/assets/uploads/7d5f6cce-66e0-49da-8578-77f5901aa2aa.jpg',NULL,NULL,1,NULL,NULL,'\0','http://159.203.95.8:8181/assets/uploads/218001a9-0901-46a9-ab83-8acc1a4dae27.jpg',0),(42,'2018-05-13 14:02:14','2018-05-13 14:15:00',3,'vdvd','http://159.203.95.8:8181/assets/uploads/3c839395-e0a9-4399-a36b-e71adbaa4335.jpg',25,NULL,2,'\nfsgsve',NULL,'\0',NULL,0),(43,'2018-05-13 14:14:25','2018-05-13 14:15:00',2,'cece','http://159.203.95.8:8181/assets/uploads/37c2b1a7-ee0a-4e8d-b2da-d9480379a08a.jpg',25,NULL,2,'dwwxw\n',NULL,'\0','http://159.203.95.8:8181/assets/uploads/444c3424-488f-4be8-ba82-8de3510b6962.jpg',0),(44,'2018-05-13 14:28:45','2018-05-13 14:30:00',2,'sgsgw','http://159.203.95.8:8181/assets/uploads/bbc9f9d6-71ae-4ac5-b9b7-562840dc3af0.jpg',25,NULL,4,'fdgehe\n',NULL,'\0','http://159.203.95.8:8181/assets/uploads/4d51f3e2-3d8f-4a89-bfa2-7ad62c71f7f3.jpg',0),(45,'2018-05-13 14:45:33','2018-05-13 15:00:00',1,'hxhx','http://159.203.95.8:8181/assets/uploads/d53be165-1a5f-420b-939b-a738c235644c.jpg',25,NULL,4,'chfj',NULL,'\0','http://159.203.95.8:8181/assets/uploads/3fbc2376-79ca-4507-af21-93e3a094387d.jpg',0),(46,'2018-05-13 16:23:06','2018-05-13 16:30:00',1,'hfucu','http://159.203.95.8:8181/assets/uploads/63e15a2a-ce64-47f5-b45d-0c18bb88fc1f.jpg',25,NULL,4,'yfyc',NULL,'\0','http://159.203.95.8:8181/assets/uploads/9abf4ec5-2d96-4fd9-8306-9ad9bfbc70c4.jpg',0),(47,'2018-05-13 16:31:26','2018-05-13 16:45:00',2,'bdhdb','http://159.203.95.8:8181/assets/uploads/425509b4-7af8-468a-92e1-0dcc88a96285.jpg',25,NULL,4,'gdhdd',NULL,'\0','http://159.203.95.8:8181/assets/uploads/96c8a25a-836f-45e0-92bf-e04024e25f9d.jpg',0),(48,'2018-05-13 17:25:53','2018-05-13 17:30:00',1,'jznz','http://159.203.95.8:8181/assets/uploads/7dc40660-6b7f-4ef0-855a-08b3e109a1e9.jpeg',25,NULL,4,'new title',NULL,'\0','http://159.203.95.8:8181/assets/uploads/789ef760-2383-4332-a118-dfc5f7d5d0e8.jpeg',0),(49,'2018-05-14 01:49:21','2018-05-14 02:00:00',3,'the information acc','http://159.203.95.8:8181/assets/uploads/1e4667c3-a5d0-4cc1-af97-4aa63e0bb0da.jpg',26,NULL,5,'my acc',NULL,'\0','http://159.203.95.8:8181/assets/uploads/55cf0b88-c4c6-450d-b6ad-15eace532a5f.jpg',0),(50,'2018-05-14 18:14:12','2018-05-14 18:15:00',2,'',NULL,23,NULL,2,'abcd','gjgg','',NULL,0),(51,'2018-05-14 18:24:05','2018-05-14 18:30:00',3,'gdgg',NULL,23,NULL,2,'abcdef','wow.mp4','',NULL,0),(52,'2018-05-14 18:26:57','2018-05-14 18:30:00',2,'','http://159.203.95.8:8181/assets/uploads/da982e29-6424-4e78-adab-1399bbc8e782.png',23,NULL,3,'asdfg','asdfg.mp4','',NULL,0);
/*!40000 ALTER TABLE `user_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facebook_auth_token` varchar(255) DEFAULT NULL,
  `facebook_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `reset_token_created_at` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `google_auth_token` text,
  `google_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FK_ugjpne1plmu0cq69ak80mc36` (`created_by_id`),
  CONSTRAINT `FK_ugjpne1plmu0cq69ak80mc36` FOREIGN KEY (`created_by_id`) REFERENCES `my_accounts` (`my_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2018-04-01 17:18:28','2018-04-01 17:18:28','mspanesar145@gmail.com',NULL,NULL,'Mandeep',NULL,'Singh','5dcda89d9c60493636b313a99fde4e92',NULL,NULL,NULL,'1522689506820eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5kZWVwMTUyMjYwMzEwNjgwOSJ9.wOLz_kMLF9lAXpIpVAdaPKWqwmp0YcCVCkRTAHEstp9scWB7x-aMeOfHAhBp-suubYPs6EYAMOZ_D1QdBkHaOw','mandeep1522603106809',NULL,NULL,NULL,NULL,NULL,NULL),(2,'2018-04-03 18:58:34','2018-04-03 18:58:34','mand@gmail.com',NULL,NULL,'mandy',NULL,'singh','5b65a28780335ee37c77c40ecec6c6dd',NULL,NULL,NULL,'1522868312821eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5keTE1MjI3ODE5MTI4MTMifQ.NTjINqYziKFfR_7BGGVqpZjsmSYu6if8TL2M7XzuuWUo8SS9Oqxu2QHtK7hf7tDxOcpMRNqBy0UZXOoBCxEZKQ','mandy1522781912813',NULL,NULL,NULL,NULL,NULL,NULL),(3,'2018-04-03 22:25:51','2018-04-03 22:25:51','test@test.com',NULL,NULL,'abc',NULL,'abc','cc4e7ba92ea0b1fc56e6ac67f682f3ea',NULL,NULL,NULL,'1522880750875eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmMxNTIyNzk0MzUwODc0In0.T2ZEJDK_MjXhAKZGec26No5EZ5XnpTWfeIWK_87F7Ybyw6fDCWgW02OxN-RcSqqp7fu_8hlJhUofX_XsaQWx4g','abc1522794350874',NULL,NULL,NULL,NULL,NULL,NULL),(4,'2018-04-04 05:47:13','2018-04-04 05:47:13','gd@gc.com',NULL,NULL,'hdhddh',NULL,'dhdh','75bd820eff5faffec164be3536fbf54c',NULL,NULL,NULL,'1522907233253eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZGhkZGgxNTIyODIwODMzMjUyIn0.Qb42Z3IbC44O_Q75XFgZIGrr9v34V2W6n83PpRjK4a3lHtvD4RXiu_AuEY_sZBvqeys8ZS4iUOpLNS6lSADFUw','hdhddh1522820833252',NULL,NULL,NULL,NULL,NULL,NULL),(5,'2018-04-06 16:05:35','2018-04-06 16:05:35','abhishek250719@yahoo.in',NULL,NULL,'abhishek',NULL,'anand','c4ed3bffe1fbfed7c4dd230b4dc8f4d1',NULL,NULL,NULL,'1523117135264eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmhpc2hlazE1MjMwMzA3MzUyNjEifQ.4tsgWH65bLQXfh0tCMY1hzMLC6qf-ZOfqTbFG5_XDh-DhMyPJMFRxaqhQg07tIPr97p-_AOPcjoiDabnY77nHQ','abhishek1523030735261',NULL,NULL,NULL,NULL,NULL,NULL),(6,'2018-04-10 05:39:29','2018-04-10 05:39:29','abc@abc.com',NULL,NULL,'test',NULL,'user','aeb90c18d1e2cd899056b3c018a0ffc5',NULL,NULL,NULL,'1523425168447eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MTUyMzMzODc2ODQyNSJ9.rDjz3PORFSKDb1o4QJv32-7Kh1AK248lAq_9JfvUorssuRM7IOJhhIenG6s9q6JGP9wcMGWVVh3d25vMXlxKuQ','test1523338768425',NULL,NULL,NULL,NULL,NULL,NULL),(7,'2018-04-10 14:34:50','2018-04-10 14:34:50','jsingh@test.com',NULL,NULL,'jp',NULL,'singh','5dcda89d9c60493636b313a99fde4e92',NULL,NULL,NULL,'1523457290251eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqcDE1MjMzNzA4OTAyNTAifQ.Q7OKuo6U6w0wtW6ZMYulw9oJLFD3dTpU-h-gDTWb-k_vD5KEzdbkf59Er8LLMUbk7nHAxEfe11eSrPUzfwxlRA','jp1523370890250',NULL,NULL,NULL,NULL,NULL,NULL),(8,'2018-04-10 20:22:50','2018-04-10 20:22:50','xyz@xyz.com',NULL,NULL,'test',NULL,'test','aeb90c18d1e2cd899056b3c018a0ffc5',NULL,NULL,NULL,'1523478169873eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MTUyMzM5MTc2OTg2OCJ9.nQyGT8uFnstOdlsu4rVlyebIDzm9ifuB8i6F4WtTGPJiDKz-VAKqjTAJsz54ms8LrUartHwnxuSQKMKSVZ9qIw','test1523391769868',NULL,NULL,NULL,NULL,NULL,NULL),(9,'2018-04-15 07:43:12','2018-04-15 07:43:12','jas@123.com',NULL,NULL,'jaspreet',NULL,'singh','46dc84813a8901cb1d2c7b001f2a6f9d',NULL,NULL,NULL,'1523864592133eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXNwcmVldDE1MjM3NzgxOTIxMzEifQ.k_aF3dlK_aGZE_7GIlbPUutAH2yLrEdf4-cP08an8TlTjaAgsa3E-HiaCCF65qaOs8VD1zVnyrR6eHsHuIF7sw','jaspreet1523778192131',NULL,NULL,NULL,NULL,NULL,NULL),(10,'2018-04-15 10:25:36','2018-04-15 10:25:36','jas1@123.com',NULL,NULL,'jas',NULL,'singh','46dc84813a8901cb1d2c7b001f2a6f9d',NULL,NULL,NULL,'1523874336410eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXMxNTIzNzg3OTM2NDA4In0.NoS2IM-CKwX0cyvAhJU_rvl6Is1Dc177cVxeCyuDGake-4rOrDgQ3KfcIpNjgknYBkgSp_zMeSXTR8wvm4locg','jas1523787936408',NULL,NULL,NULL,NULL,NULL,NULL),(11,'2018-04-25 14:53:15','2018-04-25 14:53:15','testing@gmail.com',NULL,NULL,'Test',NULL,'Ing','98bfa8ee5ec35887024887ba2b2998c4',NULL,NULL,NULL,'1524754394483eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MTUyNDY2Nzk5NDQ1NCJ9.b6IC8XnhGscCM-vRIFHQjuCQrR8AfCNMADBr73uyU7AjgQ0X7Eym-xYHfcyFpE8-j80mHBNaB7X0PIidLLvKig','test1524667994454',NULL,NULL,NULL,NULL,NULL,NULL),(12,'2018-04-27 12:28:58','2018-04-27 12:28:58','neha.thamman@gmail.com',NULL,NULL,'neha',NULL,'thamman','98bfa8ee5ec35887024887ba2b2998c4',NULL,NULL,NULL,'1524918536744eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZWhhMTUyNDgzMjEzNjcxNyJ9.8IntKSx6brNokTYHD8ppYmLWxSabwEBAGzm_ntZlYOhh_zEGvOg4hVa6mG-r19nNUBoIjdKiLkVXmKQANddxuQ','neha1524832136717',NULL,NULL,NULL,NULL,NULL,NULL),(13,'2018-04-29 16:37:10','2018-04-29 16:37:10','test@wes.com',NULL,NULL,'Jaspreet',NULL,'Singh','5aded3b18140734746bc0bb9aeac0d6e',NULL,NULL,NULL,'1525106228993eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXNwcmVldDE1MjUwMTk4Mjg5NjYifQ.Yl164zFPmNwA_COr_EppcoKkABD_x5O_HpY25ldLWYc8WHVROugb5oCJUNvK_ZpyE4nbgXRogKDwvm6mqciFtA','jaspreet1525019828966',NULL,NULL,NULL,NULL,NULL,NULL),(14,'2018-04-29 17:03:22','2018-04-29 17:03:22','test@123.com',NULL,NULL,'Jaspreet',NULL,'Singh','57070ff0ef02fea1bb0224c6fb34109a',NULL,NULL,NULL,'1525107802149eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXNwcmVldDE1MjUwMjE0MDIxNDkifQ.nJn2LKu3Lx4d3rtQQPySzNCHX9vYFtxoq2fEtxHUFkJPI6OOg_BW38EC51TKCqCeK6rwxJZSKrQyKb-HCATokw','jaspreet1525021402149',NULL,NULL,NULL,NULL,NULL,NULL),(15,'2018-04-30 15:25:50','2018-04-30 15:25:50','aanandabhi097@gmail.com',NULL,NULL,'Abhishek',NULL,'Anand','bc0bff6f11a700d01e4a0f02be581382',NULL,NULL,NULL,'1525188348675eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmhpc2hlazE1MjUxMDE5NDg0ODEifQ.itOwb-EoYxwkWU2uLP1kpE0N0s9YMnXqv0fcMNz-Xr9uGjHTXeghGAXQjlhJydDcyh1JFJTLZqBIp8RR2AAJ3A','abhishek1525101948481',NULL,NULL,NULL,NULL,NULL,NULL),(16,'2018-05-01 05:58:15','2018-05-01 05:58:15','ror@test.com',NULL,NULL,'justin',NULL,'robert','64948ecc601d5f2919ae7f3123a02e82',NULL,NULL,NULL,'1525240694795eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdXN0aW4xNTI1MTU0Mjk0Nzk0In0.7FNs1cpthe8LkWV16KNzqtgFz18hHAwrWmpVR4IsnZDKKz5tn3QUeJ4O-omi_bnRgVTkf77J3SG7aZLk-mpYcg','justin1525154294794',NULL,NULL,NULL,NULL,NULL,NULL),(17,'2018-05-01 11:47:39','2018-05-01 11:47:39','testing2@gmail.com',NULL,NULL,'test',NULL,'ing2','ffd9d9d223a1d8fe8369edd8dc5694d5',NULL,NULL,NULL,'1525261658895eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MTUyNTE3NTI1ODg5NCJ9.uNaQVWoLQCdPLPu6KydK4ddG_Dm80aXP6cEQaP1oKOHjkNcpiZmux8gk5vxMuxijlHXYmNwnU0b8Ou8LSCzfjg','test1525175258894',NULL,NULL,NULL,NULL,NULL,NULL),(18,'2018-05-01 15:27:13','2018-05-01 15:27:13','testing3@gmail.com',NULL,NULL,'test',NULL,'ing3','ffd9d9d223a1d8fe8369edd8dc5694d5',NULL,NULL,NULL,'1525274832027eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MTUyNTE4ODQzMjAwMyJ9.CdlnTID8W2feCRCM2h_gHbEvoInmB97l96pnsWhjZhMHSQuOkJNjMl-j3UITbYc3jE6MDO_kja4I7M3fKNZ40g','test1525188432003',NULL,NULL,NULL,NULL,NULL,NULL),(19,'2018-05-02 04:51:33','2018-05-02 04:51:33','rest@test.com',NULL,NULL,'Amit',NULL,'Shah','9ba8b7241cfd61a099070a808dc39db4',NULL,NULL,NULL,'1525323092624eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTUyNTIzNjY5MjYyMyJ9.E1uxXnyjeUkttphRTQwbYnrXZvjU5zTzbDeONtJzYH5OUU1XD9xTgcWcSJOaAoEG0sYP3RbqZkm4R_YWJ7sh5g','amit1525236692623',NULL,NULL,NULL,NULL,NULL,NULL),(20,'2018-05-02 16:56:55','2018-05-02 16:56:55','abc@gmail.com',NULL,NULL,'Dee',NULL,'Sach','ffd9d9d223a1d8fe8369edd8dc5694d5',NULL,NULL,NULL,'1525366614650eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWUxNTI1MjgwMjE0NjUwIn0.rPSaOAmry4dy7w9H3Bg4IPtPJ4t_gtqS2y-qjzaOqGGqkU6kijwa36AP9D2XhWcIIs4O3QrpCi2ayYATYBzJ5g','dee1525280214650',NULL,NULL,NULL,NULL,NULL,NULL),(21,'2018-05-04 04:31:21','2018-05-04 04:31:21','test@ik.com',NULL,NULL,'mandy',NULL,'tedt','ffd9d9d223a1d8fe8369edd8dc5694d5',NULL,NULL,NULL,'1525494680970eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5keTE1MjU0MDgyODA5NzAifQ.RO3rDHAughKXar6mmy8a_P4JttWZ5V8R6Mln2j-tbu9qOGD6dSwTQBL4D_wOR4T_a4FU_OT_rAlAf3TxZY8snQ','mandy1525408280970',NULL,NULL,NULL,NULL,NULL,NULL),(22,'2018-05-08 01:35:28','2018-05-08 01:35:28','qwerty@test.com',NULL,NULL,'yo',NULL,'yo','398e27f1d2ee547549bbe99b5b9d2e4e',NULL,NULL,NULL,'1525829728431eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5bzE1MjU3NDMzMjg0MzEifQ.PDPaY5it6O-xs_igl8ky4G8pycfAXvJbAFbH8IaINIukbwGnVxwDJ3kLCNtM3uXg1K4DASlHcRmGPJ6J9MHUqw','yo1525743328431',NULL,NULL,NULL,NULL,NULL,NULL),(23,'2018-05-11 18:07:08','2018-05-14 18:11:12','panesar_13@yahoo.com','EAAZAlK8DUJAcBADUUsjwEGs2yuyREdum6zAAKiZAYKwhqFUT0OHgHRi2ZC942WyaeDpvcuB5o34yx4PKbUdjjd8g3WEC77ACVzTGL9Gdjcf5Xg1eGz6O0s70LXfJs4yZAzwpaXto2Ep8eNfq3EmMyte10sHTb3MZBxx4GlGbCN1Oab8GVDFsOXjkJHJGaJGMaVclc23tUEvAQ1GbndwPLwZBZAIKQmabIvfrs5FFUgBbwZDZD','1829913983726399','Mandeep Singh',NULL,NULL,NULL,'https://lookaside.facebook.com/platform/profilepic/?asid=1829913983726399&height=961&ext=1526321227&hash=AeSZiCkP77i_Zmo0',NULL,NULL,'1526407872336eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5kZWVwLnNpbmdoMTUyNjA2MjAyNzY5NyJ9.1LOSjRq7XYJoUSqEHR4I3uLTkLvtHY-wpKHsJOe7_zR493NdcAg9eoXf48B0Q4mGW9C6puH2fkqEz86rAetlnQ','mandeep.singh1526062027697',NULL,NULL,NULL,NULL,NULL,NULL),(24,'2018-05-11 19:14:13','2018-05-11 19:35:20','mandeep.singh@redblink.com',NULL,NULL,'Mandeep Singh',NULL,NULL,NULL,NULL,NULL,NULL,'1526153719867eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYW5kZWVwIFNpbmdoMTUyNjA2NjA1MzQwNCJ9.9OMglJdol9G6L_76jzIFFOmHbi3tIUg04282oirE84KRrzurUuR3N2hBDv1czMRN0qo2N0SPhBeVsPMXbnJpIQ','Mandeep Singh1526066053404',NULL,NULL,NULL,NULL,'eyJhbGciOiJSUzI1NiIsImtpZCI6ImE3NDhlOWY3NjcxNTlmNjY3YTAyMjMzMThkZTBiMjMyOWU1NDQzNjIifQ.eyJhenAiOiIxMDc5Mjc0MzMzODU2LW5qbmVpZnNsNTZ1czVpMHFoODJsOXQxaWpsYjA2NWlsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiMTA3OTI3NDMzMzg1Ni1sanZ1bm40NW9jM3NuZnUydjJ1MG9iYmIzdmExdmk4cy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMTM2Nzg2MDUwMTkxNDY0MDg1MSIsImhkIjoicmVkYmxpbmsuY29tIiwiZW1haWwiOiJtYW5kZWVwLnNpbmdoQHJlZGJsaW5rLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJleHAiOjE1MjYwNjkzNjksImlzcyI6Imh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbSIsImlhdCI6MTUyNjA2NTc2OSwibmFtZSI6Ik1hbmRlZXAgU2luZ2giLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1PYi1hV181SUFYOC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BSWNmZFhEV2ZKVjh0ZklSNy1mc1M4UXBmTDlGdnYxVG5BL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJNYW5kZWVwIiwiZmFtaWx5X25hbWUiOiJTaW5naCJ9.KSGiYnHxtC_rauI4muLozBRt_SM_4KsyIjee7Pk8-hyG3xLe4CArCI6108Rl2Yqd36U1atTFha8hvubrNfX93LRzqsZcSwMTrIDXSju9sgCL08L65X4I7jcBPnjw66_EuH843qPLjcUbyMoTmthgqMO52M11RAqvxEThE4kZFCCYBhmqwV5L2ZEJ6j2zItVrvP7ke3sLwS8706DdVrCrVilawa2Bpr0vjzgR3kBT3N2E_Vsl-0oktiv8zEzNn3Y_NQRWR1-doTGZ7sbtf8oc-m47qI-yzUc-8ohfzLsXw2_O6tn0XUMbzNG8kWTvXqK-vNo-FVCs_ZSC7lvEwjX6zA',NULL),(25,'2018-05-13 11:28:29','2018-05-13 11:28:29','annnn@bsbs.com',NULL,NULL,'vsvsvbsh',NULL,'gzvv','b6a2ce15f319b2546a137f012854ab37',NULL,NULL,NULL,'1526297308859eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2c3ZzdmJzaDE1MjYyMTA5MDg4NDIifQ.eJpU5WPP_4WmgzHG0eyyqcDlj7qIlOKNrDL6mVYPun4I5iqgBk5i8PU1hjsqEOdBPXarKK3dQuka6vfVQOwMpQ','vsvsvbsh1526210908842',NULL,NULL,NULL,NULL,NULL,NULL),(26,'2018-05-14 01:46:01','2018-05-14 01:46:01','harrysingh999@gmail.com','EAAZAlK8DUJAcBADPCzsgxTRJYIsy17ZB56bNfxMnjXPpVrwYocB93aPpt9x6pWXfrosQA7K7xGctzL0Ljbf2UF0YqTyKF3DbAuLwelyn1eSrDDruw0i9FuuYidwJ2ZBPVQ0pVVLHEUJOPjf2iTYd8G1hcQsGq6081YbZCUDuUtrudRKtpZAwATaRLZCZBuTi3BgZBi98IMQj5b1sa95cyBeP1GAfrdZC19QoZD','10215771457777864','Harry Singh',NULL,NULL,NULL,'https://lookaside.facebook.com/platform/profilepic/?asid=10215771457777864&height=961&ext=1526521561&hash=AeTTas485orrA_7E',NULL,NULL,'1526348761293eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXJyeS5zaW5naDE1MjYyNjIzNjEyOTIifQ.tiGXtn8eB6IVONxnT_MRKURDocj44uaCk0mtiKFa2smHDpsT94BVZ33Bv1kgils7ovYc5758GBeY_IPwSs2oBA','harry.singh1526262361292',NULL,NULL,NULL,NULL,NULL,NULL),(27,'2018-05-14 01:46:33','2018-05-14 01:46:33','jaspreet605@gmail.com',NULL,NULL,'jaspreet singh',NULL,NULL,NULL,NULL,NULL,NULL,'1526348793368eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXNwcmVldCBzaW5naDE1MjYyNjIzOTMzNjgifQ.j8gjxKOS6W7cHKwh1FtlEmwlLcY0uo0vipnZJ2kxdgUGgJgW6kophQHKSkmuApASEuiu5mWinJIGWBap5Sq2xA','jaspreet singh1526262393368',NULL,NULL,NULL,NULL,'eyJhbGciOiJSUzI1NiIsImtpZCI6ImE3NDhlOWY3NjcxNTlmNjY3YTAyMjMzMThkZTBiMjMyOWU1NDQzNjIifQ.eyJhenAiOiIxMDc5Mjc0MzMzODU2LW5qbmVpZnNsNTZ1czVpMHFoODJsOXQxaWpsYjA2NWlsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiMTA3OTI3NDMzMzg1Ni1sanZ1bm40NW9jM3NuZnUydjJ1MG9iYmIzdmExdmk4cy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMDYwNDc3NTg5ODczMjMzNDk4NyIsImVtYWlsIjoiamFzcHJlZXQ2MDVAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImV4cCI6MTUyNjI2NTk5MSwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNTI2MjYyMzkxLCJuYW1lIjoiamFzcHJlZXQgc2luZ2giLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1xT29ma2xfN0ZWSS9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BSWNmZFhBR2Y0U3JZaTZvdUg1TzcxdTNPb0pIUktFUG5nL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJqYXNwcmVldCIsImZhbWlseV9uYW1lIjoic2luZ2giLCJsb2NhbGUiOiJlbiJ9.lNa26HiN34QjA2yRsUGkdK89pZI2snU5XkwT1xh4nhrYcSxVHvsG0AnKTYXvI-wlycItOtSwdYTxv5XZZwnrrMcT-DmuRco6e19v7fS5sJVWiSqg53c4Z54tmjWDvZSCb0QUQ7d911ZM0lu4EAOXT4wBRej5bsGfUJreebW1KmvJMZHt0Eh_ALM-vp_MkNH7peo4xTDcdDUUdaU_UOCTf_vjjdiFiW4ofAZuA8SgQycfeAH63-ZxkzzEJBiLjt7BUl9raLMEowxI_TLvLuT3a_25SYb_mBBbA4Mv4Y6ncjV9fnMgKOhfBc5SSGMsl4INA7OAcF_I5_nOyzGbPiPsdQ',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-15 17:04:47
