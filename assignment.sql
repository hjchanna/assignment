-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.55-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for assignment
DROP DATABASE IF EXISTS `assignment`;
CREATE DATABASE IF NOT EXISTS `assignment` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `assignment`;


-- Dumping structure for table assignment.item
DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `code` varchar(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  `description` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(100) NOT NULL,
  `supplier` int(11) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_item_user1_idx` (`supplier`),
  CONSTRAINT `fk_item_user1` FOREIGN KEY (`supplier`) REFERENCES `user` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table assignment.item: ~8 rows (approximately)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
REPLACE INTO `item` (`code`, `name`, `description`, `price`, `image`, `supplier`) VALUES
	('001', 'Wireless Mouse', '2.4GHz 6D 1600DPI USB Wireless Optical Gaming Mouse Mice', 3.78, 'http://i.ebayimg.com/images/g/8-kAAOSw3ydV1ZxW/s-l500.jpg', 1),
	('002', 'Wireless Keyboard', '2.4G Optical Wireless Keyboard and Mouse USB Receiver Kit', 11.99, 'http://i.ebayimg.com/images/g/mRAAAOxyLN9Sp7PJ/s-l500.jpg', 1),
	('003', 'Wireless Headphones', 'Hurricane H Bluetooth 4.1 Wireless Stereo Headphones Headset', 33.5, 'http://i.ebayimg.com/images/g/LdoAAOSwfcVT~xHz/s-l500.jpg', 1),
	('004', 'Audiovox Accessories ', 'Audiovox Accessories 2 Usb 3 Outlet Charger PCHSTAT2R', 19.54, 'http://i.ebayimg.com/images/g/HjYAAOSw-kdXxDOB/s-l500.jpg', 1),
	('005', 'Cenovo Mini PC ', 'Cenovo Mini PC Intel X5-Z8300 Quad Core Windows 10 4GB RAM 64GB', 148.72, 'http://i.ebayimg.com/images/g/VvIAAOSwi4lXPaEd/s-l500.jpg', 1),
	('006', 'Home LED Projector', 'Mini 1080P HD Multimedia Home LED Projector Cinema Theater AV TV VGA', 68, 'http://i.ebayimg.com/images/g/fv8AAOSwpDdVcUmi/s-l500.jpg', 1),
	('007', 'Universal Remote', 'Universal Remote Control Controller With Learn Function For VCR DVD', 8.63, 'http://i.ebayimg.com/images/g/5bcAAOxyTMdTMje7/s-l500.jpg', 1),
	('008', 'Airplane Bluetooth', 'PowerUp 3.0 Smartphone Controlled Paper Airplane Bluetooth SMART iOS', 49.89, 'http://i.ebayimg.com/images/g/kw8AAOSwx-9WzfHe/s-l500.jpg', 1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


-- Dumping structure for table assignment.notification
DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `place_order` int(11) NOT NULL,
  `confirm_order` int(11) NOT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_notification_user1_idx` (`user`),
  CONSTRAINT `fk_notification_user1` FOREIGN KEY (`user`) REFERENCES `user` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table assignment.notification: ~0 rows (approximately)
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
REPLACE INTO `notification` (`index_no`, `user`, `place_order`, `confirm_order`) VALUES
	(1, 2, 0, 3);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;


-- Dumping structure for table assignment.order
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(10) NOT NULL,
  `quantity` int(11) NOT NULL,
  `customer` int(11) NOT NULL,
  `supplier` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_order_user_idx` (`customer`),
  KEY `fk_order_item1_idx` (`item`),
  KEY `fk_order_user1_idx` (`supplier`),
  CONSTRAINT `fk_order_item1` FOREIGN KEY (`item`) REFERENCES `item` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`customer`) REFERENCES `user` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`supplier`) REFERENCES `user` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- Dumping data for table assignment.order: ~90 rows (approximately)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
REPLACE INTO `order` (`index_no`, `item`, `quantity`, `customer`, `supplier`, `status`) VALUES
	(1, '003', 1, 2, 1, 'PENDING'),
	(2, '002', 1, 2, 1, 'PENDING'),
	(3, '001', 1, 2, 1, 'PENDING'),
	(4, '003', 1, 2, 1, 'PENDING'),
	(5, '002', 1, 2, 1, 'PENDING'),
	(6, '003', 1, 2, 1, 'PENDING'),
	(7, '002', 1, 2, 1, 'PENDING'),
	(8, '003', 1, 2, 1, 'PENDING'),
	(9, '002', 1, 2, 1, 'PENDING'),
	(10, '001', 1, 2, 1, 'PENDING'),
	(11, '003', 1, 2, 1, 'CONFIRM'),
	(12, '002', 1, 2, 1, 'PENDING'),
	(13, '003', 1, 2, 1, 'PENDING'),
	(14, '002', 1, 2, 1, 'PENDING'),
	(15, '003', 1, 2, 1, 'PENDING'),
	(16, '002', 1, 2, 1, 'PENDING'),
	(17, '001', 1, 2, 1, 'PENDING'),
	(18, '008', 1, 2, 1, 'PENDING'),
	(19, '008', 1, 2, 1, 'PENDING'),
	(20, '007', 1, 2, 1, 'PENDING'),
	(21, '008', 5, 2, 1, 'PENDING'),
	(22, '004', 1, 2, 1, 'PENDING'),
	(23, '003', 1, 2, 1, 'PENDING'),
	(24, '003', 1, 2, 1, 'PENDING'),
	(25, '004', 1, 2, 1, 'PENDING'),
	(26, '002', 1, 2, 1, 'PENDING'),
	(27, '008', 1, 2, 1, 'PENDING'),
	(28, '007', 1, 2, 1, 'PENDING'),
	(29, '006', 1, 2, 1, 'PENDING'),
	(30, '002', 1, 2, 1, 'PENDING'),
	(31, '001', 1, 2, 1, 'PENDING'),
	(32, '004', 1, 2, 1, 'PENDING'),
	(33, '003', 1, 2, 1, 'PENDING'),
	(34, '007', 1, 2, 1, 'PENDING'),
	(35, '004', 3, 2, 1, 'PENDING'),
	(36, '008', 1, 2, 1, 'PENDING'),
	(37, '007', 1, 2, 1, 'PENDING'),
	(38, '002', 1, 2, 1, 'PENDING'),
	(39, '003', 1, 2, 1, 'PENDING'),
	(40, '002', 1, 2, 1, 'PENDING'),
	(41, '003', 1, 2, 1, 'PENDING'),
	(42, '004', 1, 2, 1, 'PENDING'),
	(43, '003', 1, 2, 1, 'PENDING'),
	(44, '002', 1, 2, 1, 'PENDING'),
	(45, '003', 1, 2, 1, 'PENDING'),
	(46, '004', 1, 2, 1, 'PENDING'),
	(47, '003', 1, 2, 1, 'PENDING'),
	(48, '004', 1, 2, 1, 'PENDING'),
	(49, '002', 1, 2, 1, 'PENDING'),
	(50, '003', 1, 2, 1, 'PENDING'),
	(51, '003', 1, 2, 1, 'PENDING'),
	(52, '004', 1, 2, 1, 'PENDING'),
	(53, '003', 1, 2, 1, 'PENDING'),
	(54, '004', 1, 2, 1, 'PENDING'),
	(55, '003', 1, 2, 1, 'PENDING'),
	(56, '004', 1, 2, 1, 'PENDING'),
	(57, '003', 1, 2, 1, 'PENDING'),
	(58, '004', 1, 2, 1, 'PENDING'),
	(59, '002', 1, 2, 1, 'PENDING'),
	(60, '007', 1, 2, 1, 'PENDING'),
	(61, '008', 1, 2, 1, 'PENDING'),
	(62, '007', 1, 2, 1, 'PENDING'),
	(63, '003', 1, 2, 1, 'PENDING'),
	(64, '004', 1, 2, 1, 'PENDING'),
	(65, '002', 1, 2, 1, 'PENDING'),
	(66, '003', 1, 2, 1, 'PENDING'),
	(67, '004', 1, 2, 1, 'PENDING'),
	(68, '002', 1, 2, 1, 'PENDING'),
	(69, '002', 1, 2, 1, 'PENDING'),
	(70, '004', 1, 2, 1, 'PENDING'),
	(71, '003', 1, 2, 1, 'PENDING'),
	(72, '003', 1, 2, 1, 'PENDING'),
	(73, '004', 1, 2, 1, 'PENDING'),
	(74, '004', 2, 2, 1, 'PENDING'),
	(75, '003', 2, 2, 1, 'PENDING'),
	(76, '008', 1, 2, 1, 'PENDING'),
	(77, '007', 1, 2, 1, 'PENDING'),
	(78, '004', 1, 2, 1, 'PENDING'),
	(79, '003', 1, 2, 1, 'PENDING'),
	(80, '004', 1, 2, 1, 'PENDING'),
	(81, '004', 1, 2, 1, 'PENDING'),
	(82, '003', 1, 2, 1, 'PENDING'),
	(83, '008', 1, 2, 1, 'CONFIRM'),
	(84, '003', 1, 2, 1, 'PENDING'),
	(85, '002', 1, 2, 1, 'PENDING'),
	(86, '004', 1, 2, 1, 'PENDING'),
	(87, '003', 1, 2, 1, 'CONFIRM'),
	(88, '002', 1, 2, 1, 'PENDING'),
	(89, '008', 1, 2, 1, 'PENDING'),
	(90, '008', 1, 2, 1, 'PENDING');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;


-- Dumping structure for table assignment.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `type` varchar(10) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`index_no`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table assignment.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`index_no`, `name`, `type`, `username`, `password`) VALUES
	(1, 'Supplier', 'SUPPLIER', 'supplier', 'supplier'),
	(2, 'Customer', 'CUSTOMER', 'customer', 'customer');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
