-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 27, 2022 at 03:01 PM
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
  PRIMARY KEY (`current_header`,`company`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `headernames`
--

INSERT INTO `headernames` (`current_header`, `ssot_header`, `company`) VALUES
('BankAccountNumber', 'rAccountNumber', 'FinanceNow'),
('merTransAmount', 'amount', 'PaymentGo'),
('nationality', 'sNationality', 'PaymentGo'),
('payeeAccountNo', 'rAccountNumber', 'PaymentGo'),
('payeeBankName', 'rBank', 'PaymentGo'),
('payeeBirthDate', 'rDOB', 'PaymentGo'),
('payeeBranchName', 'rBranch', 'PaymentGo'),
('payeeCaNo', 'rIDNumber', 'PaymentGo'),
('payeeCaType', 'rIDType', 'PaymentGo'),
('payeeGivenName', 'rFirstName', 'PaymentGo'),
('payeePhone', 'rMobileNumber', 'PaymentGo'),
('payeeSurname', 'rLastName', 'PaymentGo'),
('PaymentMode', 'paymentMode', 'FinanceNow'),
('PayoutCurrency', 'rCurrency', 'FinanceNow'),
('PurposeOfRemittance', 'remitPurpose', 'FinanceNow'),
('ReceiverAddress', 'rAddress', 'FinanceNow'),
('ReceiverCity', 'rCity', 'FinanceNow'),
('ReceiverCountry', 'rCountry', 'FinanceNow'),
('ReceiverFirstName', 'rFirstName', 'FinanceNow'),
('ReceiverIdNumber', 'rIDNumber', 'FinanceNow'),
('ReceiverIdType', 'rIDType', 'FinanceNow'),
('ReceiverLastName', 'rLastName', 'FinanceNow'),
('ReceiverNationality', 'rNationality', 'FinanceNow'),
('recipient_account_number', 'rAccountNumber', 'EverywhereRemit'),
('recipient_country', 'rCountry', 'EverywhereRemit'),
('recipient_currency', 'rCurrency', 'EverywhereRemit'),
('recipient_legal_name_first', 'rFirstName', 'EverywhereRemit'),
('recipient_legal_name_last', 'rLastName', 'EverywhereRemit'),
('recipient_mobile_number', 'rMobileNumber', 'EverywhereRemit'),
('recipient_type', 'rType', 'EverywhereRemit'),
('remitAccountNo', 'sAccountNumber', 'PaymentGo'),
('remitAddress', 'sAddress', 'PaymentGo'),
('remitCaNo', 'sIDNumber', 'PaymentGo'),
('remitCaType', 'sIDType', 'PaymentGo'),
('remitCountryCode', 'sCountry', 'PaymentGo'),
('remitGivenName', 'sFirstName', 'PaymentGo'),
('remitPurpose', 'remitPurpose', 'PaymentGo'),
('remitSurname', 'sLastName', 'PaymentGo'),
('remittance_purpose', 'remitPurpose', 'EverywhereRemit'),
('segment', 'segment', 'EverywhereRemit'),
('SenderAddress', 'sAddress', 'FinanceNow'),
('SenderBeneficiaryRelationship', 'relationship', 'FinanceNow'),
('SenderCity', 'sCity', 'FinanceNow'),
('SenderCountry', 'sCountry', 'FinanceNow'),
('SenderDateOfBirth', 'sDOB', 'FinanceNow'),
('SenderFirstName', 'sFirstName', 'FinanceNow'),
('SenderIdNumber', 'sIDNumber', 'FinanceNow'),
('SenderIdType', 'sIDType', 'FinanceNow'),
('SenderLastName', 'sLastName', 'FinanceNow'),
('SenderNationality', 'sNationality', 'FinanceNow'),
('SenderSourceOfFund', 'sSourceOfFund', 'FinanceNow'),
('SenderState', 'sState', 'FinanceNow'),
('sender_address_city', 'sCity', 'EverywhereRemit'),
('sender_address_country', 'sAddressCountry', 'EverywhereRemit'),
('sender_address_line', 'sAddress', 'EverywhereRemit'),
('sender_country', 'sCountry', 'EverywhereRemit'),
('sender_currency', 'sCurrency', 'EverywhereRemit'),
('sender_date_of_birth', 'sDOB', 'EverywhereRemit'),
('sender_id_country', 'sCountryID', 'EverywhereRemit'),
('sender_id_number', 'sIDNumber', 'EverywhereRemit'),
('sender_id_type', 'sIDType', 'EverywhereRemit'),
('sender_legal_name_first', 'sFirstName', 'EverywhereRemit'),
('sender_legal_name_last', 'sLastName', 'EverywhereRemit'),
('sender_nationality', 'sNationality', 'EverywhereRemit'),
('settleCurrency', 'sCurrency', 'PaymentGo'),
('sourceOfFunds', 'sSourceOfFund', 'PaymentGo'),
('source_of_funds', 'sSourceOfFund', 'EverywhereRemit'),
('source_type', 'sourceType', 'EverywhereRemit'),
('transCurrency', 'rCurrency', 'PaymentGo'),
('units', 'amount', 'EverywhereRemit');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`username`, `filename`, `company`, `transactionstatus`) VALUES
('hyong.2019@scis.smu.edu.sg', 'grock.csv', 'PaymentGo', 'Successful'),
('jaydenGrock', 'EngLieh.csv', 'EverywhereRemit', 'Pending'),
('hyong.2019@scis.smu.edu.sg', 'Luffy.csv', 'FinanceNow', 'Rejected');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `remittancetransaction`
--

