-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2020-12-21 03:43:32
-- サーバのバージョン： 10.4.11-MariaDB
-- PHP のバージョン: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `comasy`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `ingredient`
--

CREATE TABLE `ingredient` (
  `recipe_id` varchar(6) NOT NULL,
  `goods_id` varchar(10) NOT NULL,
  `number` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- テーブルのデータのダンプ `ingredient`
--

INSERT INTO `ingredient` (`recipe_id`, `goods_id`, `number`) VALUES
('re0001', 'b066', 1),
('re0001', 'b246', 1),
('re0001', 'b259', 1),
('re0001', 'b355', 1),
('re0001', 'b399', 1),
('re0002', 'b028', 2),
('re0002', 'b066', 1000),
('re0002', 'b240', 30),
('re0002', 'b250', 30),
('re0002', 'b279', 2),
('re0002', 'b322', 100),
('re0003', 'b099', 250),
('re0003', 'b242', 1),
('re0003', 'b243', 2),
('re0003', 'b249', 1),
('re0003', 'b263', 1),
('re0003', 'b265', 1),
('re0003', 'b332', 3),
('re0003', 'b333', 1),
('re0004', 'b008', 1),
('re0004', 'b131', 1),
('re0004', 'b242', 1),
('re0004', 'b249', 1),
('re0004', 'b255', 1),
('re0004', 'b260', 1),
('re0004', 'b320', 1),
('re0004', 'b406', 1),
('re0005', 'b066', 6),
('re0005', 'b242', 1),
('re0005', 'b244', 2),
('re0005', 'b249', 1),
('re0005', 'b259', 1),
('re0005', 'b406', 4),
('re0006', 'b150', 1),
('re0006', 'b242', 1),
('re0006', 'b244', 1),
('re0006', 'b247', 1),
('re0006', 'b249', 1),
('re0006', 'b255', 1),
('re0006', 'b316', 1),
('re0006', 'b322', 1),
('re0007', 'b028', 1),
('re0007', 'b097', 30),
('re0007', 'b138', 200),
('re0007', 'b250', 30),
('re0007', 'b274', 1),
('re0007', 'b279', 1),
('re0007', 'b322', 100),
('re0008', 'b026', 2),
('re0008', 'b066', 150),
('re0008', 'b163', 1),
('re0008', 'b241', 1),
('re0008', 'b242', 2),
('re0008', 'b243', 2),
('re0008', 'b244', 3);

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`recipe_id`,`goods_id`) USING BTREE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
