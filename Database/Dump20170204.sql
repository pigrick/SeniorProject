-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: seniorproject
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `belike`
--

DROP TABLE IF EXISTS `belike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `beid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_blogentry` (`userid`,`beid`),
  KEY `belike_blogentry_idx` (`beid`),
  CONSTRAINT `belike_blogentry` FOREIGN KEY (`beid`) REFERENCES `blogentry` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `belike_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belike`
--

LOCK TABLES `belike` WRITE;
/*!40000 ALTER TABLE `belike` DISABLE KEYS */;
INSERT INTO `belike` VALUES (7,1,23),(5,1,25),(12,1,26),(11,2,25),(141,2,26),(142,3,26);
/*!40000 ALTER TABLE `belike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogentry`
--

DROP TABLE IF EXISTS `blogentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blogentry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `detail` longtext,
  `userid` int(11) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id_idx` (`userid`),
  CONSTRAINT `user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogentry`
--

LOCK TABLES `blogentry` WRITE;
/*!40000 ALTER TABLE `blogentry` DISABLE KEYS */;
INSERT INTO `blogentry` VALUES (18,'asdsad','asdasdsadasd',2,'2017-01-23 14:20:41'),(19,'asdsad','asdad',2,'2017-01-23 14:22:09'),(21,'asdasda','asdasdasdasd',2,'2017-01-23 15:20:16'),(23,'Up Up and Away!','Running away into the depth of sorrow, crushing through the rainbow of purifier. Cleanse my body and let me free. I soar higher than I able. Fall to the ground and that goes my life.',2,'2017-01-25 14:05:00'),(24,'Albert the swordmaster','He charge into the battlefield, gave and strongest swing, smashing through his enemy shield. Fear is once again release to his nemesis.',2,'2017-01-25 14:05:06'),(25,'Candy crush','I love to eat candy, but candy kills my teeth. I beg god that my teeth is hard as steel, crushing the depth of hell.',2,'2017-01-25 14:05:12'),(26,'Running Love','Walking across the river,\r\nI saw an angel begging for the touch of love.\r\nAs I walk, the angel disappear.\r\nA weird dream I have.',2,'2017-01-25 14:05:30'),(27,'Epic adventure to Space','I went to Space and came back stronger. Too Bad I haven\'t took a shower since then. Thank you.',1,'2017-01-29 21:18:39'),(28,'Technology advancement','You are most powerful being in the world!',1,'2017-01-31 22:31:32'),(29,'how are me','good',6,'2017-02-01 13:42:23'),(30,'Crazy Encounter','Walking through forest of danger alps, I saw creature that devour a whole mountain. I loss myself.',5,'2017-02-03 22:38:42'),(31,'Breathless night','I can\'t fall asleep but only to think about past. Suddenly a shadow pass by my soul. My heart imploded as slow catch my breath. Amazing experience.',4,'2017-02-03 22:42:20');
/*!40000 ALTER TABLE `blogentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` text,
  `userid` int(11) NOT NULL,
  `beid` int(11) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `readed` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id_idx` (`userid`),
  KEY `user_comment_idx` (`userid`),
  KEY `comment_blogentry_idx` (`beid`),
  CONSTRAINT `comment_blogentry` FOREIGN KEY (`beid`) REFERENCES `blogentry` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `comment_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (16,'asdasd',2,18,'2017-01-24 21:26:45',1),(17,'zsdsdf',2,19,'2017-01-24 21:28:40',1),(19,'jfjhgj',2,21,'2017-01-24 21:34:34',1),(25,'asdasd',2,18,'2017-01-24 22:08:59',1),(27,NULL,2,18,'2017-01-25 11:17:23',1),(30,'rokcing\r\n',2,21,'2017-01-25 11:31:49',1),(31,'dsfsdfsdfd',2,19,'2017-01-25 13:49:40',1),(33,'omg lord\r\n',2,25,'2017-01-25 14:29:15',1),(35,'sdasd',2,24,'2017-01-25 14:36:59',1),(37,'riaskfasfdad',1,23,'2017-01-25 14:53:00',1),(39,'This is lovely!',1,26,'2017-01-29 21:41:33',1),(41,'Whats sup?',2,26,'2017-01-30 11:59:18',1),(42,'This is crazily cool!',2,28,'2017-01-31 22:35:57',1),(43,'I like it how things go with ur life.',1,26,'2017-01-31 22:36:29',1),(44,'Not bad!',1,23,'2017-02-01 09:43:34',1),(45,'great\r\n',1,24,'2017-02-01 09:44:21',1),(47,'hey great!',2,27,'2017-02-01 09:45:41',1),(48,'great one',3,25,'2017-02-01 09:50:36',1),(49,'This is incredible.',1,26,'2017-02-01 10:55:47',1),(50,'Amazingly intriguing!',5,23,'2017-02-01 10:58:29',1),(51,'yo, ur blog sucks',2,29,'2017-02-01 13:44:40',1),(52,'sad one there',3,25,'2017-02-03 22:30:24',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentlike`
--