INSERT INTO `remittancetransaction` (`dtype`, `rowid`, `r_account_num`, `r_currency`, `r_first_name`, `r_last_name`, `remit_purpose`, `s_address`, `s_country`, `s_first_name`, `sidnumber`, `sidtype`, `s_last_name`, `s_nationality`, `s_source_of_fund`, `amount`, `r_country`, `r_mobile_number`, `r_type`, `s_address_country`, `s_city`, `s_currency`, `sdob`, `sidcountry`, `segment`, `source_type`, `payment_mode`, `r_address`, `r_city`, `ridnumber`, `ridtype`, `r_nationality`, `s_relation`, `s_state`, `r_bank`, `r_branch`, `rdob`, `r_mobile_no`, `s_account_number`) VALUES
('PaymentGo', 1, '345345433', 'SGD', 'Zhawn', 'Gan', 'Washing', 'Blk 3312 Macperson #02-10', 'SGP', 'Kerdad', '333', '1', 'Tan', 'SGP', '99', '9999', NULL, NULL, NULL, NULL, NULL, 'SGD', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '464', '1', NULL, NULL, NULL, 'POSB', 'Pasir Ris', '12/21/1995', '98342658', '3333'),
('PaymentGo', 2, '577766655', 'JPY', 'Zheng', 'Ge Ping', 'Eating', 'Blk 4 Tampines Ave 8 #5-55', 'SGP', 'Stonecold', '444', '2', 'Tan', 'SGP', '2', '888888', NULL, NULL, NULL, NULL, NULL, 'JPY', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '555', '2', NULL, NULL, NULL, 'Stand Charted', 'Seng Kang Mall', '12/21/1995', '69545258', '4323'),
('EverywhereRemit', 3, '19820546855', 'CNY', 'Putien', 'Muhd', '008-03', '788 Lower Changi #99-99', 'SGP', 'Jayden', '123', 'national', 'Ho', 'SGP', '4', '3009', '1010101010', '98862855', '11', 'SGP', 'Singapore', 'EUR', '10/10/2010', 'SGP', 'individual', 'partner', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('EverywhereRemit', 4, '18956571573', 'CNY', 'Ninjiom', 'Tan', '001-01', '494 Johor Barhu', 'SGP', 'Jon', '124', 'national', 'Jones', 'MYS', '3', '4009', '1010101010', '98453688', '11', 'MYS', 'Johor Barhu', 'EUR', '29/01/2000', 'MYS', 'individual', 'partner', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 5, '123454', 'SGD', 'Fred', 'Ong', '12', '11 Eunos #99-99', 'SGP', 'Yang', '33333', '3', 'Grock', 'SGP', '2', NULL, 'EUR', NULL, NULL, NULL, 'Singapore', NULL, '10/10/2010', NULL, NULL, NULL, 'Cheque', '848 Hogo Rd', 'SGP', '123', '3009', 'AUS', '99', 'Singapore', NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 6, '33232453', 'SGD', 'Muhd ', 'Hindra', '12', '19 Woodlands #91-04', 'SGP', 'Yang', '44444', '5', 'Guo', 'MYS', '7', NULL, 'EUR', NULL, NULL, NULL, 'Kup Kup', NULL, '29/01/2000', NULL, NULL, NULL, 'Google Pays', '19 Changi Village #02-20', 'SGP', '143', '4009', 'AUS', '99', 'Singapore', NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 7, '123454', 'SGD', 'Fred', 'Ong', '12', '11 Eunos #99-99', 'SGP', 'Yang', '33333', '3', 'Grock', 'SGP', '2', NULL, 'EUR', NULL, NULL, NULL, 'Singapore', NULL, '10/10/2010', NULL, NULL, NULL, 'Cheque', '848 Hogo Rd', 'SGP', '123', '3009', 'AUS', '99', 'Singapore', NULL, NULL, NULL, NULL, NULL),
('FinanceNow', 8, '33232453', 'SGD', 'Muhd ', 'Hindra', '12', '19 Woodlands #91-04', 'SGP', 'Yang', '44444', '5', 'Guo', 'MYS', '7', NULL, 'EUR', NULL, NULL, NULL, 'Kup Kup', NULL, '29/01/2000', NULL, NULL, NULL, 'Google Pays', '19 Changi Village #02-20', 'SGP', '143', '4009', 'AUS', '99', 'Singapore', NULL, NULL, NULL, NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
