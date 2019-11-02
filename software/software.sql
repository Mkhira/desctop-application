-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 15, 2019 at 04:59 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `software`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `bill_id` int(20) NOT NULL,
  `Bill_owner` varchar(30) NOT NULL,
  `Owner_phone` varchar(20) NOT NULL,
  `total` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`bill_id`, `Bill_owner`, `Owner_phone`, `total`) VALUES
(1, 'khira', '01094580442', '2500.0');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(20) NOT NULL,
  `employee_name` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` varchar(20) NOT NULL,
  `salary` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `employee_name`, `user_name`, `password`, `type`, `salary`, `phone`, `address`) VALUES
(1, 'khira', 'kh', '123', 'manger', '2000', '01094580442', 'masr'),
(2, 'mohamed', 'km', '123', 'employee', '1000', '01094580443', 'ffff');

-- --------------------------------------------------------

--
-- Table structure for table `inventoryes`
--

CREATE TABLE `inventoryes` (
  `product_id` int(20) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `model` varchar(20) NOT NULL,
  `price` varchar(20) NOT NULL,
  `amount` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventoryes`
--

INSERT INTO `inventoryes` (`product_id`, `product_name`, `model`, `price`, `amount`) VALUES
(1, 'kj', '606', '50', '450');

-- --------------------------------------------------------

--
-- Table structure for table `solds`
--

CREATE TABLE `solds` (
  `id` int(20) NOT NULL,
  `bill_id` int(20) NOT NULL,
  `product_id` varchar(20) NOT NULL,
  `price` varchar(20) NOT NULL,
  `amount` varchar(20) NOT NULL,
  `total` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `solds`
--

INSERT INTO `solds` (`id`, `bill_id`, `product_id`, `price`, `amount`, `total`) VALUES
(1, 1, '1', '50', '50', '2500');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `inventoryes`
--
ALTER TABLE `inventoryes`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `solds`
--
ALTER TABLE `solds`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bill_id` (`bill_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `bill_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `inventoryes`
--
ALTER TABLE `inventoryes`
  MODIFY `product_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `solds`
--
ALTER TABLE `solds`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `solds`
--
ALTER TABLE `solds`
  ADD CONSTRAINT `solds_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`bill_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