DROP TABLE IF EXISTS `commentlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentlike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `commentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_comment` (`userid`,`commentid`),
  KEY `commentlike_comment_idx` (`commentid`),
  CONSTRAINT `commentlike_comment` FOREIGN KEY (`commentid`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `commentlike_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentlike`
--

LOCK TABLES `commentlike` WRITE;
/*!40000 ALTER TABLE `commentlike` DISABLE KEYS */;
INSERT INTO `commentlike` VALUES (1,1,16),(30,1,37),(31,1,39),(2,2,16),(27,2,17),(20,2,19),(28,2,31),(26,2,35),(40,2,39),(38,2,41),(42,6,51);
/*!40000 ALTER TABLE `commentlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendrequest`
--

DROP TABLE IF EXISTS `friendrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendrequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `askid` int(11) NOT NULL,
  `answerid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `askid_answerid` (`answerid`,`askid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendrequest`
--

LOCK TABLES `friendrequest` WRITE;
/*!40000 ALTER TABLE `friendrequest` DISABLE KEYS */;
INSERT INTO `friendrequest` VALUES (14,4,2);
/*!40000 ALTER TABLE `friendrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid1` varchar(45) NOT NULL,
  `userid2` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid1_userid2` (`userid1`,`userid2`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (9,'1','2'),(17,'2','3'),(26,'2','5'),(27,'2','6');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sendid` int(11) NOT NULL,
  `receiveid` int(11) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `readed` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,2,3,'hi','2017-02-01 11:52:51',1),(2,3,2,'no','2017-02-01 11:52:52',1),(3,2,3,'How are you','2017-02-02 11:14:29',1),(108,3,2,'I am really godd','2017-02-03 21:15:06',1),(109,3,2,'how about you','2017-02-03 21:15:09',1),(110,2,3,'I am very good too','2017-02-03 21:15:14',1),(111,2,3,'I miss your grandpa','2017-02-03 21:15:23',1),(112,2,1,'whats sup','2017-02-03 21:25:46',1),(113,2,1,'sup','2017-02-03 21:26:19',1),(114,2,1,'testing','2017-02-03 21:26:50',1),(115,2,1,'how','2017-02-03 21:26:52',1),(116,1,2,'u there','2017-02-03 21:28:17',1),(123,5,2,'how are you','2017-02-03 21:34:29',1),(124,2,5,'gooood','2017-02-03 21:35:01',1),(125,2,3,'god like','2017-02-03 22:33:06',1),(126,3,2,'where on earth','2017-02-03 22:33:23',1),(127,4,2,'you are a hero','2017-02-03 22:33:38',1),(128,5,2,'I realy like your post','2017-02-03 22:33:53',1),(129,1,2,'whats ur next big movie coming?\'','2017-02-03 22:34:12',1),(130,2,5,'Thank you','2017-02-03 22:34:31',1),(131,2,4,'hi','2017-02-04 10:03:25',1),(132,4,2,'hey','2017-02-04 10:03:38',1),(133,4,2,'a','2017-02-04 10:03:43',1),(134,4,2,'s','2017-02-04 10:03:43',1),(135,4,2,'d','2017-02-04 10:03:43',1),(136,4,2,'f','2017-02-04 10:03:44',1),(137,4,2,'g','2017-02-04 10:03:44',1),(138,4,2,'h','2017-02-04 10:03:44',1),(139,4,2,'qwe','2017-02-04 10:04:22',0),(140,1,2,'asdasd','2017-02-04 10:04:36',0),(141,5,2,'yo','2017-02-04 10:04:48',1),(142,5,2,'asdasd','2017-02-04 10:04:52',1),(143,5,2,'asd','2017-02-04 10:04:53',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` blob NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `picurl` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mike','mike@gmail.com','$31$16$6nii5Fkh_kCHF6byeO65RQSt2xYpasjiKTwnT8RlBiU','2017-01-21 10:26:56','http://www.funkidslive.com/wp-content/uploads/2014/07/cute-tiger-cub-300x232.jpg','I am handsome prince. I will make you my princess.'),(2,'rick','rick@hotmail.com','$31$16$7mp9G352EDpxRpgCEqloVz6mmIUD_4Z0Jkoio8RGzM4','2017-01-21 10:26:56','http://static.boredpanda.com/blog/wp-content/uuuploads/cute-baby-animals/cute-baby-animals-10.jpg','I am interesting creature that will blow your mind everytime we interact.'),(3,'james','james@yahoo.com','$31$16$LfruflWJPMNJsWzphbLDjCzXjsE1DI4u28FkrHznQeg','2017-01-24 23:12:29','http://www.joinselfie.com/wp-content/uploads/2015/05/enhanced-11988-1418422002-17.jpg','I am an animal. I party untill morning and drink until I\'m unsconscious.'),(4,'jacky','jacky@gmail.com','$31$16$LfruflWJPMNJsWzphbLDjCzXjsE1DI4u28FkrHznQeg','2017-01-29 21:13:31','https://s-media-cache-ak0.pinimg.com/originals/ef/25/8a/ef258a45b0ff51fb20373e1c9fc7b644.jpg','You will be surprise how can make you laugh. Don\'t be fooled by scary face.'),(5,'kevin','kevin@gmail.com','$31$16$XXGZbEpkttfp3SCqT5lXXWys_MGe2EPsUZHtD-4wwqo','2017-01-29 21:14:53','http://guelph.ca/wp-content/uploads/rabbit.png','I will go through the depth of the ocean just to touch the turtle. I\'m fearless.'),(6,'God','God@gmail.com','$31$16$Qrs3Ez0Fz7FWmMmpVtKH0tgTTlKrG1qwfTa1bDUsH3o','2017-02-01 13:41:46','https://cdn.psychologytoday.com/sites/default/files/field_blog_entry_images/God_the_Father.jpg','GOD');
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

-- Dump completed on 2017-02-04 10:17:14
