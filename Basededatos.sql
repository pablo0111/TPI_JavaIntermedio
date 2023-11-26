CREATE DATABASE  IF NOT EXISTS `tpi_argprog2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tpi_argprog2`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: tpi_argprog2
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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idClienteCUIT` bigint unsigned NOT NULL,
  `RazonSocial` varchar(90) NOT NULL,
  `eMail` varchar(90) DEFAULT NULL,
  `estado` int DEFAULT '1',
  PRIMARY KEY (`idClienteCUIT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (12123123121,'Razon Test','mail@mail.com',0),(20301235155,'Clientazo','client@azo.com',1),(20302221111,'Cliente Mucha plata','mucha@plata.com',1),(55102003001,'CosmeFulanito CORP','cosme@corp.com',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadoincidente`
--

DROP TABLE IF EXISTS `estadoincidente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadoincidente` (
  `idestadoIncidente` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(90) NOT NULL,
  PRIMARY KEY (`idestadoIncidente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadoincidente`
--

LOCK TABLES `estadoincidente` WRITE;
/*!40000 ALTER TABLE `estadoincidente` DISABLE KEYS */;
INSERT INTO `estadoincidente` VALUES (1,'ABIERTO'),(2,'RESUELTO'),(3,'EN ESPERA'),(4,'CANCELADO');
/*!40000 ALTER TABLE `estadoincidente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidente`
--

DROP TABLE IF EXISTS `incidente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidente` (
  `idincidenteNumero` bigint unsigned NOT NULL AUTO_INCREMENT,
  `idClienteCUIT` bigint unsigned DEFAULT NULL,
  `idTecnico` int unsigned DEFAULT NULL,
  `idParent` bigint unsigned DEFAULT '0',
  `idTipoProblema` int unsigned DEFAULT NULL,
  `horasAdic` int DEFAULT '0',
  `fechaCreacion` datetime DEFAULT NULL,
  `descripcionProblema` varchar(255) DEFAULT NULL,
  `descripcionResolucion` varchar(255) DEFAULT '0',
  `idEstado` int unsigned DEFAULT NULL,
  `idServicio` int DEFAULT NULL,
  `fechaResolucion` datetime DEFAULT NULL,
  `duracion` bigint GENERATED ALWAYS AS (timestampdiff(SECOND,`fechaCreacion`,`fechaResolucion`)) VIRTUAL,
  PRIMARY KEY (`idincidenteNumero`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidente`
--

LOCK TABLES `incidente` WRITE;
/*!40000 ALTER TABLE `incidente` DISABLE KEYS */;
INSERT INTO `incidente` (`idincidenteNumero`, `idClienteCUIT`, `idTecnico`, `idParent`, `idTipoProblema`, `horasAdic`, `fechaCreacion`, `descripcionProblema`, `descripcionResolucion`, `idEstado`, `idServicio`, `fechaResolucion`) VALUES (1,55102003001,10,NULL,11,125,'2023-11-22 00:00:00','No le funciona Tango al nuevo, piden re-instalar','Se resolvió reiniciando la pc',2,1,NULL),(2,55102003001,8,NULL,11,NULL,'2023-11-22 00:00:00','No le funciona al nuevo que entro ayer',NULL,1,1,NULL),(3,55102003001,10,NULL,11,NULL,'2023-11-25 10:35:00','El nuevo tampoco puede abrir Oracle',NULL,2,10,'2023-11-25 12:15:00'),(4,55102003001,8,NULL,8,16,'2023-11-25 11:15:00','No le fuciona Tango al equipo de impuestos, 3 personas',NULL,1,1,NULL),(5,55102003001,10,0,11,34,'2023-11-25 12:15:00','No anda no anda no andaaaa',' ',1,1,NULL),(6,20301235155,9,NULL,10,0,'2023-11-26 00:31:50','No baila , se queda sentado',NULL,1,1,NULL),(7,20301235155,9,NULL,11,10,'2023-11-26 00:31:50','No carga el aplicativo -----URGENTE',NULL,1,5,NULL),(8,20302221111,10,NULL,11,0,'2023-11-26 00:41:01','Baila otro tipo de musica',NULL,1,1,NULL),(9,20302221111,10,NULL,11,0,'2023-11-26 00:41:01','Al compañero de al lado tampoco le hace los pasos bien','Se cambió la sintonia de la radio',2,1,'2023-11-26 00:42:59');
/*!40000 ALTER TABLE `incidente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nomina`
--

DROP TABLE IF EXISTS `nomina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nomina` (
  `idnominaLegajo` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(125) NOT NULL,
  `email` varchar(125) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `contactoPreferido` int unsigned NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `rol_id` int unsigned DEFAULT NULL,
  `estado` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idnominaLegajo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nomina`
--

LOCK TABLES `nomina` WRITE;
/*!40000 ALTER TABLE `nomina` DISABLE KEYS */;
INSERT INTO `nomina` VALUES (1,'Juan','juan@mail.com','5554444',1,'juan','1234',3,0),(2,'Pedro','pedro@mail.com','5554545',2,'pedro','1234',2,1),(3,'Romina','romina@mail.com','5556565',2,'romina','1234',5,1),(4,'Patricia','patricia@mail.com','5551234',2,'patricia','1234',5,1),(5,'Eva','eva@mail.com','5557849',2,'eva','1234',4,1),(6,'admin','admin@admin.com','5551616',1,'admin','admin',1,1),(7,'Roberto','roberto@mail.com','5559841',2,'roberto','1234',3,1),(8,'Guillermo','guillermo@mail.com','5554949',3,'guillermo','1234',3,1),(9,'Juana','juana@mail.com','5554848',1,'juana','1234',3,1),(10,'Robertito','robertito@mail.com','5551215',3,'robertito','1234',3,1);
/*!40000 ALTER TABLE `nomina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idroles` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(90) NOT NULL,
  PRIMARY KEY (`idroles`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'RRHH'),(3,'TECNICO'),(4,'OPERADORMDA'),(5,'COMERCIAL');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios_contratados`
--

DROP TABLE IF EXISTS `servicios_contratados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios_contratados` (
  `clienteCUIT_id` bigint unsigned NOT NULL,
  `servicio_especialidad_id` int unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios_contratados`
--

LOCK TABLES `servicios_contratados` WRITE;
/*!40000 ALTER TABLE `servicios_contratados` DISABLE KEYS */;
INSERT INTO `servicios_contratados` VALUES (55102003001,1),(55102003001,11),(55102003001,5),(55102003001,10),(20302221111,1),(20302221111,2),(20302221111,10),(20302221111,11),(20301235155,11),(20301235155,1),(20301235155,5),(20302221111,5);
/*!40000 ALTER TABLE `servicios_contratados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios_especialidades`
--

DROP TABLE IF EXISTS `servicios_especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios_especialidades` (
  `idservicios_especialidades` int unsigned NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`idservicios_especialidades`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios_especialidades`
--

LOCK TABLES `servicios_especialidades` WRITE;
/*!40000 ALTER TABLE `servicios_especialidades` DISABLE KEYS */;
INSERT INTO `servicios_especialidades` VALUES (1,'Tango'),(2,'SalesForce'),(3,'Windows'),(4,'MacOs'),(5,'SAP Enterprice'),(6,'SAP Lite'),(7,'ERP'),(8,'Windows 3.11'),(9,'AWS'),(10,'Oracle'),(11,'NetBeans');
/*!40000 ALTER TABLE `servicios_especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnico_especialidad`
--

DROP TABLE IF EXISTS `tecnico_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnico_especialidad` (
  `legajoID` bigint NOT NULL,
  `servicio_especialidadID` int unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnico_especialidad`
--

LOCK TABLES `tecnico_especialidad` WRITE;
/*!40000 ALTER TABLE `tecnico_especialidad` DISABLE KEYS */;
INSERT INTO `tecnico_especialidad` VALUES (9,4),(9,7),(10,3),(10,4),(10,6),(10,7),(10,10),(10,1),(10,2),(8,1),(8,2),(9,5),(9,1),(9,11),(8,11),(8,5);
/*!40000 ALTER TABLE `tecnico_especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipocontacto`
--

DROP TABLE IF EXISTS `tipocontacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipocontacto` (
  `idtipoContacto` int unsigned NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(90) NOT NULL,
  PRIMARY KEY (`idtipoContacto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipocontacto`
--

LOCK TABLES `tipocontacto` WRITE;
/*!40000 ALTER TABLE `tipocontacto` DISABLE KEYS */;
INSERT INTO `tipocontacto` VALUES (1,'WHATSAPP'),(2,'EMAIL'),(3,'TELEFONO');
/*!40000 ALTER TABLE `tipocontacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoproblema`
--

DROP TABLE IF EXISTS `tipoproblema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoproblema` (
  `idtipoproblema` int unsigned NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(90) NOT NULL,
  `hsRequeridasETR` int unsigned NOT NULL,
  PRIMARY KEY (`idtipoproblema`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoproblema`
--

LOCK TABLES `tipoproblema` WRITE;
/*!40000 ALTER TABLE `tipoproblema` DISABLE KEYS */;
INSERT INTO `tipoproblema` VALUES (1,'Falla Total',25),(2,'Falla parcial 90%',45),(3,'Falla parcial 80%',65),(4,'Falla parcial 70%',85),(5,'Falla parcial 60%',105),(6,'Falla parcial 50%',125),(7,'Falla parcial 40%',145),(8,'Falla parcial 30%',165),(9,'Falla parcial 20%',185),(10,'Falla parcial 10%',205),(11,'Falla puntual',265);
/*!40000 ALTER TABLE `tipoproblema` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-26  1:16:14
