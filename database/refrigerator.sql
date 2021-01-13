-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2020-12-14 02:21:35
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
-- テーブルの構造 `refrigerator`
--

CREATE TABLE `refrigerator` (
  `refrigerator_id` varchar(5) NOT NULL,
  `mail_address` varchar(100) NOT NULL,
  `refrigerator_name` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- テーブルのデータのダンプ `refrigerator`
--

INSERT INTO `refrigerator` (`refrigerator_id`, `mail_address`, `refrigerator_name`) VALUES
('r0001', 'bjmk1290313@gn.iwasaki.ac.jp', ''),
('r0002', 'bjmk1290313@gn.iwasaki.ac.jp', '');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `refrigerator`
--
ALTER TABLE `refrigerator`
  ADD PRIMARY KEY (`refrigerator_id`,`mail_address`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
