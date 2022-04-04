-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 04, 2022 at 01:18 PM
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
CREATE DATABASE IF NOT EXISTS `remittancedb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
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
  `api_header` varchar(99) NOT NULL,
  `size` varchar(99) NOT NULL,
  `regex` varchar(99) NOT NULL,
  PRIMARY KEY (`current_header`,`company`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `headernames`
--

INSERT INTO `headernames` (`current_header`, `ssot_header`, `company`, `api_header`, `size`, `regex`) VALUES
('Payment Mode', 'paymentMode', 'FinanceNow', 'PaymentMode', '0|50', ''),
('Payout Currency', 'rCurrency', 'FinanceNow', 'PayoutCurrency', '3|3', ''),
('Purpose Of Remittance', 'remitPurpose', 'EverywhereRemit', 'remittance_purpose', '', ''),
('Purpose Of Remittance', 'remitPurpose', 'FinanceNow', 'PurposeOfRemittance', '1|3', ''),
('Purpose Of Remittance', 'remitPurpose', 'PaymentGo', 'remitPurpose', '', ''),
('Receiver Account Number', 'rAccountNumber', 'EverywhereRemit', 'recipient_account_number', '0|128', ''),
('Receiver Account Number', 'rAccountNumber', 'FinanceNow', 'BankAccountNumber', '1|20', ''),
('Receiver Account Number', 'rAccountNumber', 'PaymentGo', 'payeeAccountNo', '', ''),
('Receiver Address', 'rAddress', 'FinanceNow', 'ReceiverAddress', '1|25', ''),
('Receiver City', 'rCity', 'FinanceNow', 'ReceiverCity', '1|25', ''),
('Receiver Country', 'rCountry', 'FinanceNow', 'ReceiverCountry', '1|3', ''),
('Receiver Date of Birth', 'rDOB', 'PaymentGo', 'payeeBirthDate', '', ''),
('Receiver First name', 'rFirstName', 'EverywhereRemit', 'recipient_legal_name_first', '', '^[A-Za-z0-9 .-]+$'),
('Receiver First Name', 'rFirstName', 'FinanceNow', 'ReceiverFirstName', '1|50', ''),
('Receiver First Name', 'rFirstName', 'PaymentGo', 'payeeGivenName', '', ''),
('Receiver ID Number', 'rIDNumber', 'FinanceNow', 'ReceiverIdNumber', '1|18', ''),
('Receiver ID Number', 'rIDNumber', 'PaymentGo', 'payeeCaNo', '', ''),
('Receiver ID Type', 'rIDType', 'FinanceNow', 'ReceiverIdType', '2|2', ''),
('Receiver ID Type', 'rIDType', 'PaymentGo', 'payeeCaType', '', '[12]'),
('Receiver Last Name', 'rLastName', 'EverywhereRemit', 'recipient_legal_name_last', '0|50', '^[A-Za-z0-9 .-]+$'),
('Receiver Last Name', 'rLastName', 'FinanceNow', 'ReceiverLastName', '1|30', ''),
('Receiver Last Name', 'rLastName', 'PaymentGo', 'payeeSurname', '', ''),
('Receiver Nationality', 'rNationality', 'FinanceNow', 'ReceiverNationality', '1|3', ''),
('Receiving Amount', 'amount', 'EverywhereRemit', 'units', '', ''),
('Receiving Amount', 'amount', 'FinanceNow', 'TransferAmount', '', ''),
('Receiving Amount', 'amount', 'PaymentGo', 'merTransAmount', '', ''),
('Recipient Country', 'rCountry', 'EverywhereRemit', 'recipient_country', '3|3', 'CHN'),
('Recipient Currency', 'recipient_currency', 'EverywhereRemit', 'recipient_currency', '', 'CNY'),
('Recipient Type', 'rType', 'EverywhereRemit', 'recipient_type', '', 'bank_account'),
('Sender Address', 'sAddress', 'EverywhereRemit', 'sender_address_line', '', '^[A-Za-z0-9 ,.-]+$'),
('Sender Address', 'sAddress', 'FinanceNow', 'SenderAddress', '0|60', ''),
('Sender Address', 'sAddress', 'PaymentGo', 'remitAddress', '', ''),
('Sender Address Country', 'sAddressCountry', 'EverywhereRemit', 'sender_address_country', '3|3', ''),
('Sender City', 'sCity', 'EverywhereRemit', 'sender_address_city', '', ''),
('Sender City', 'sCity', 'FinanceNow', 'SenderCity', '0|50', ''),
('Sender Country', 'sCountry', 'EverywhereRemit', 'sender_country', '3|3', 'individual'),
('Sender Country', 'sCountry', 'FinanceNow', 'SenderCountry', '0|3', ''),
('Sender Country', 'sCountry', 'PaymentGo', 'remitCountryCode', '', ''),
('Sender Currency', 'sCurrency', 'EverywhereRemit', 'sender_currency', '', 'EUR|SGD|USD'),
('Sender Date of Birth', 'sDOB', 'EverywhereRemit', 'sender_date_of_birth', '0|50', '\\\\d{4}-\\\\d{1,2}-\\\\d{1,2}'),
('Sender Date of Birth', 'sDOB', 'FinanceNow', 'SenderDateOfBirth', '1|11', ''),
('Sender First Name', 'sFirstName', 'EverywhereRemit', 'sender_legal_name_first', '0|50', '^[A-Za-z0-9 .-]+$'),
('Sender First name', 'sFirstName', 'FinanceNow', 'SenderFirstName', '1|50', ''),
('Sender First name', 'sFirstName', 'PaymentGo', 'remitGivenName', '', ''),
('Sender ID Country', 'sCountryID', 'EverywhereRemit', 'sender_id_country', '3|3', ''),
('Sender ID Number', 'sIDNumber', 'EverywhereRemit', 'sender_id_number', '', ''),
('Sender ID Number', 'sIDNumber', 'FinanceNow', 'SenderIdNumber', '1|13', ''),
('Sender ID Number', 'sIDNumber', 'PaymentGo', 'remitCaNo', '', ''),
('Sender ID Type', 'sIDType', 'EverywhereRemit', 'sender_id_type', '', 'national|passport'),
('Sender ID Type', 'sIDType', 'FinanceNow', 'SenderIdType', '2|2', ''),
('Sender ID Type', 'sIDType', 'PaymentGo', 'remitCaType', '', '[12]'),
('Sender Last name', 'sLastName', 'EverywhereRemit', 'sender_legal_name_last', '0|50', '^[A-Za-z0-9 .-]+$'),
('Sender Last name', 'sLastName', 'FinanceNow', 'SenderLastName', '1|50', ''),
('Sender Last name', 'sLastName', 'PaymentGo', 'sender_legal_name_last', '', ''),
('Sender Nationality', 'sNationality', 'EverywhereRemit', 'sender_nationality', '3|3', ''),
('Sender Nationality', 'sNationality', 'FinanceNow', 'SenderNationality', '1|3', ''),
('Sender Nationality', 'sNationality', 'PaymentGo', 'nationality', '', ''),
('Sender Purpose of Remittance', 'remitPurpose', 'EverywhereRemit', 'remittance_purpose', '', ''),
('Sender Purpose of Remittance', 'remitPurpose', 'FinanceNow', 'PurposeOfRemittance', '1|3', ''),
('Sender Purpose of Remittance', 'remitPurpose', 'PaymentGo', 'remitPurpose', '', ''),
('Sender Relationship', 'relationship', 'FinanceNow', 'SenderBeneficiaryRelationship', '1|10', ''),
('Sender Source of Fund', 'sSourceOfFund', 'EverywhereRemit', 'source_of_funds', '', ''),
('Sender Source of Fund', 'sSourceOfFund', 'FinanceNow', 'SenderSourceOfFund', '1|10', ''),
('Sender Source of Fund', 'sSourceOfFund', 'PaymentGo', 'sourceOfFunds', '', ''),
('Sender State', 'sState', 'FinanceNow', 'SenderState', '0|20', ''),
('Source Type', 'sourceType', 'EverywhereRemit', 'source_type', '', 'partner');

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
  `s_source_of_fund` varchar(255) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `r_country` varchar(50) DEFAULT NULL,
  `r_mobile_number` varchar(15) DEFAULT NULL,
  `r_type` varchar(2) DEFAULT NULL,
  `s_address_country` varchar(50) DEFAULT NULL,
  `s_city` varchar(50) DEFAULT NULL,
  `s_currency` varchar(255) DEFAULT NULL,
  `sdob` varchar(10) DEFAULT NULL,
  `sidcountry` varchar(20) DEFAULT NULL,
  `segment` varchar(255) DEFAULT NULL,
  `source_type` varchar(30) DEFAULT NULL,
  `payment_mode` varchar(255) DEFAULT NULL,
  `r_address` varchar(255) DEFAULT NULL,
  `r_city` varchar(255) DEFAULT NULL,
  `ridnumber` varchar(255) DEFAULT NULL,
  `ridtype` varchar(255) DEFAULT NULL,
  `r_nationality` varchar(255) DEFAULT NULL,
  `s_relation` varchar(255) DEFAULT NULL,
  `s_state` varchar(255) DEFAULT NULL,
  `r_bank` varchar(255) DEFAULT NULL,
  `r_branch` varchar(255) DEFAULT NULL,
  `rdob` varchar(255) DEFAULT NULL,
  `r_mobile_no` varchar(255) DEFAULT NULL,
  `s_account_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rowid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `remittancetransaction`
