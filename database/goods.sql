-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2020-12-23 03:34:50
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
  `goods_picture_name` varchar(16) NOT NULL,
  `category_type` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- テーブルのデータのダンプ `goods`
--

INSERT INTO `goods` (`goods_id`, `category_id`, `goods_name`, `goods_picture_name`, `category_type`) VALUES
('b001', 'cat01', 'キャベツ', '', '12-98'),
('b002', 'cat01', '玉ねぎ', '', '12-96'),
('b003', 'cat01', 'もやし', '', '12-99-318'),
('b004', 'cat01', 'レタス', '', '12-100-2'),
('b005', 'cat01', 'ほうれん草', '', '12-457'),
('b006', 'cat01', '白菜', '', '12-453'),
('b007', 'cat01', 'かぼちゃ', '', '12-448'),
('b008', 'cat01', 'ねぎ', '', '12-103-8'),
('b009', 'cat01', 'ブロッコリー', '', '12-458'),
('b010', 'cat01', 'きゅうり', '', '12-450'),
('b011', 'cat01', 'ピーマン', '', '12-101-30'),
('b012', 'cat01', 'にんじん', '', '12-95'),
('b013', 'cat01', 'なす', '', '12-447'),
('b014', 'cat01', 'トマト', '', '12-454'),
('b015', 'cat01', '大根', '', '12-449'),
('b016', 'cat01', 'じゃがいも', '', '12-97'),
('b017', 'cat01', '里芋', '', '12-103-308'),
('b018', 'cat01', 'パプリカ', '', '12-101-456'),
('b019', 'cat01', 'ミニトマト', '', '12-454-2047'),
('b020', 'cat01', '枝豆', '', '12-101-24'),
('b021', 'cat01', 'オクラ', '', '12-101-32'),
('b022', 'cat01', '大葉（しそ）', '', '12-107-448'),
('b023', 'cat01', '万能ねぎ', '', '12-107-856'),
('b024', 'cat01', 'なめこ', '', '12-105-79'),
('b025', 'cat01', 'さつまいも', '', '12-452'),
('b026', 'cat01', 'しょうが', '', '12-107-316'),
('b027', 'cat01', 'ズッキーニ', '', '12-101-315'),
('b028', 'cat01', 'にんにく', '', '12-107-9'),
('b029', 'cat01', 'しめじ', '', '12-105-76'),
('b030', 'cat01', 'ごぼう', '', '12-455'),
('b031', 'cat01', '長いも', '', '12-102-847'),
('b032', 'cat01', '椎茸', '', '12-105-75'),
('b033', 'cat01', 'チンゲン菜', '', '12-102-319'),
('b034', 'cat01', '冬瓜', '', '12-101-821'),
('b035', 'cat01', 'とうもろこし', '', '12-101-422'),
('b036', 'cat01', 'ゴーヤ', '', '12-101-31'),
('b037', 'cat01', '小松菜', '', '12-456'),
('b038', 'cat01', 'らっきょう', '', '12-104-1543'),
('b039', 'cat01', 'にら', '', '12-103-4'),
('b040', 'cat01', '春菊', '', '12-102-449'),
('b041', 'cat01', 'セロリ', '', '12-103-314'),
('b042', 'cat01', 'カリフラワー', '', '12-103-34'),
('b043', 'cat01', 'かぶ', '', '12-102-16'),
('b044', 'cat01', 'そら豆', '', '12-100-23'),
('b045', 'cat01', 'グリーンピース', '', '12-100-844'),
('b046', 'cat01', 'ふき', '', ''),
('b047', 'cat01', 'アスパラガス', '', '12-100-11'),
('b048', 'cat01', 'ししとう', '', '12-101-1532'),
('b049', 'cat01', '舞茸', '', '12-105-77'),
('b050', 'cat01', 'マッシュルーム', '', '12-105-338'),
('b051', 'cat01', '松茸', '', '12-105-337'),
('b052', 'cat01', 'えのきだけ', '', '12-105-78'),
('b053', 'cat01', 'モロヘイヤ', '', '12-101-509'),
('b054', 'cat01', '山芋', '', '12-102-18'),
('b055', 'cat01', 'エリンギ', '', '12-105-339'),
('b056', 'cat01', 'れんこん', '', '12-102-15'),
('b057', 'cat01', 'みずな', '', '12-103-3'),
('b058', 'cat01', 'みょうが', '', '12-107-36'),
('b059', 'cat01', 'ルッコラ', '', ''),
('b060', 'cat01', 'たけのこ', '', '12-100-10'),
('b061', 'cat01', 'さやいんげん', '', '12-101-22'),
('b062', 'cat01', 'さやえんどう', '', '12-100-845'),
('b063', 'cat01', 'わけぎ', '', ''),
('b064', 'cat01', 'あしたば', '', ''),
('b065', 'cat01', 'エシャロット', '', '12-104-1548'),
('b066', 'cat02', '水', '', ''),
('b067', 'cat02', '緑茶', '', ''),
('b068', 'cat02', '紅茶', '', ''),
('b069', 'cat02', '烏龍茶', '', ''),
('b070', 'cat02', 'ハブ茶', '', ''),
('b071', 'cat02', '麦茶', '', ''),
('b072', 'cat02', '玄米茶', '', ''),
('b073', 'cat02', 'ほうじ茶', '', ''),
('b074', 'cat02', '玉露', '', ''),
('b075', 'cat02', 'プーアル茶', '', ''),
('b076', 'cat02', 'ジャスミン茶', '', ''),
('b077', 'cat02', 'マテ茶', '', ''),
('b078', 'cat02', 'ハトムギ茶', '', ''),
('b079', 'cat02', 'ルイボスティー', '', ''),
('b080', 'cat02', 'コーヒー', '', ''),
('b081', 'cat02', 'ココア', '', ''),
('b082', 'cat02', 'ジュース', '', ''),
('b083', 'cat02', '炭酸飲料', '', ''),
('b084', 'cat02', 'スポーツドリンク', '', ''),
('b085', 'cat02', '乳性飲料', '', ''),
('b086', 'cat02', '栄養ドリンク', '', ''),
('b087', 'cat02', 'エナジードリンク', '', ''),
('b088', 'cat02', '豆乳', '', '35-470-1613'),
('b089', 'cat02', '青汁', '', ''),
('b090', 'cat02', 'スムージー', '', '27-465-1597'),
('b091', 'cat02', 'リキュール', '', ''),
('b092', 'cat02', '日本酒', '', ''),
('b093', 'cat02', '焼酎', '', ''),
('b094', 'cat02', 'ブランデー', '', ''),
('b095', 'cat02', 'ウィスキー', '', ''),
('b096', 'cat02', 'ビール', '', ''),
('b097', 'cat02', 'ワイン', '', ''),
('b098', 'cat02', '甘酒', '', ''),
('b099', 'cat02', '梅酒', '', ''),
('b100', 'cat02', 'チューハイ', '', ''),
('b101', 'cat03', '鶏もも肉', '', '10-277-518'),
('b102', 'cat03', '鶏むね肉', '', '10-277-1119'),
('b103', 'cat03', '鶏ささみ', '', '10-277-519'),
('b104', 'cat03', '鶏手羽肉', '', '10-277-1488'),
('b105', 'cat03', '砂肝', '', '10-277-1489'),
('b106', 'cat03', '鶏レバー', '', '10-277-1490'),
('b107', 'cat03', '鶏皮', '', ''),
('b108', 'cat03', '鶏ひき肉', '', '10-278-838'),
('b109', 'cat03', '豚ロース肉', '', '10-276-1485'),
('b110', 'cat03', '豚肩ロース肉', '', '10-276-2142'),
('b111', 'cat03', '豚もも肉', '', '10-276-1486'),
('b112', 'cat03', '豚バラ肉', '', '10-276-830'),
('b113', 'cat03', '豚ヒレ肉', '', '10-276-1484'),
('b114', 'cat03', '豚レバー', '', '10-276-1487'),
('b115', 'cat03', '豚もつ', '', ''),
('b116', 'cat03', 'トントロ', '', ''),
('b117', 'cat03', '豚足', '', ''),
('b118', 'cat03', '豚肩肉', '', '10-276-2013'),
('b119', 'cat03', '豚ひき肉', '', '10-278-836'),
('b120', 'cat03', '牛ヒレ肉', '', ''),
('b121', 'cat03', '牛バラ肉', '', '10-275-2134'),
('b122', 'cat03', '牛もも肉', '', ''),
('b123', 'cat03', '牛ロース肉', '', ''),
('b124', 'cat03', '牛タン', '', '10-275-1483'),
('b125', 'cat03', '牛レバー', '', ''),
('b126', 'cat03', '牛ひき肉', '', '10-278-835'),
('b127', 'cat03', 'ラム肉', '', '10-69-45'),
('b128', 'cat03', '鴨肉', '', '10-69-458'),
('b129', 'cat03', '馬肉', '', '10-69-461'),
('b130', 'cat03', 'ベーコン', '', '10-68-49'),
('b131', 'cat03', 'ハム', '', '10-67'),
('b132', 'cat03', 'ソーセージ', '', '10-66'),
('b133', 'cat03', 'チャーシュー', '', '31-333-1259'),
('b134', 'cat03', '生ハム', '', '10-67-1491'),
('b135', 'cat03', 'サラミ', '', ''),
('b136', 'cat03', 'スパム', '', '10-69-51'),
('b137', 'cat03', 'コンビーフ', '', ''),
('b138', 'cat03', 'パンチェッタ', '', ''),
('b139', 'cat03', 'ビーフジャーキー', '', ''),
('b140', 'cat03', '卵', '', '33'),
('b141', 'cat03', '牛乳', '', '27-268-266'),
('b142', 'cat03', 'ヨーグルト', '', '13-483'),
('b143', 'cat03', 'クリーム', '', '19-192-240'),
('b144', 'cat03', 'プロセスチーズ', '', '13-482'),
('b145', 'cat03', 'スライスチーズ', '', '13-482'),
('b146', 'cat03', '粉チーズ', '', ''),
('b147', 'cat03', 'クリームチーズ', '', '13-482-1641'),
('b148', 'cat03', 'モッツァレラチーズ', '', '13-482-1642'),
('b149', 'cat03', 'カマンベールチーズ', '', '13-482-1643'),
('b150', 'cat03', 'チーズ', '', '13-482'),
('b151', 'cat03', 'サワークリーム', '', ''),
('b152', 'cat03', 'うずらの卵', '', ''),
('b153', 'cat04', 'あさり', '', '11-82-60'),
('b154', 'cat04', 'しじみ', '', '11-82-478'),
('b155', 'cat04', 'はまぐり', '', '11-82-63'),
('b156', 'cat04', 'ホタテ', '', '11-82-61'),
('b157', 'cat04', '牡蠣', '', '11-444'),
('b158', 'cat04', 'ムール貝', '', '11-82-477'),
('b159', 'cat04', 'あわび', '', '11-82-329'),
('b160', 'cat04', 'サザエ', '', '11-82-330'),
('b161', 'cat04', 'つぶ貝', '', '11-82-475'),
('b162', 'cat04', '赤貝', '', '11-82-64'),
('b163', 'cat04', 'ばい貝', '', '11-82-64'),
('b164', 'cat04', '貝柱', '', '11-82'),
('b165', 'cat04', 'サケ', '', '11-70-55'),
('b166', 'cat04', 'さば', '', '11-72-322'),
('b167', 'cat04', 'まぐろ', '', '11-77'),
('b168', 'cat04', 'サーモン', '', '11-70'),
('b169', 'cat04', 'ぶり', '', '11-74'),
('b170', 'cat04', 'タラ', '', '11-443'),
('b171', 'cat04', 'たい', '', '11-76'),
('b172', 'cat04', 'カツオ', '', '11-78-324'),
('b173', 'cat04', 'カジキ', '', '11-78-522'),
('b174', 'cat04', 'サンマ', '', '11-75'),
('b175', 'cat04', 'イワシ', '', '11-71'),
('b176', 'cat04', 'アジ', '', '11-73'),
('b177', 'cat04', 'ししゃも', '', '11-78-471'),
('b178', 'cat04', 'かれい', '', '11-78-323'),
('b179', 'cat04', 'ひらめ', '', '11-78-1499'),
('b180', 'cat04', 'スズキ', '', '11-78-2029'),
('b181', 'cat04', 'ほっけ', '', '11-78-468'),
('b182', 'cat04', 'きす', '', '11-78-840'),
('b183', 'cat04', 'ムツ', '', ''),
('b184', 'cat04', 'タチウオ', '', '11-78-2027'),
('b185', 'cat04', 'キンキ', '', ''),
('b186', 'cat04', 'ニシン', '', '11-78-1497'),
('b187', 'cat04', 'メバル', '', '11-78-841'),
('b188', 'cat04', 'はも', '', '11-78-1501'),
('b189', 'cat04', 'カワハギ', '', ''),
('b190', 'cat04', 'ワカサギ', '', '11-78-327'),
('b191', 'cat04', 'ウナギ', '', '11-78-334'),
('b192', 'cat04', 'あなご', '', '11-78-472'),
('b193', 'cat04', 'イカ', '', '11-80-68'),
('b194', 'cat04', 'タコ', '', '11-81'),
('b195', 'cat04', '海老', '', '11-79'),
('b196', 'cat04', '甘海老', '', '11-79-1505'),
('b197', 'cat04', '伊勢海老', '', '11-79-65'),
('b198', 'cat04', 'カニ', '', '11-83'),
('b199', 'cat04', 'しらす', '', '11-78-469'),
('b200', 'cat04', 'たらこ', '', '11-445-1511'),
('b201', 'cat04', 'いくら', '', '11-445-1512'),
('b202', 'cat04', 'とびっこ', '', ''),
('b203', 'cat04', 'ウニ', '', '11-446-1515'),
('b204', 'cat04', 'かずのこ', '', '49-641'),
('b205', 'cat04', 'しらこ', '', '11-446-1516'),
('b206', 'cat04', 'くらげ', '', '11-446-1517'),
('b207', 'cat04', 'ほたるいか', '', '11-80-2032'),
('b208', 'cat04', 'ちくわ', '', '13-108-490'),
('b209', 'cat04', 'かまぼこ', '', '13-108-528'),
('b210', 'cat04', 'はんぺん', '', '13-108-107'),
('b211', 'cat04', '魚肉ソーセージ', '', ''),
('b212', 'cat04', 'さくらえび', '', '11-79-1504'),
('b213', 'cat04', 'すじこ', '', '11-445-1512'),
('b214', 'cat04', '明太子', '', '11-445-1510'),
('b215', 'cat04', 'シーフードミックス', '', ''),
('b216', 'cat04', 'ツナ缶', '', '13-109-843'),
('b217', 'cat04', 'さばの水煮缶', '', '13-109-1637'),
('b218', 'cat04', 'めかぶ', '', '13-113-1653'),
('b219', 'cat04', 'わかめ', '', '13-113-73'),
('b220', 'cat04', 'ひじき', '', '13-113-120'),
('b221', 'cat04', 'のり', '', '13-113-336'),
('b222', 'cat04', 'かつお節', '', '13-114-121'),
('b223', 'cat04', '昆布', '', '13-113-72'),
('b224', 'cat04', 'アンチョビ', '', ''),
('b225', 'cat04', 'もずく', '', '13-113-335'),
('b226', 'cat05', 'いちご', '', '34-692-1965'),
('b227', 'cat05', 'キウイフルーツ', '', '34-691'),
('b228', 'cat05', 'スイカ', '', '34-693-1976'),
('b229', 'cat05', 'バナナ', '', '34-697'),
('b230', 'cat05', 'みかん', '', '34-695-1987'),
('b231', 'cat05', 'りんご', '', '34-688'),
('b232', 'cat05', 'オレンジ', '', '34-702'),
('b233', 'cat05', 'マンゴー', '', '34-693-1980'),
('b234', 'cat05', 'パイナップル', '', '34-693-1979'),
('b235', 'cat05', 'メロン', '', '34-693-1977'),
('b236', 'cat05', 'もも', '', '34-693-1970'),
('b237', 'cat05', 'ぶどう', '', '34-689-1983'),
('b238', 'cat05', 'グレープフルーツ', '', '34-690'),
('b239', 'cat05', 'チョコレート', '', '21-208'),
('b240', 'cat05', 'アイスクリーム', '', '21-442-1468'),
('b241', 'cat05', 'ブルーベリー', '', '34-462-1552'),
('b242', 'cat05', 'あんこ', '', ''),
('b243', 'cat05', 'さくらんぼ', '', '34-693-1974'),
('b244', 'cat05', '柿', '', '34-460'),
('b245', 'cat05', '梨', '', '34-689-1982'),
('b246', 'cat05', 'あんず', '', '34-693-1972'),
('b247', 'cat05', 'クランベリー', '', ''),
('b248', 'cat05', 'プルーン', '', '34-693-1971'),
('b249', 'cat05', '洋ナシ', '', '34-689-1984'),
('b250', 'cat05', 'プラム', '', ''),
('b251', 'cat05', 'くり', '', '34-689-1981'),
('b252', 'cat05', 'レーズン', '', '22-220-997'),
('b253', 'cat05', 'ナッツ', '', '34-693-1969'),
('b254', 'cat05', 'ケーキ', '', '21-206'),
('b255', 'cat05', 'シュークリーム', '', '21-212-195'),
('b256', 'cat05', 'プリン', '', '21-211'),
('b257', 'cat05', 'レモン', '', '34-461-1551'),
('b258', 'cat05', 'ざくろ', '', '34-689-1985'),
('b259', 'cat05', 'びわ', '', '34-693-1975'),
('b260', 'cat05', 'ゼリー', '', '21-441-1464'),
('b261', 'cat05', 'クラッカー', '', ''),
('b262', 'cat05', '水あめ', '', ''),
('b263', 'cat06', '塩', '', ''),
('b264', 'cat06', '砂糖', '', ''),
('b265', 'cat06', '醤油', '', ''),
('b266', 'cat06', '料理酒', '', ''),
('b267', 'cat06', 'みりん', '', ''),
('b268', 'cat06', '酢', '', ''),
('b269', 'cat06', '味噌', '', '19-675-1568'),
('b270', 'cat06', 'バター', '', ''),
('b271', 'cat06', 'マーガリン', '', ''),
('b272', 'cat06', 'サラダ油', '', ''),
('b273', 'cat06', 'オリーブオイル', '', '19-464'),
('b274', 'cat06', 'ごま油', '', '19-710'),
('b275', 'cat06', 'マヨネーズ', '', '19-192-627'),
('b276', 'cat06', 'ケチャップ', '', ''),
('b277', 'cat06', 'ドレッシング', '', '19-196'),
('b278', 'cat06', '塩コショウ', '', ''),
('b279', 'cat06', 'めんつゆ', '', '19-194-250'),
('b280', 'cat06', 'ウスターソース', '', ''),
('b281', 'cat06', 'はちみつ', '', ''),
('b282', 'cat06', '和風だし', '', '19-195'),
('b283', 'cat06', '中華だし', '', '19-711-2078'),
('b284', 'cat06', 'うま味調味料', '', ''),
('b285', 'cat06', 'おろしにんにく', '', ''),
('b286', 'cat06', 'おろししょうが', '', ''),
('b287', 'cat06', 'オイスターソース', '', '19-711-2072'),
('b288', 'cat06', 'ブラックペッパー', '', ''),
('b289', 'cat06', 'コンソメ', '', '17-167'),
('b290', 'cat06', 'わさび', '', ''),
('b291', 'cat06', 'からし', '', ''),
('b292', 'cat06', 'ポン酢', '', '19-675-1576'),
('b293', 'cat06', 'ジャム', '', '21-218'),
('b294', 'cat06', 'ピーナッツバター', '', '21-218-295'),
('b295', 'cat06', 'バニラエッセンス', '', ''),
('b296', 'cat06', 'カレー粉', '', '19-675-1578'),
('b297', 'cat06', 'パセリ', '', '12-107-450'),
('b298', 'cat06', 'バジル', '', '12-107-1537'),
('b299', 'cat06', 'シナモン', '', ''),
('b300', 'cat06', 'ローリエ', '', ''),
('b301', 'cat06', 'ナツメグ', '', ''),
('b302', 'cat06', '唐辛子', '', '12-107-513'),
('b303', 'cat06', 'ローズマリー', '', '12-107-1536'),
('b304', 'cat06', '山椒', '', ''),
('b305', 'cat06', 'クミン', '', ''),
('b306', 'cat06', 'ターメリック', '', ''),
('b307', 'cat06', 'サフラン', '', ''),
('b308', 'cat06', 'オールスパイス', '', ''),
('b309', 'cat06', 'ガーリックパウダー', '', ''),
('b310', 'cat06', 'オレガノ', '', ''),
('b311', 'cat06', 'タイム', '', ''),
('b312', 'cat06', '八角', '', ''),
('b313', 'cat06', 'チリパウダー', '', ''),
('b314', 'cat06', 'パプリカパウダー', '', ''),
('b315', 'cat06', 'クローブ', '', ''),
('b316', 'cat06', 'カルダモン', '', ''),
('b317', 'cat06', '五香粉', '', ''),
('b318', 'cat06', 'ホワイトペッパー', '', ''),
('b319', 'cat06', 'コリアンダー', '', ''),
('b320', 'cat06', 'メープルシロップ', '', ''),
('b321', 'cat06', 'マスタード', '', ''),
('b322', 'cat06', 'ラー油', '', '19-193-363'),
('b323', 'cat06', 'コチュジャン', '', '19-711-2074'),
('b324', 'cat06', '柚子胡椒', '', '19-463'),
('b325', 'cat06', '魚醤', '', ''),
('b326', 'cat06', 'バルサミコ酢', '', '19-675-1572'),
('b327', 'cat06', 'タバスコ', '', ''),
('b328', 'cat06', 'デミグラスソース', '', '19-192-960'),
('b329', 'cat06', 'チリソース', '', '19-192-1560'),
('b330', 'cat06', '塩こうじ', '', '19-675-1566'),
('b331', 'cat06', 'たれ', '', '19-193'),
('b332', 'cat06', '七味唐辛子', '', ''),
('b333', 'cat06', '白だし', '', '19-195-1565'),
('b334', 'cat06', '豆板醤', '', '19-711-2075'),
('b335', 'cat06', '甜麺醤', '', ''),
('b336', 'cat06', '豆鼓醤', '', ''),
('b337', 'cat06', '一味唐辛子', '', ''),
('b338', 'cat06', 'カレールウ', '', '30-307'),
('b339', 'cat06', 'レモン汁', '', '34-461'),
('b340', 'cat06', '牛脂', '', ''),
('b341', 'cat06', 'ラード', '', ''),
('b342', 'cat06', '土佐酢', '', ''),
('b343', 'cat07', '米', '', ''),
('b344', 'cat07', 'うどん', '', '16-152'),
('b345', 'cat07', 'パスタ', '', '16-152'),
('b346', 'cat07', 'ショートパスタ', '', '13-479-1627'),
('b347', 'cat07', 'そば', '', '16-153'),
('b348', 'cat07', '食パン', '', '22-434-1430'),
('b349', 'cat07', 'パン', '', '22'),
('b350', 'cat07', 'フランスパン', '', '22-219-165'),
('b351', 'cat07', '菓子パン', '', '22-221'),
('b352', 'cat07', '餅', '', '21-214-185'),
('b353', 'cat07', '素麺', '', '16-154-151'),
('b354', 'cat07', '春雨', '', '13-114-350'),
('b355', 'cat07', '薄力粉', '', '13-481-1632'),
('b356', 'cat07', '片栗粉', '', '13-481-2066'),
('b357', 'cat07', 'パン粉', '', '22'),
('b358', 'cat07', 'お好み焼き粉', '', '16-385'),
('b359', 'cat07', 'ホットケーキミックス', '', '13-480-1628'),
('b360', 'cat07', 'ベーキングパウダー', '', '21'),
('b361', 'cat07', 'もち米', '', '13-478-1626'),
('b362', 'cat07', '玄米', '', ''),
('b363', 'cat07', 'ドライイースト', '', ''),
('b364', 'cat07', 'ゼラチン', '', '21-441-1464'),
('b365', 'cat07', '米粉', '', '13-481-1629'),
('b366', 'cat07', 'コーンスターチ', '', ''),
('b367', 'cat07', '天ぷら粉', '', '30-313-1216'),
('b368', 'cat07', '白玉粉', '', '21-214-1471'),
('b369', 'cat07', '上新粉', '', '13-481-2067'),
('b370', 'cat07', '雑穀米', '', ''),
('b371', 'cat07', 'くず粉', '', '13-481-1633'),
('b372', 'cat07', '押し麦', '', ''),
('b373', 'cat07', 'そば粉', '', '13-481-1631'),
('b374', 'cat07', '玄米粉', '', ''),
('b375', 'cat07', '麺', '', '16'),
('b376', 'cat07', '中力粉', '', '13-481-1632'),
('b377', 'cat07', '強力粉', '', '13-481-1632'),
('b378', 'cat08', '豆腐', '', '30-315'),
('b379', 'cat08', '納豆', '', '35-468-1611'),
('b380', 'cat08', '油揚げ', '', '5-473-1616'),
('b381', 'cat08', '厚揚げ', '', '35-467-1610'),
('b382', 'cat08', '豆', '', '35-477'),
('b383', 'cat08', '大豆', '', '35-477-1620'),
('b384', 'cat08', 'きなこ', '', '13-481-1630'),
('b385', 'cat08', 'おから', '', '35-466-1609'),
('b386', 'cat08', 'あずき', '', ''),
('b387', 'cat08', '高野豆腐', '', '35-469-1612'),
('b388', 'cat08', '湯葉', '', ''),
('b389', 'cat08', 'ココナッツミルク', '', '27-268-2091'),
('b390', 'cat08', 'スキムミルク', '', ''),
('b391', 'cat08', 'こんにゃく', '', '13-111'),
('b392', 'cat08', 'しらたき', '', '13-112-125'),
('b393', 'cat08', '黒ごま', '', ''),
('b394', 'cat08', '白ごま', '', ''),
('b395', 'cat08', '塩昆布', '', '13-113-1652'),
('b396', 'cat08', '天かす', '', ''),
('b397', 'cat08', 'とろろ昆布', '', ''),
('b398', 'cat08', '青のり', '', ''),
('b399', 'cat08', 'キムチ', '', '42-554-1784'),
('b400', 'cat08', 'ザーサイ', '', ''),
('b401', 'cat08', 'メンマ', '', ''),
('b402', 'cat08', '抹茶', '', '27-267-1959'),
('b403', 'cat08', 'オリーブ', '', ''),
('b404', 'cat08', 'ホワイトソース', '', '19-192-1554'),
('b405', 'cat08', 'がんも', '', '13-108-529'),
('b406', 'cat08', 'さつま揚げ', '', '13-108-1635'),
('b407', 'cat08', 'ぎょうざの皮', '', '30-301-2172'),
('b408', 'cat08', 'ライスペーパー', '', ''),
('b409', 'cat08', 'ところてん', '', ''),
('b410', 'cat08', '松の実', '', ''),
('b411', 'cat08', 'クコの実', '', ''),
('b412', 'cat08', 'ゆかり', '', ''),
('b413', 'cat08', 'かんぴょう', '', '13-114-117'),
('b414', 'cat08', '麩', '', '13-114-533'),
('b415', 'cat08', '酒粕', '', '19-675-1581'),
('b416', 'cat08', 'コーヒー豆', '', '27-266'),
('b417', 'cat08', '梅', '', '34-693-1967'),
('b418', 'cat08', '梅干し', '', '13-484-1660');


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