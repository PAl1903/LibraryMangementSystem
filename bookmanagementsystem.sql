-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2023 at 08:25 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `book_detail`
--

CREATE TABLE `book_detail` (
  `isbn` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `authorname` varchar(45) NOT NULL,
  `status` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book_detail`
--

INSERT INTO `book_detail` (`isbn`, `name`, `authorname`, `status`) VALUES
(123, 'Advance Java', 'Linga', 1),
(124, 'Operating System', 'Andrew', 1),
(235, 'Computer Networks', 'Tanenbaum', 1),
(267, 'Think and Grow Rich', 'Napoleon Hill', 1);

-- --------------------------------------------------------

--
-- Table structure for table `issuebook`
--

CREATE TABLE `issuebook` (
  `id` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `issuedate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `login_detail`
--

CREATE TABLE `login_detail` (
  `id` int(11) NOT NULL,
  `emailid` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_detail`
--

INSERT INTO `login_detail` (`id`, `emailid`, `password`, `role`) VALUES
(1, 'pal@gmail.com', 'pal', 0),
(2, 'admin@library.com', 'admin', 1),
(3, 'pal@solarteam.com.au', 'pal', 0),
(4, 'pal1@gmail.com', 'qwe', 0),
(5, 'temp1@gmail.com', '123', 0),
(6, 'temp2@gmail.com', '123', 0),
(7, 'temp3@gmail.com', '123', 0),
(8, 'temp11@gmail.com', '12', 0),
(15, 'harshad@gmail.com', '123', 0),
(16, 'test@gmail.com', '123', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book_detail`
--
ALTER TABLE `book_detail`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `issuebook`
--
ALTER TABLE `issuebook`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn_UNIQUE` (`isbn`);

--
-- Indexes for table `login_detail`
--
ALTER TABLE `login_detail`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `issuebook`
--
ALTER TABLE `issuebook`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `login_detail`
--
ALTER TABLE `login_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `issuebook`
--
ALTER TABLE `issuebook`
  ADD CONSTRAINT `issuebook_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book_detail` (`isbn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
