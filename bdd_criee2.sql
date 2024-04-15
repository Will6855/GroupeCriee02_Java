-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 08 nov. 2023 à 19:48
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdd_criee2`
--

-- --------------------------------------------------------

--
-- Structure de la table `bac`
--

DROP TABLE IF EXISTS `bac`;
CREATE TABLE IF NOT EXISTS `bac` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datePeche` date NOT NULL,
  `idBateau` int(11) NOT NULL,
  `idTypeBac` char(1) NOT NULL,
  `idLot` int(11) NOT NULL,
  `idBac` int(11) NOT NULL,
  `poidsBrutBac` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idBateau` (`idBateau`,`datePeche`,`idBac`),
  KEY `idTypeBac` (`idTypeBac`),
  KEY `idLot` (`idLot`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bac`
--

INSERT INTO `bac` (`id`, `datePeche`, `idBateau`, `idTypeBac`, `idLot`, `idBac`, `poidsBrutBac`) VALUES
(1, '2023-11-08', 1, 'F', 5, 1, 12);

-- --------------------------------------------------------

--
-- Structure de la table `bateau`
--

DROP TABLE IF EXISTS `bateau`;
CREATE TABLE IF NOT EXISTS `bateau` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `immatriculation` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bateau`
--

INSERT INTO `bateau` (`id`, `nom`, `immatriculation`) VALUES
(1, 'Altair', 'Ad 895511'),
(2, 'Macareux', 'Ad 584873'),
(3, 'Avel Ar Mor', 'Ad 584930'),
(4, 'Plujadur', 'Ad 627846'),
(5, 'Gwaien', 'Ad 730414'),
(6, 'L Estran', 'Ad 815532'),
(7, 'Le Petit Corse', 'Ad 584826'),
(8, 'Le Vorlen', 'Ad 614221'),
(9, 'Les Copains d Abord', 'Ad 584846'),
(10, 'Tu Pe Du', 'Ad 584871'),
(11, 'Korrigan', 'Ad 895472'),
(12, 'Ar Guevel', 'Ad 895479'),
(13, 'Broceliande', 'Ad 895519'),
(14, 'L Aventurier', 'Ad 584865'),
(15, 'L Oceanide', 'Ad 741312'),
(16, 'L Arche d alliance', 'Ad 584830'),
(17, 'Sirocco', 'Ad 715792'),
(18, 'Ondine', 'Ad 584772'),
(19, 'Chimere', 'Ad 895516');

-- --------------------------------------------------------

--
-- Structure de la table `espece`
--

DROP TABLE IF EXISTS `espece`;
CREATE TABLE IF NOT EXISTS `espece` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `nomScientifique` varchar(50) DEFAULT NULL,
  `nomCourt` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `espece`
--

INSERT INTO `espece` (`id`, `nom`, `nomScientifique`, `nomCourt`) VALUES
(33760, 'Baudroie', 'Lophius Piscatorus', 'BAUDR'),
(33090, 'Bar de Chalut', 'Dicentrarchus Labrax', 'BARCH'),
(33091, 'Bar de Ligne', 'Dicentrarchus Labrax', 'BARLI'),
(32130, 'Lieu Jaune de Ligne', 'Pollachius pollachius', 'LJAUL'),
(42040, 'Araignée de mer casier', 'Maja squinado', 'ARAIS'),
(42041, 'Araignée de mer chalut', 'Maja squinado', 'ARAIL'),
(43010, 'Homard', 'Hammarus gammorus', 'HOMAR'),
(43030, 'Langouste rouge', 'Palinurus elephas', 'LANGR'),
(32140, 'Lieu Noir', 'Lophius Virens', 'LNOI'),
(31020, 'Turbot', 'Psetta maxima', 'TURBO'),
(33480, 'Dorade rose', 'Pagellus bogaraveo', 'DORAD'),
(38150, 'Raie douce', 'Raja Montagui', 'RAIE'),
(33020, 'Congre commun', 'Conger conger', 'CONGR'),
(32020, 'Merlu', 'Merluccius bilinearis', 'MERLU'),
(31030, 'Barbue', 'Scophthalmus rhombus', 'BARBU'),
(31150, 'Plie ou carrelet', 'Pleuronectes Platessa', 'PLIE'),
(32050, 'Cabillaud', 'Gadus Morhua Morhue', 'CABIL'),
(32230, 'Lingue franche', 'Molva Molva', 'LINGU'),
(33080, 'Saint Pierre', 'Zeus Faber', 'STPIE'),
(33110, 'Mérou ou cernier', 'Polyprion Americanus', 'CERNI'),
(33120, 'Mérou noir', 'Epinephelus Guaza', 'MEROU'),
(33410, 'Rouget Barbet', 'Mullus SPP', 'ROUGT'),
(33450, 'Dorade royale chalut', 'Sparus Aurata', 'DORAC'),
(33451, 'Dorade royale ligne', 'Sparus Aurata', 'DORAL'),
(33490, 'Pageot Acarne', 'Pagellus Acarne', 'PAGEO'),
(33500, 'Pageot Commun', 'Pagellus Arythrinus', 'PAGEC'),
(33580, 'Vieille', 'LabrusBergylta', 'VIEIL'),
(33730, 'Grondin gris', 'Eutrigla Gurnadus', 'GRONG'),
(33740, 'Grondin rouge', 'Aspitrigla Cuculus', 'GRONR'),
(33761, 'Baudroie Maigre', 'Lophius Piscicatorius', 'BAUDM'),
(33790, 'Grondin Camard', 'Trigloporus Lastoviza', 'GRONC'),
(33800, 'Grondin Perlon', 'Trigla Lucerna', 'GRONP'),
(34150, 'Mulet', 'Mugil SPP', 'MULET'),
(35040, 'Sardine atlantique', 'Sardina Pilchardus', 'SARDI'),
(37050, 'Maquereau', 'Scomber Scombrus', 'MAQUE'),
(38160, 'Raie Pastenague commune', 'Dasyatis Pastinaca', 'RAIEP'),
(42020, 'Crabe tourteau de casier', 'Cancer Pagurus', 'CRABS'),
(42021, 'Crabe tourteau de chalut', 'Cancer Pagurus', 'CRABL'),
(44010, 'Langoustine', 'Nephrops norvegicus', 'LANGT'),
(57010, 'Seiche', 'Sepia SPP', 'SEICH'),
(57020, 'Calmar', 'Loligo SPP', 'CALAM'),
(57050, 'Poulpe', 'Octopus SPP', 'POULP');

-- --------------------------------------------------------

--
-- Structure de la table `lot`
--

DROP TABLE IF EXISTS `lot`;
CREATE TABLE IF NOT EXISTS `lot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datePeche` date NOT NULL,
  `idBateau` int(11) NOT NULL,
  `prixEnchere` float DEFAULT NULL,
  `dateEnchere` date DEFAULT NULL,
  `HeureDebutEnchere` datetime DEFAULT NULL,
  `prixPlancher` float DEFAULT NULL,
  `prixDepart` float DEFAULT NULL,
  `codeEtat` char(1) DEFAULT NULL,
  `idEspece` int(11) NOT NULL,
  `idTaille` int(11) NOT NULL,
  `idQualite` char(1) NOT NULL,
  `idPresentation` char(3) NOT NULL,
  `idLot` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBateau` (`idBateau`,`datePeche`,`idLot`),
  KEY `idEspece` (`idEspece`),
  KEY `idTaille` (`idTaille`),
  KEY `idQualite` (`idQualite`),
  KEY `idPresentation` (`idPresentation`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lot`
--

INSERT INTO `lot` (`id`, `datePeche`, `idBateau`, `prixEnchere`, `dateEnchere`, `HeureDebutEnchere`, `prixPlancher`, `prixDepart`, `codeEtat`, `idEspece`, `idTaille`, `idQualite`, `idPresentation`, `idLot`) VALUES
(1, '2023-10-27', 1, NULL, NULL, NULL, NULL, NULL, NULL, 33091, 15, 'B', 'ET', 1),
(3, '2023-10-29', 2, NULL, NULL, NULL, NULL, NULL, NULL, 33091, 10, 'E', 'ENT', 1),
(5, '2023-11-08', 1, NULL, NULL, NULL, NULL, NULL, NULL, 33091, 20, 'E', 'ET', 1);

-- --------------------------------------------------------

--
-- Structure de la table `peche`
--

DROP TABLE IF EXISTS `peche`;
CREATE TABLE IF NOT EXISTS `peche` (
  `datePeche` date NOT NULL,
  `idBateau` int(11) NOT NULL,
  PRIMARY KEY (`idBateau`,`datePeche`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `peche`
--

INSERT INTO `peche` (`datePeche`, `idBateau`) VALUES
('2022-07-18', 1),
('2022-07-24', 1),
('2022-07-25', 1),
('2022-07-30', 1),
('2023-10-27', 1),
('2023-11-08', 1),
('2023-10-29', 2),
('2022-07-25', 3),
('2022-07-30', 3),
('2022-08-25', 3),
('2022-07-18', 4),
('2022-08-12', 5),
('2022-07-25', 7),
('2022-07-30', 7),
('2022-07-18', 9),
('2022-08-12', 9),
('2022-07-18', 11),
('2022-07-20', 11),
('2022-07-21', 11),
('2022-07-23', 11),
('2022-07-24', 11),
('2022-07-25', 11),
('2022-07-30', 11),
('2022-08-25', 11);

-- --------------------------------------------------------

--
-- Structure de la table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
CREATE TABLE IF NOT EXISTS `presentation` (
  `id` char(3) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `presentation`
--

INSERT INTO `presentation` (`id`, `libelle`) VALUES
('ET', 'Etété'),
('ENT', 'Entier'),
('VID', 'Vidé');

-- --------------------------------------------------------

--
-- Structure de la table `qualite`
--

DROP TABLE IF EXISTS `qualite`;
CREATE TABLE IF NOT EXISTS `qualite` (
  `id` char(1) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `qualite`
--

INSERT INTO `qualite` (`id`, `libelle`) VALUES
('A', 'glacé'),
('B', 'déclassé'),
('E', 'extra');

-- --------------------------------------------------------

--
-- Structure de la table `taille`
--

DROP TABLE IF EXISTS `taille`;
CREATE TABLE IF NOT EXISTS `taille` (
  `id` int(11) NOT NULL,
  `specification` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `taille`
--

INSERT INTO `taille` (`id`, `specification`) VALUES
(10, 'TAILLE de 10'),
(15, 'TAILLE de 15'),
(20, 'TAILLE de 20'),
(25, 'TAILLE de 25'),
(30, 'TAILLE de 30'),
(35, 'TAILLE de 35'),
(40, 'TAILLE de 40'),
(45, 'TAILLE de 45'),
(50, 'TAILLE de 50'),
(55, 'TAILLE de 55'),
(60, 'TAILLE de 60'),
(65, 'TAILLE de 65'),
(70, 'TAILLE de 70'),
(75, 'TAILLE de 75'),
(80, 'TAILLE de 80'),
(85, 'TAILLE de 85'),
(90, 'TAILLE de 90'),
(95, 'TAILLE de 95');

-- --------------------------------------------------------

--
-- Structure de la table `typebac`
--

DROP TABLE IF EXISTS `typebac`;
CREATE TABLE IF NOT EXISTS `typebac` (
  `id` char(1) NOT NULL,
  `tare` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `typebac`
--

INSERT INTO `typebac` (`id`, `tare`) VALUES
('B', 2.5),
('F', 4);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
