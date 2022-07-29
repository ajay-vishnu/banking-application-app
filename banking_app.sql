-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 29, 2022 at 08:55 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `user_id` int(10) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `account_balance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`user_id`, `account_id`, `account_balance`) VALUES
(26, 2021145914, 39000),
(28, 14092021224937, 9100);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transaction_id` bigint(50) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `transaction_type` varchar(10) NOT NULL,
  `transfer_name` bigint(20) DEFAULT NULL,
  `credit` bigint(20) DEFAULT NULL,
  `debit` bigint(20) DEFAULT NULL,
  `account_balance` bigint(20) NOT NULL,
  `transaction_message` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transaction_id`, `account_id`, `transaction_date`, `transaction_type`, `transfer_name`, `credit`, `debit`, `account_balance`, `transaction_message`) VALUES
(4937114832, 14092021224937, '2021-10-29 06:18:32', 'Withdraw', NULL, NULL, 200, 9100, 'did just now'),
(4937225058, 14092021224937, '2021-09-14 17:20:58', 'Deposit', NULL, 10000, NULL, 10000, '2nd acc test'),
(5914111854, 2021145914, '2021-11-07 05:48:54', 'Deposit', NULL, 200, NULL, 39600, 'ivaga maadhe guru'),
(5914160030, 2021145914, '2021-09-21 10:30:30', 'Withdraw', NULL, NULL, 1000, 39400, 'Hello guru'),
(5914164809, 2021145914, '2021-09-14 11:18:09', 'Deposit', NULL, 50000, NULL, 50400, 'test ui'),
(5914164828, 2021145914, '2021-09-14 11:18:28', 'Withdraw', NULL, NULL, 1000, 49400, 'first withdraw'),
(5914164930, 2021145914, '2021-09-14 11:19:30', 'Withdraw', NULL, NULL, 10000, 39400, 'first withdraw'),
(14092021236242, 14092021224937, '2021-09-14 17:43:05', 'Sent', 2021145914, NULL, 700, 9300, 'test send');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` text NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `name`, `password`) VALUES
(26, 'testmail@gmail.com', 'testmail', 'testmail'),
(28, 'testmail1@gmail.com', 'Testname', 'testmail');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`account_id`),
  ADD KEY `fk_user_id` (`user_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `fk_account_id` (`account_id`),
  ADD KEY `fk_transfer_account_id` (`transfer_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `fk_account_id` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_transfer_account_id` FOREIGN KEY (`transfer_name`) REFERENCES `accounts` (`account_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