--

INSERT INTO `remittancetransaction` (`dtype`, `rowid`, `r_account_num`, `r_currency`, `r_first_name`, `r_last_name`, `remit_purpose`, `s_address`, `s_country`, `s_first_name`, `sidnumber`, `sidtype`, `s_last_name`, `s_nationality`, `s_source_of_fund`, `amount`, `r_country`, `r_mobile_number`, `r_type`, `s_address_country`, `s_city`, `s_currency`, `sdob`, `sidcountry`, `segment`, `source_type`, `payment_mode`, `r_address`, `r_city`, `ridnumber`, `ridtype`, `r_nationality`, `s_relation`, `s_state`, `r_bank`, `r_branch`, `rdob`, `r_mobile_no`, `s_account_number`) VALUES
('FinanceNow', 1, '622203000000', NULL, 'Max', 'Chua', '1', '4 Sago Lane Singapore', 'SGP', 'XIAO MING', 'S9575000J', '2', 'PENG', 'SGP', '1', '1000', NULL, NULL, NULL, NULL, 'Singapore', NULL, '1995-08-16', NULL, NULL, NULL, NULL, 'Liaoning Province', 'BEIJING', '231236199995056X', '02', 'CHN', '2', NULL, NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 2, '622203000001', NULL, 'John', 'Tan', '99', 'BLK 204B COMPASSVALE DR', 'SGP', 'ANG KOK LIONG', 'S7607000O', '2', 'ROBIN', 'SGP', '1', '200', NULL, NULL, NULL, NULL, 'Singapore', NULL, '1976-02-12', NULL, NULL, NULL, NULL, '?????????????', 'NA', '3505211999998000', '02', 'CHN', '99', NULL, NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 3, '622203000002', NULL, 'Alan', 'Lim', '99', 'BLK 203 SERANGOON CENTRAL', 'SGP', 'GEK LENG', 'S1294000H', '2', 'SIM', 'SGP', '1', '300', NULL, NULL, NULL, NULL, 'Singapore', NULL, '1958-09-22', NULL, NULL, NULL, NULL, '??????????????', 'NA', '4129221974050000', '02', 'CHN', '99', NULL, NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 4, '622203000005', NULL, 'Ximu', 'Sima', '99', 'BLK500 SERANGOON NORTH AVE4', 'SGP', 'HUAY SHING', 'S0180000B', '2', 'SIA', 'SGP', '1', '1800', NULL, NULL, NULL, NULL, 'Singapore', NULL, '1953-04-03', NULL, NULL, NULL, NULL, '??????????????', 'NA', '220421198912065000', '02', 'CHN', '99', NULL, NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 5, '622203000008', NULL, 'Yang', 'Guo', '99', '180ORCHARDROAD', 'SGP', 'QI CHAO', 'G2840000P', '2', 'XIE', 'SGP', '1', '1000', NULL, NULL, NULL, NULL, 'Singapore', NULL, '1990-02-02', NULL, NULL, NULL, NULL, '????????????????', 'NA', '410901199208031000', '02', 'CHN', '99', NULL, NULL, NULL, NULL, NULL, NULL),
('PaymentGo', 6, '622203000003', NULL, 'Aaron', 'Ong', '99', 'BLK 38 KEPPEL BAY DRIVE ', 'SGP', 'HLA SHWE', 'S1988000H', '2', 'TOH', 'SGP', '1', '9000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '44512219761130000', '02', NULL, NULL, NULL, NULL, NULL, '1976-11-30', NULL, NULL),
('PaymentGo', 7, '622203000006', NULL, 'Siqu', 'Li', '99', '7 7 WEST COAST WALK #24-14 SPORE ', 'SGP', 'KHUANG CHAI VINCENCE', 'S1490000C', '2', 'CHAN', 'SGP', '1', '8888', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '211021199108167000', '02', NULL, NULL, NULL, NULL, NULL, '1991-08-16', NULL, NULL),
('PaymentGo', 8, '622203000009', NULL, 'Long', 'Nv', '6', '697 HOUGANG ST61 HOUGANG SPRING  ', 'SGP', 'SENG LEONG', '53310000B', '2', 'TOH', 'SGP', '1', '9000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '441881195907111000', '02', NULL, NULL, NULL, NULL, NULL, '1959-07-11', NULL, NULL),
('PaymentGo', 9, '6222030000010', NULL, 'Huo', 'Chen', '99', 'APT BLK 34 LORONG 5 TOA PAYOH', 'SGP', 'CHOON HUA', 'S6920000D', '2', 'ONG', 'SGP', '1', '300000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '210219196112079000', '02', NULL, NULL, NULL, NULL, NULL, '1961-12-07', NULL, NULL),
('PaymentGo', 10, '622203000011', NULL, 'De', 'Lin', '99', 'BLK 656B JURONG WEST STREET 61 ', 'SGP', 'PEI WAH', 'S1100000C', '2', 'PERLIN THOR', 'SGP', '1', '600000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '211481198110276000', '02', NULL, NULL, NULL, NULL, NULL, '1981-10-27', NULL, NULL),
('EverywhereRemit', 11, '622203000004', NULL, 'Liang', 'Wang', '99', '319 UPPER EAST COAST RD SINGAPORE ', 'SGP', 'XIA LIN', 'G5110000M', '2', 'ZHAO', 'SGP', '1', '5000', NULL, NULL, NULL, NULL, 'Singapore', NULL, '1987-05-24', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('EverywhereRemit', 12, '622203000007', NULL, 'Zheng', 'Lin', '99', 'BLK 1C CANTONMENT RD ', 'SGP', 'GENG MUN VINCENT', 'S7110000B', '2', 'LOH', 'SGP', '1', '6000', NULL, NULL, NULL, NULL, 'Singapore', NULL, '1971-03-26', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(99) NOT NULL,
  `filename` varchar(99) NOT NULL,
  `company` varchar(99) NOT NULL,
  `transactionstatus` varchar(255) NOT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transactionid`, `username`, `filename`, `company`, `transactionstatus`) VALUES
(1, 'hyong.2019@scis.smu.edu.sg', 'grock.csv', 'PaymentGo', 'Transaction Successful'),
(2, 'znchua.2019@smu.edu.sg', 'EngLieh.csv', 'EverywhereRemit', 'Transaction Pending AML'),
(3, 'hyong.2019@scis.smu.edu.sg', 'Luffy.csv', 'FinanceNow', 'Transaction Rejected'),
(4, 'hyong.2019@scis.smu.edu.sg', 'remit.csv', 'FinanceNow', 'Transaction Pending Compliance Checks');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
