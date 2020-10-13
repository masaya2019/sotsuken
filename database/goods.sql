-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2020-10-13 03:40:18
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
-- テーブルの構造 `goods`
--

CREATE TABLE `goods` (
  `goods_id` varchar(10) NOT NULL,
  `category_id` varchar(5) NOT NULL,
  `goods_name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- テーブルのデータのダンプ `goods`
--

INSERT INTO `goods` (`goods_id`, `category_id`, `goods_name`) VALUES
('b001', 'cat01', 'アスパラガス'),
('b002', 'cat01', '枝豆'),
('b003', 'cat01', 'えのきだけ'),
('b004', 'cat01', 'エリンギ'),
('b005', 'cat01', 'オクラ'),
('b006', 'cat01', 'かぶ'),
('b007', 'cat01', 'かぼちゃ'),
('b008', 'cat01', 'カリフラワー'),
('b009', 'cat01', 'キャベツ'),
('b010', 'cat01', 'きゅうり'),
('b011', 'cat01', 'グリンピース'),
('b012', 'cat01', 'ごぼう'),
('b013', 'cat01', '小松菜'),
('b014', 'cat01', 'ゴーヤ'),
('b015', 'cat01', 'さつまいも'),
('b017', 'cat01', '里芋'),
('b018', 'cat01', 'さやいんげん'),
('b019', 'cat01', 'さやえんどう'),
('b020', 'cat01', '椎茸'),
('b021', 'cat01', 'ししとう'),
('b022', 'cat01', 'しそ'),
('b023', 'cat01', 'しめじ'),
('b024', 'cat01', 'じゃがいも'),
('b025', 'cat01', '春菊'),
('b026', 'cat01', 'しょうが'),
('b027', 'cat01', 'ズッキーニ'),
('b028', 'cat01', 'セロリ'),
('b029', 'cat01', 'そら豆'),
('b030', 'cat01', '大根'),
('b031', 'cat01', 'たけのこ'),
('b032', 'cat01', '玉ねぎ'),
('b033', 'cat01', 'チンゲン菜'),
('b034', 'cat01', '冬瓜'),
('b035', 'cat01', 'とうもろこし'),
('b036', 'cat01', 'トマト'),
('b037', 'cat01', 'なす'),
('b038', 'cat01', 'なめこ'),
('b039', 'cat01', 'にら'),
('b040', 'cat01', 'にんじん'),
('b041', 'cat01', 'にんにく'),
('b042', 'cat01', 'ねぎ'),
('b043', 'cat01', '白菜'),
('b044', 'cat01', '万能ねぎ'),
('b045', 'cat01', 'ピーマン'),
('b046', 'cat01', 'ふき'),
('b047', 'cat01', 'ブロッコリー'),
('b048', 'cat01', 'ほうれん草'),
('b049', 'cat01', '舞茸'),
('b050', 'cat01', 'マッシュルーム'),
('b051', 'cat01', '松茸'),
('b052', 'cat01', 'もやし'),
('b053', 'cat01', 'モロヘイヤ'),
('b054', 'cat01', '山芋'),
('b055', 'cat01', 'レタス'),
('b056', 'cat01', 'れんこん'),
('b057', 'cat01', 'みずな'),
('b058', 'cat01', 'みょうが'),
('b059', 'cat01', 'ルッコラ'),
('b060', 'cat01', '長いも'),
('b061', 'cat01', 'ミニトマト'),
('b062', 'cat01', 'パプリカ'),
('b063', 'cat01', 'わけぎ'),
('b064', 'cat01', 'あしたば'),
('b065', 'cat01', 'エシャロット'),
('b016', 'cat01', 'らっきょう');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
