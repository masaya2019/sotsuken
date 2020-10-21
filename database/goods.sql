-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2020-10-20 04:28:42
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
  `goods_name` varchar(10) NOT NULL,
  `goods_picture_name` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- テーブルのデータのダンプ `goods`
--

INSERT INTO `goods` (`goods_id`, `category_id`, `goods_name`, `goods_picture_name`) VALUES
('b001', 'cat01', 'アスパラガス', ''),
('b002', 'cat01', '枝豆', ''),
('b003', 'cat01', 'えのきだけ', ''),
('b004', 'cat01', 'エリンギ', ''),
('b005', 'cat01', 'オクラ', ''),
('b006', 'cat01', 'かぶ', ''),
('b007', 'cat01', 'かぼちゃ', ''),
('b008', 'cat01', 'カリフラワー', ''),
('b009', 'cat01', 'キャベツ', ''),
('b010', 'cat01', 'きゅうり', ''),
('b011', 'cat01', 'グリンピース', ''),
('b012', 'cat01', 'ごぼう', ''),
('b013', 'cat01', '小松菜', ''),
('b014', 'cat01', 'ゴーヤ', ''),
('b015', 'cat01', 'さつまいも', ''),
('b016', 'cat01', 'らっきょう', ''),
('b017', 'cat01', '里芋', ''),
('b018', 'cat01', 'さやいんげん', ''),
('b019', 'cat01', 'さやえんどう', ''),
('b020', 'cat01', '椎茸', ''),
('b021', 'cat01', 'ししとう', ''),
('b022', 'cat01', 'しそ', ''),
('b023', 'cat01', 'しめじ', ''),
('b024', 'cat01', 'じゃがいも', ''),
('b025', 'cat01', '春菊', ''),
('b026', 'cat01', 'しょうが', ''),
('b027', 'cat01', 'ズッキーニ', ''),
('b028', 'cat01', 'セロリ', ''),
('b029', 'cat01', 'そら豆', ''),
('b030', 'cat01', '大根', ''),
('b031', 'cat01', 'たけのこ', ''),
('b032', 'cat01', '玉ねぎ', ''),
('b033', 'cat01', 'チンゲン菜', ''),
('b034', 'cat01', '冬瓜', ''),
('b035', 'cat01', 'とうもろこし', ''),
('b036', 'cat01', 'トマト', ''),
('b037', 'cat01', 'なす', ''),
('b038', 'cat01', 'なめこ', ''),
('b039', 'cat01', 'にら', ''),
('b040', 'cat01', 'にんじん', ''),
('b041', 'cat01', 'にんにく', ''),
('b042', 'cat01', 'ねぎ', ''),
('b043', 'cat01', '白菜', ''),
('b044', 'cat01', '万能ねぎ', ''),
('b045', 'cat01', 'ピーマン', ''),
('b046', 'cat01', 'ふき', ''),
('b047', 'cat01', 'ブロッコリー', ''),
('b048', 'cat01', 'ほうれん草', ''),
('b049', 'cat01', '舞茸', ''),
('b050', 'cat01', 'マッシュルーム', ''),
('b051', 'cat01', '松茸', ''),
('b052', 'cat01', 'もやし', ''),
('b053', 'cat01', 'モロヘイヤ', ''),
('b054', 'cat01', '山芋', ''),
('b055', 'cat01', 'レタス', ''),
('b056', 'cat01', 'れんこん', ''),
('b057', 'cat01', 'みずな', ''),
('b058', 'cat01', 'みょうが', ''),
('b059', 'cat01', 'ルッコラ', ''),
('b060', 'cat01', '長いも', ''),
('b061', 'cat01', 'ミニトマト', ''),
('b062', 'cat01', 'パプリカ', ''),
('b063', 'cat01', 'わけぎ', ''),
('b064', 'cat01', 'あしたば', ''),
('b065', 'cat01', 'エシャロット', ''),
('b066', 'cat02', '水', ''),
('b067', 'cat02', '緑茶', ''),
('b068', 'cat02', '紅茶', ''),
('b069', 'cat02', '烏龍茶', ''),
('b070', 'cat02', 'ハブ茶', ''),
('b071', 'cat02', '麦茶', ''),
('b072', 'cat02', '玄米茶', ''),
('b073', 'cat02', 'ほうじ茶', ''),
('b074', 'cat02', '玉露', ''),
('b075', 'cat02', 'プーアル茶', ''),
('b076', 'cat02', 'ジャスミン茶', ''),
('b077', 'cat02', 'マテ茶', ''),
('b078', 'cat02', 'ハトムギ茶', ''),
('b079', 'cat02', 'ルイボスティー', ''),
('b080', 'cat02', 'コーヒー', ''),
('b081', 'cat02', 'ココア', ''),
('b082', 'cat02', 'ジュース', ''),
('b083', 'cat02', '炭酸飲料', ''),
('b084', 'cat02', 'スポーツドリンク', ''),
('b085', 'cat02', '乳性飲料', ''),
('b086', 'cat02', '栄養ドリンク', ''),
('b087', 'cat02', 'エナジードリンク', ''),
('b088', 'cat02', 'ビール', ''),
('b089', 'cat02', 'ワイン', ''),
('b090', 'cat02', 'チューハイ', ''),
('b091', 'cat02', 'リキュール', ''),
('b092', 'cat02', '日本酒', ''),
('b093', 'cat02', '焼酎', ''),
('b094', 'cat02', 'ブランデー', ''),
('b095', 'cat02', 'ウィスキー', ''),
('b096', 'cat02', '豆乳', ''),
('b097', 'cat02', '青汁', ''),
('b098', 'cat02', 'スムージー', ''),
('b099', 'cat03', '鶏もも肉', ''),
('b100', 'cat03', '鶏むね肉', ''),
('b101', 'cat03', '鶏ささみ', ''),
('b102', 'cat03', '鶏手羽肉', ''),
('b103', 'cat03', '砂肝', ''),
('b104', 'cat03', '鶏レバー', ''),
('b105', 'cat03', '鶏皮', ''),
('b106', 'cat03', '鶏ひき肉', ''),
('b107', 'cat03', '豚ロース肉', ''),
('b108', 'cat03', '豚肩ロース肉', ''),
('b109', 'cat03', '豚もも肉', ''),
('b110', 'cat03', '豚バラ肉', ''),
('b111', 'cat03', '豚ヒレ肉', ''),
('b112', 'cat03', '豚レバー', ''),
('b113', 'cat03', '豚もつ', ''),
('b114', 'cat03', 'トントロ', ''),
('b115', 'cat03', '豚足', ''),
('b116', 'cat03', '豚肩肉', ''),
('b117', 'cat03', '豚ひき肉', ''),
('b118', 'cat03', '牛ヒレ肉', ''),
('b119', 'cat03', '牛バラ肉', ''),
('b120', 'cat03', '牛もも肉', ''),
('b121', 'cat03', '牛ロース肉', ''),
('b122', 'cat03', '牛タン', ''),
('b123', 'cat03', '牛レバー', ''),
('b124', 'cat03', '牛ひき肉', ''),
('b125', 'cat03', 'ラム肉', ''),
('b126', 'cat03', '鴨肉', ''),
('b127', 'cat03', '馬肉', ''),
('b128', 'cat03', 'ベーコン', ''),
('b129', 'cat03', 'ハム', ''),
('b130', 'cat03', 'ソーセージ', ''),
('b131', 'cat03', 'チャーシュー', ''),
('b132', 'cat03', '生ハム', ''),
('b133', 'cat03', 'サラミ', ''),
('b134', 'cat03', 'スパム', ''),
('b135', 'cat03', 'コンビーフ', ''),
('b136', 'cat03', 'パンチェッタ', ''),
('b137', 'cat03', 'ビーフジャーキー', ''),
('b138', 'cat04', 'あさり', ''),
('b139', 'cat04', 'しじみ', ''),
('b140', 'cat04', 'はまぐり', ''),
('b141', 'cat04', 'ホタテ', ''),
('b142', 'cat04', '牡蠣', ''),
('b143', 'cat04', 'ムール貝', ''),
('b144', 'cat04', 'あわび', ''),
('b145', 'cat04', 'サザエ', ''),
('b146', 'cat04', 'つぶ貝', ''),
('b147', 'cat04', '赤貝', ''),
('b148', 'cat04', 'ばい貝', ''),
('b149', 'cat04', '貝柱', ''),
('b150', 'cat04', 'サケ', ''),
('b151', 'cat04', 'さば', ''),
('b152', 'cat04', 'まぐろ', ''),
('b153', 'cat04', 'サーモン', ''),
('b154', 'cat04', 'ぶり', ''),
('b155', 'cat04', 'タラ', ''),
('b156', 'cat04', 'たい', ''),
('b157', 'cat04', 'カツオ', ''),
('b158', 'cat04', 'カジキ', ''),
('b159', 'cat04', 'サンマ', ''),
('b160', 'cat04', 'イワシ', ''),
('b161', 'cat04', 'アジ', ''),
('b162', 'cat04', 'ししゃも', ''),
('b163', 'cat04', 'かれい', ''),
('b164', 'cat04', 'ひらめ', ''),
('b165', 'cat04', 'スズキ', ''),
('b166', 'cat04', 'ほっけ', ''),
('b167', 'cat04', 'きす', ''),
('b168', 'cat04', 'ムツ', ''),
('b169', 'cat04', 'タチウオ', ''),
('b170', 'cat04', 'キンキ', ''),
('b171', 'cat04', 'ニシン', ''),
('b172', 'cat04', 'メバル', ''),
('b173', 'cat04', 'はも', ''),
('b174', 'cat04', 'カワハギ', ''),
('b175', 'cat04', 'ワカサギ', ''),
('b176', 'cat04', 'ウナギ', ''),
('b177', 'cat04', 'あなご', ''),
('b178', 'cat04', 'イカ', ''),
('b179', 'cat04', 'タコ', ''),
('b180', 'cat04', '海老', ''),
('b181', 'cat04', '甘海老', ''),
('b182', 'cat04', '伊勢海老', ''),
('b183', 'cat04', 'カニ', ''),
('b184', 'cat04', 'しらす', ''),
('b185', 'cat04', 'たらこ', ''),
('b186', 'cat04', 'いくら', ''),
('b187', 'cat04', 'とびっこ', ''),
('b188', 'cat04', 'ウニ', ''),
('b189', 'cat04', 'かずのこ', ''),
('b190', 'cat04', 'しらこ', ''),
('b191', 'cat04', 'くらげ', ''),
('b192', 'cat04', 'ほたるいか', ''),
('b193', 'cat04', 'ちくわ', ''),
('b194', 'cat04', 'かまぼこ', ''),
('b195', 'cat04', 'はんぺん', ''),
('b196', 'cat04', '魚肉ソーセージ', ''),
('b197', 'cat04', 'さくらえび', ''),
('b198', 'cat04', 'すじこ', ''),
('b199', 'cat04', '明太子', ''),
('b200', 'cat04', 'シーフードミックス', ''),
('b201', 'cat04', 'ツナ缶', ''),
('b202', 'cat04', 'さばの水煮缶', ''),
('b203', 'cat05', 'いちご', ''),
('b204', 'cat05', 'キウイフルーツ', ''),
('b205', 'cat05', 'ブルーベリー', ''),
('b206', 'cat05', 'バナナ', ''),
('b207', 'cat05', 'みかん', ''),
('b208', 'cat05', 'りんご', ''),
('b209', 'cat05', 'オレンジ', ''),
('b210', 'cat05', 'チョコレート', ''),
('b211', 'cat05', 'レーズン', ''),
('b212', 'cat05', 'あんこ', ''),
('b213', 'cat05', 'もも', ''),
('b214', 'cat05', 'ぶどう', ''),
('b215', 'cat05', 'グレープフルーツ', ''),
('b216', 'cat05', 'マンゴー', ''),
('b217', 'cat05', 'パイナップル', ''),
('b218', 'cat05', 'スイカ', ''),
('b219', 'cat05', 'メロン', ''),
('b220', 'cat05', 'さくらんぼ', ''),
('b221', 'cat05', '柿', ''),
('b222', 'cat05', '梨', ''),
('b223', 'cat05', 'あんず', ''),
('b224', 'cat05', 'クランベリー', ''),
('b225', 'cat05', 'プルーン', ''),
('b226', 'cat05', '洋ナシ', ''),
('b227', 'cat05', 'プラム', ''),
('b228', 'cat05', 'くり', ''),
('b229', 'cat05', 'アイスクリーム', ''),
('b230', 'cat05', 'クラッカー', ''),
('b231', 'cat05', 'ケーキ', ''),
('b232', 'cat05', 'シュークリーム', ''),
('b233', 'cat05', 'プリン', ''),
('b234', 'cat05', 'レモン', ''),
('b235', 'cat05', 'ざくろ', ''),
('b236', 'cat05', 'びわ', ''),
('b237', 'cat05', 'ゼリー', ''),
('b238', 'cat05', 'クラッカー', ''),
('b239', 'cat05', '水あめ', ''),
('b240', 'cat06', 'メープルシロップ', ''),
('b241', 'cat06', 'ジャム', ''),
('b242', 'cat06', 'ピーナッツバター', ''),
('b243', 'cat06', 'バニラエッセンス', ''),
('b244', 'cat06', 'カレー粉', ''),
('b245', 'cat06', 'パセリ', ''),
('b246', 'cat06', 'バジル', ''),
('b247', 'cat06', 'シナモン', ''),
('b248', 'cat06', 'ローリエ', ''),
('b249', 'cat06', 'ナツメグ', ''),
('b250', 'cat06', '唐辛子', ''),
('b251', 'cat06', 'ローズマリー', ''),
('b252', 'cat06', '山椒', ''),
('b253', 'cat06', 'クミン', ''),
('b254', 'cat06', 'ターメリック', ''),
('b255', 'cat06', 'サフラン', ''),
('b256', 'cat06', 'オールスパイス', ''),
('b257', 'cat06', 'ガーリックパウダー', ''),
('b258', 'cat06', 'オレガノ', ''),
('b259', 'cat06', 'タイム', ''),
('b260', 'cat06', '八角', ''),
('b261', 'cat06', 'チリパウダー', ''),
('b262', 'cat06', 'パプリカパウダー', ''),
('b263', 'cat06', 'クローブ', ''),
('b264', 'cat06', 'カルダモン', ''),
('b265', 'cat06', 'ブラックペッパー', ''),
('b266', 'cat06', '五香粉', ''),
('b267', 'cat06', 'ホワイトペッパー', ''),
('b268', 'cat06', 'コリアンダー', ''),
('b269', 'cat06', '塩', ''),
('b270', 'cat06', '砂糖', ''),
('b271', 'cat06', '醤油', ''),
('b272', 'cat06', '料理酒', ''),
('b273', 'cat06', 'みりん', ''),
('b274', 'cat06', '酢', ''),
('b275', 'cat06', '味噌', ''),
('b276', 'cat06', 'バター', ''),
('b277', 'cat06', 'マーガリン', ''),
('b278', 'cat06', 'サラダ油', ''),
('b279', 'cat06', 'オリーブオイル', ''),
('b280', 'cat06', '', '');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `goods`
--
ALTER TABLE `goods`
  ADD PRIMARY KEY (`goods_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
