-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               9.0.1 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for cg_db
CREATE DATABASE IF NOT EXISTS `cg_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cg_db`;

-- Dumping structure for table cg_db.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cg_db.category: ~6 rows (approximately)
INSERT INTO `category` (`id`, `category_name`) VALUES
	(1, 'Test'),
	(2, 'Electronics'),
	(3, 'Home Appliances'),
	(4, 'Books'),
	(5, 'Clothing'),
	(6, 'Outdoor');

-- Dumping structure for table cg_db.product
CREATE TABLE IF NOT EXISTS `product` (
  `category_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `price` bigint DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cg_db.product: ~20 rows (approximately)
INSERT INTO `product` (`category_id`, `id`, `price`, `product_name`, `status`) VALUES
	(1, 5, 999000, 'Smartphone Model X', 'Available'),
	(1, 6, 1200000, 'Laptop Model Y', 'Available'),
	(1, 7, 300000, 'Tablet Model Z', 'Out of Stock'),
	(2, 8, 150000, 'Microwave Model A', 'Available'),
	(2, 9, 800000, 'Refrigerator Model B', 'Available'),
	(2, 10, 400000, 'Blender Model C', 'Available'),
	(3, 11, 20000000, 'Book Title D', 'Available'),
	(3, 12, 1500000, 'Book Title E', 'Available'),
	(3, 13, 300000, 'Book Title F', 'Available'),
	(4, 14, 50000, 'T-Shirt Model G', 'Available'),
	(4, 15, 750000, 'Jeans Model H', 'Available'),
	(4, 16, 2000000, 'Jacket Model I', 'Available'),
	(5, 17, 1000000, 'Tent Model J', 'Available'),
	(5, 18, 500000, 'Sleeping Bag Model K', 'Available'),
	(5, 19, 1500000, 'Backpack Model L', 'Available'),
	(1, 20, 250000, 'Headphones Model M', 'Available'),
	(2, 21, 100000, 'Toaster Model N', 'Available'),
	(3, 22, 4000000, 'Book Title O', 'Available'),
	(4, 23, 1200000, 'Dress Model P', 'Available'),
	(5, 24, 1100000, 'Hiking Book Model Q', 'Available');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
