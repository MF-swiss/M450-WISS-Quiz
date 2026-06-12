SET NAMES utf8mb4;

DROP DATABASE IF EXISTS wiss_quiz;
CREATE DATABASE wiss_quiz CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE wiss_quiz;

DROP USER IF EXISTS 'wiss_quiz'@'%';
CREATE USER 'wiss_quiz'@'%' IDENTIFIED BY 'wiss_quiz';
GRANT ALL PRIVILEGES ON wiss_quiz.* TO 'wiss_quiz'@'%';

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `category` VALUES
(1,'database'),
(2,'java'),
(3,'php'),
(4,'Applikationen testen'),
(5,'Skriptsprache Bash');

CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7jaqbm9p4prg7n91dd1uabrvj` (`category_id`),
  CONSTRAINT `FK7jaqbm9p4prg7n91dd1uabrvj` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `question` VALUES
(1,'Welcher SQL-Befehl dient zum Abfragen von Daten?',1),
(2,'Welcher SQL-Befehl fügt neue Datensätze ein?',1),
(3,'Welcher SQL-Befehl erstellt eine neue Tabelle?',1),
(4,'Welcher SQL-Befehl ändert bestehende Datensätze?',1),
(5,'Welcher SQL-Befehl löscht Datensätze?',1),
(6,'Welcher SQL-Befehl entfernt eine ganze Tabelle?',1),
(7,'Welcher SQL-Befehl sortiert ein Resultat?',1),
(8,'Mit welcher Klausel filtert man Zeilen?',1),
(9,'Welcher Join liefert nur passende Datensätze aus beiden Tabellen?',1),
(10,'Welche Funktion zählt Datensätze?',1),

(11,'Welcher Datentyp eignet sich für Ganzzahlen in Java?',2),
(12,'Welcher Datentyp eignet sich für Kommazahlen in Java?',2),
(13,'Welche Methode startet ein Java-Programm?',2),
(14,'Womit erzeugt man ein Objekt in Java?',2),
(15,'Welches Zeichen beendet in Java eine Anweisung?',2),
(16,'Welches Schlüsselwort verwendet man für Vererbung in Java?',2),
(17,'Welches Schlüsselwort gehört zu einer Bedingung?',2),
(18,'Wie deklariert man ein Array von int?',2),
(19,'Wie lautet der Einstiegspunkt einer Java-Anwendung vollständig?',2),
(20,'Welcher Zugriffsmodifikator erlaubt Zugriff von überall?',2),

(21,'Welches Zeichen beginnt in PHP eine Variable?',3),
(22,'Welches Tag beginnt einen PHP-Block?',3),
(23,'Womit gibt man in PHP Text aus?',3),
(24,'Welches Zeichen beendet in PHP eine Anweisung?',3),
(25,'Wie verbindet man in PHP Strings?',3),
(26,'Welches Schlüsselwort definiert in PHP eine Funktion?',3),
(27,'Welches Superglobal enthält GET-Parameter?',3),
(28,'Welches Superglobal enthält POST-Daten?',3),
(29,'Mit welchem Operator vergleicht man in PHP auf Gleichheit?',3),
(30,'Welcher Datentyp speichert in PHP Wahrheitswerte?',3),

(31,'Welcher Test prüft eine einzelne Methode isoliert?',4),
(32,'Welches Framework wird in eurem Projekt für Unit-Tests verwendet?',4),
(33,'Wozu dient Mockito?',4),
(34,'Was beschreibt die Testpyramide am besten?',4),
(35,'Welche Testart prüft das Zusammenspiel mehrerer Komponenten?',4),
(36,'Welche Testart prüft die Anwendung als Ganzes aus Benutzersicht?',4),
(37,'Was bedeutet Arrange-Act-Assert?',4),
(38,'Warum sind automatisierte Tests bei Regressionen nützlich?',4),
(39,'Was ist ein Mock?',4),
(40,'Was ist ein typischer Grenzwerttest?',4),

(41,'Welches Zeichen kennzeichnet in Bash eine Variable beim Auslesen?',5),
(42,'Welcher Befehl listet Dateien in einem Verzeichnis auf?',5),
(43,'Welcher Befehl wechselt das Verzeichnis?',5),
(44,'Welcher Befehl kopiert Dateien?',5),
(45,'Welcher Befehl verschiebt oder benennt Dateien um?',5),
(46,'Welcher Befehl löscht Dateien?',5),
(47,'Mit welchem Zeichen beginnt in Bash ein Kommentar?',5),
(48,'Welcher Befehl gibt den aktuellen Ordner aus?',5),
(49,'Welcher Befehl erstellt ein neues Verzeichnis?',5),
(50,'Welcher Befehl zeigt den Inhalt einer Textdatei an?',5);

CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `correct` TINYINT(1) NOT NULL,
  `question_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8frr4bcabmmeyyu60qt7iiblo` (`question_id`),
  CONSTRAINT `FK8frr4bcabmmeyyu60qt7iiblo` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `answer` VALUES
(1,'INSERT',0,1),
(2,'SELECT',1,1),
(3,'UPDATE',0,1),
(4,'DELETE',0,1),

(5,'CREATE',0,2),
(6,'DROP',0,2),
(7,'INSERT',1,2),
(8,'SELECT',0,2),

(9,'CREATE TABLE',1,3),
(10,'ALTER TABLE',0,3),
(11,'UPDATE TABLE',0,3),
(12,'BUILD TABLE',0,3),

(13,'SELECT',0,4),
(14,'ORDER BY',0,4),
(15,'UPDATE',1,4),
(16,'COUNT',0,4),

(17,'DROP',0,5),
(18,'DELETE',1,5),
(19,'CLEAR',0,5),
(20,'REMOVE',0,5),

(21,'REMOVE TABLE',0,6),
(22,'DROP TABLE',1,6),
(23,'DELETE',0,6),
(24,'CLEAR TABLE',0,6),

(25,'WHERE',0,7),
(26,'GROUP BY',0,7),
(27,'HAVING',0,7),
(28,'ORDER BY',1,7),

(29,'WHERE',1,8),
(30,'HAVING',0,8),
(31,'JOIN',0,8),
(32,'ORDER BY',0,8),

(33,'LEFT JOIN',0,9),
(34,'INNER JOIN',1,9),
(35,'RIGHT JOIN',0,9),
(36,'OUTER JOIN',0,9),

(37,'AVG()',0,10),
(38,'COUNT()',1,10),
(39,'SUM()',0,10),
(40,'MIN()',0,10),

(41,'String',0,11),
(42,'boolean',0,11),
(43,'int',1,11),
(44,'double',0,11),

(45,'char',0,12),
(46,'double',1,12),
(47,'int',0,12),
(48,'long',0,12),

(49,'run()',0,13),
(50,'main()',1,13),
(51,'start()',0,13),
(52,'init()',0,13),

(53,'class',0,14),
(54,'new',1,14),
(55,'create',0,14),
(56,'object',0,14),

(57,'.',0,15),
(58,';',1,15),
(59,':',0,15),
(60,',',0,15),

(61,'instanceof',0,16),
(62,'implements',0,16),
(63,'extends',1,16),
(64,'inherits',0,16),

(65,'new',0,17),
(66,'if',1,17),
(67,'switch',0,17),
(68,'class',0,17),

(69,'numbers:int[];',0,18),
(70,'int[] zahlen;',1,18),
(71,'array<int> zahlen;',0,18),
(72,'int zahlen[]();',0,18),

(73,'start(String[] args)',0,19),
(74,'main(void)',0,19),
(75,'execute',0,19),
(76,'public static void main(String[] args)',1,19),

(77,'protected',0,20),
(78,'static',0,20),
(79,'private',0,20),
(80,'public',1,20),

(81,'$',1,21),
(82,'#',0,21),
(83,'%',0,21),
(84,'&',0,21),

(85,'<php>',0,22),
(86,'{{php}}',0,22),
(87,'<?php',1,22),
(88,'<script php>',0,22),

(89,'show',0,23),
(90,'echo',1,23),
(91,'write',0,23),
(92,'console.log',0,23),

(93,':',0,24),
(94,'.',0,24),
(95,';',1,24),
(96,',',0,24),

(97,'&',0,25),
(98,'.',1,25),
(99,'+',0,25),
(100,':',0,25),

(101,'define',0,26),
(102,'function',1,26),
(103,'method',0,26),
(104,'func',0,26),

(105,'$_GET',1,27),
(106,'$_POST',0,27),
(107,'$_REQUEST_BODY',0,27),
(108,'$_URL',0,27),

(109,'$_BODY',0,28),
(110,'$_POST',1,28),
(111,'$_FORM',0,28),
(112,'$_GET',0,28),

(113,'=',0,29),
(114,'=>',0,29),
(115,'==',1,29),
(116,'!=',0,29),

(117,'string',0,30),
(118,'array',0,30),
(119,'bool',1,30),
(120,'int',0,30),

(121,'Unit-Test',1,31),
(122,'Integrationstest',0,31),
(123,'Systemtest',0,31),
(124,'Abnahmetest',0,31),

(125,'Docker',0,32),
(126,'JUnit',1,32),
(127,'React',0,32),
(128,'MySQL',0,32),

(129,'Zum Deployen ins Internet',0,33),
(130,'Zum Mocken von Abhängigkeiten',1,33),
(131,'Zum Schreiben von CSS',0,33),
(132,'Zum Starten von Docker',0,33),

(133,'Viele Unit-Tests, wenige End-to-End-Tests',1,34),
(134,'Nur manuelle Tests',0,34),
(135,'Keine Tests',0,34),
(136,'Nur End-to-End-Tests',0,34),

(137,'Code-Review',0,35),
(138,'Usability-Test',0,35),
(139,'Integrationstest',1,35),
(140,'Unit-Test',0,35),

(141,'Linting',0,36),
(142,'Systemtest',1,36),
(143,'Refactoring',0,36),
(144,'Unit-Test',0,36),

(145,'Analysieren-Ändern-Archivieren',0,37),
(146,'Vorbereiten-Ausführen-Prüfen',1,37),
(147,'Abfrage-Antwort-Ausgabe',0,37),
(148,'Anmelden-Aktualisieren-Abmelden',0,37),

(149,'Weil man keine Anforderungen mehr braucht',0,38),
(150,'Weil die App automatisch schneller wird',0,38),
(151,'Weil man Fehler nach Änderungen schnell erkennt',1,38),
(152,'Weil man nie mehr manuell testen muss',0,38),

(153,'Ein Produktionsfehler',0,39),
(154,'Ein Platzhalter für eine Abhängigkeit im Test',1,39),
(155,'Ein echter Datenbankserver',0,39),
(156,'Ein Benutzerkonto für Tests',0,39),

(157,'Nur den Happy Path prüfen',0,40),
(158,'59%, 60%, 61% prüfen',1,40),
(159,'Nur einen Zufallswert prüfen',0,40),
(160,'Nie Randfälle prüfen',0,40),

(161,'@',0,41),
(162,'$',1,41),
(163,'#',0,41),
(164,'&',0,41),

(165,'rm',0,42),
(166,'cd',0,42),
(167,'ls',1,42),
(168,'pwd',0,42),

(169,'mkdir',0,43),
(170,'cd',1,43),
(171,'cp',0,43),
(172,'mv',0,43),

(173,'cat',0,44),
(174,'cp',1,44),
(175,'mv',0,44),
(176,'rm',0,44),

(177,'rm',0,45),
(178,'mv',1,45),
(179,'ls',0,45),
(180,'cp',0,45),

(181,'erase',0,46),
(182,'cat',0,46),
(183,'del',0,46),
(184,'rm',1,46),

(185,'#',1,47),
(186,'//',0,47),
(187,'**',0,47),
(188,'--',0,47),

(189,'whoami',0,48),
(190,'pwd',1,48),
(191,'cd',0,48),
(192,'ls',0,48),

(193,'dircreate',0,49),
(194,'mkdir',1,49),
(195,'mkfile',0,49),
(196,'touchdir',0,49),

(197,'mv',0,50),
(198,'cat',1,50),
(199,'cp',0,50),
(200,'rm',0,50);