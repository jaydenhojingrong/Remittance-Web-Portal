-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 18, 2022 at 02:45 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `remittancedb`
--
CREATE DATABASE IF NOT EXISTS `remittancedb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `remittancedb`;

-- --------------------------------------------------------

--
-- Table structure for table `headernames`
--

DROP TABLE IF EXISTS `headernames`;
CREATE TABLE IF NOT EXISTS `headernames` (
  `current_header` varchar(99) NOT NULL,
  `ssot_header` varchar(99) NOT NULL,
  `company` varchar(99) NOT NULL,
  `current_headerzzz` varchar(255) NOT NULL,
  `rowid` int(11) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `r_country` varchar(255) DEFAULT NULL,
  `r_mobile` varchar(255) DEFAULT NULL,
  `r_type` varchar(255) DEFAULT NULL,
  `s_city` varchar(255) DEFAULT NULL,
  `s_country` varchar(255) NOT NULL,
  `s_country_address` varchar(255) DEFAULT NULL,
  `s_currency` varchar(255) DEFAULT NULL,
  `sdob` varchar(255) DEFAULT NULL,
  `sidcountry` varchar(255) DEFAULT NULL,
  `segment` varchar(255) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`current_header`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `headernames`
--

INSERT INTO `headernames` (`current_header`, `ssot_header`, `company`, `current_headerzzz`, `rowid`, `amount`, `r_country`, `r_mobile`, `r_type`, `s_city`, `s_country`, `s_country_address`, `s_currency`, `sdob`, `sidcountry`, `segment`, `source_type`) VALUES
('Address', 'sAddress', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Address City', 'sCity', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary Account Number', 'rAccountNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary Bank', 'rBank', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary Branch', 'rBranch', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary Date of Birth', 'rDOB', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary First Name', 'rFirstName', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary ID Number', 'rIDNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary ID Type', 'rIDType', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary Last Name', 'rLastName', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary Mobile Number', 'rMobileNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Beneficiary Send Currency', 'sCurrency', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Country', 'sCountry', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Country of Address', 'sAddressCountry', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Date of Birth', 'sDOB', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Destination Amount', 'rCurrency', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Ewallet ID', 'rAccountNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('First Name', 'sFirstName', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('ID Number', 'sIDNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('ID Type', 'sIDType', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Last Name', 'sLastName', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Mobile Number', 'rMobileNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Nationality', 'sNationality', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Payment Mode', 'paymentMode', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Purpose Of Remittance', 'remitPurpose', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver account number', 'rAccountNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver Address', 'rAddress', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver City', 'rCity', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver Country', 'rCountry', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver First Name', 'rFirstName', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver ID Number', 'rIDNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver ID Type', 'rIDType', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver Last Name', 'rLastName', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Receiver Nationality', 'rNationality', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('recipient_country', 'rCountry', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('recipient_currency', 'rCurrency', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('recipient_type', 'rType', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Remittance Purpose', 'remitPurpose', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('sCountryID', 'ID Country of Issue', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('segment', 'segment', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender Account Number', 'sAccountNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender Address', 'sAddress', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender City', 'sCity', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender DOB', 'sDOB', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender First Name', 'sFirstName', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender ID Number', 'sIDNumber', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender ID Type', 'sIDType', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender Nationality', 'sNationality', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender Relation', 'relationship', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender Residence Address', 'sAddress', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender source of fund', 'sSourceOfFund', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Sender State/Province', 'sState', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('sender_currency', 'sCurrency', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Source of Fund', 'sSourceOfFund', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('Source of Funds', 'sSourceOfFund', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
('source_type', 'sourceType', '', '', 0, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `remittancetransaction`
--

DROP TABLE IF EXISTS `remittancetransaction`;
CREATE TABLE IF NOT EXISTS `remittancetransaction` (
  `dtype` varchar(31) NOT NULL,
  `rowid` int(11) NOT NULL AUTO_INCREMENT,
  `r_account_num` varchar(255) DEFAULT NULL,
  `r_currency` varchar(255) DEFAULT NULL,
  `r_first_name` varchar(255) DEFAULT NULL,
  `r_last_name` varchar(255) DEFAULT NULL,
  `remit_purpose` varchar(255) DEFAULT NULL,
  `s_address` varchar(255) DEFAULT NULL,
  `s_country` varchar(255) DEFAULT NULL,
  `s_first_name` varchar(255) DEFAULT NULL,
  `sidnumber` varchar(255) DEFAULT NULL,
  `sidtype` varchar(255) DEFAULT NULL,
  `s_last_name` varchar(255) DEFAULT NULL,
  `s_nationality` varchar(255) DEFAULT NULL,
  `s_source_of_funds` varchar(255) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `r_country` varchar(255) DEFAULT NULL,
  `r_mobile` varchar(255) DEFAULT NULL,
  `r_type` varchar(255) DEFAULT NULL,
  `s_city` varchar(255) DEFAULT NULL,
  `s_country_address` varchar(255) DEFAULT NULL,
  `s_currency` varchar(255) DEFAULT NULL,
  `sdob` varchar(255) DEFAULT NULL,
  `sidcountry` varchar(255) DEFAULT NULL,
  `segment` varchar(255) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rowid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `remittancetransaction`
--

INSERT INTO `remittancetransaction` (`dtype`, `rowid`, `r_account_num`, `r_currency`, `r_first_name`, `r_last_name`, `remit_purpose`, `s_address`, `s_country`, `s_first_name`, `sidnumber`, `sidtype`, `s_last_name`, `s_nationality`, `s_source_of_funds`, `amount`, `r_country`, `r_mobile`, `r_type`, `s_city`, `s_country_address`, `s_currency`, `sdob`, `sidcountry`, `segment`, `source_type`) VALUES
('EverywhereRemit', 1, '19820546855', 'CNY', 'Putien', 'Muhd', '008-03', '788 Lower Changi #99-99', 'SGP', 'Jayden', '123', 'national', 'Ho', 'SGP', '4', '3009', 'CHN', '98862855', 'bank_account', 'Singapore', 'SGP', 'EUR', '10/10/2010', 'SGP', 'individual', 'partner'),
('EverywhereRemit', 2, '18956571573', 'CNY', 'Ninjiom', 'Tan', '001-01', '494 Johor Barhu', 'SGP', 'Jon', '124', 'national', 'Jones', 'MYS', '3', '4009', 'CHN', '98453688', 'bank_account', 'Johor Barhu', 'MYS', 'EUR', '29/01/2000', 'MYS', 'individual', 'partner');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
