-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2020-11-16 01:21:26
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
-- テーブルの構造 `search_index`
--

CREATE TABLE `search_index` (
  `goods_id` varchar(10) NOT NULL,
  `keyword` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- テーブルのデータのダンプ `search_index`
--

INSERT INTO `search_index` (`goods_id`, `keyword`) VALUES
('b001', 'きゃべつ'),
('b001', 'キャベツ'),
('b001', '野菜'),
('b002', 'たまねぎ'),
('b002', 'タマネギ'),
('b002', '玉ねぎ'),
('b002', '玉葱'),
('b002', '野菜'),
('b003', 'もやし'),
('b003', '野菜'),
('b004', 'れたす'),
('b004', 'レタス'),
('b004', '野菜'),
('b005', 'ほうれんそう'),
('b005', 'ほうれん草'),
('b005', 'ホウレンソウ'),
('b005', 'ホウレン草'),
('b005', '野菜'),
('b006', 'はくさい'),
('b006', 'ハクサイ'),
('b006', '白菜'),
('b006', '野菜'),
('b007', 'かぼちゃ'),
('b007', 'カボチャ'),
('b007', '南瓜'),
('b007', '野菜'),
('b008', 'ねぎ'),
('b008', 'ネギ'),
('b008', '葱'),
('b008', '野菜'),
('b009', 'ぶろっこりー'),
('b009', 'ブロッコリー'),
('b009', '野菜'),
('b010', 'きゅうり'),
('b010', 'キュウリ'),
('b010', '野菜'),
('b011', 'ぴーまん'),
('b011', 'ピーマン'),
('b011', '野菜'),
('b012', 'にんじん'),
('b012', 'ニンジン'),
('b012', '人参'),
('b012', '野菜'),
('b013', 'なす'),
('b013', 'ナス'),
('b013', '茄子'),
('b013', '野菜'),
('b014', 'とまと'),
('b014', 'トマト'),
('b014', '野菜'),
('b015', 'だいこん'),
('b015', 'ダイコン'),
('b015', '大根'),
('b015', '野菜'),
('b016', 'いも'),
('b016', 'じゃがいも'),
('b016', 'じゃが芋'),
('b016', 'ジャガイモ'),
('b016', '芋'),
('b016', '野菜'),
('b017', 'いも'),
('b017', 'さといも'),
('b017', 'サトイモ'),
('b017', '芋'),
('b017', '里芋'),
('b017', '野菜'),
('b018', 'ぱぷりか'),
('b018', 'パプリカ'),
('b018', '野菜'),
('b019', 'みにとまと'),
('b019', 'ミニトマト'),
('b019', '野菜'),
('b020', 'えだまめ'),
('b020', '枝豆'),
('b020', '野菜'),
('b021', 'おくら'),
('b021', 'オクラ'),
('b021', '野菜'),
('b022', 'しそ'),
('b022', '大葉'),
('b022', '紫蘇'),
('b022', '野菜'),
('b023', 'ばんのうねぎ'),
('b023', '万能ねぎ'),
('b023', '万能葱'),
('b023', '野菜'),
('b024', 'きのこ'),
('b024', 'なめこ'),
('b024', 'キノコ'),
('b024', '野菜'),
('b025', 'いも'),
('b025', 'さつまいも'),
('b025', 'サツマイモ'),
('b025', '芋'),
('b025', '野菜'),
('b026', 'しょうが'),
('b026', 'ショウガ'),
('b026', '生姜'),
('b026', '野菜'),
('b027', 'ずっきーに'),
('b027', 'ズッキーニ'),
('b027', '野菜'),
('b028', 'にんにく'),
('b028', 'ニンニク'),
('b028', '野菜'),
('b029', 'きのこ'),
('b029', 'しめじ'),
('b029', 'キノコ'),
('b029', '野菜'),
('b030', 'ごぼう'),
('b030', '野菜'),
('b031', 'いも'),
('b031', 'ながいも'),
('b031', '芋'),
('b031', '野菜'),
('b031', '長いも'),
('b031', '長芋'),
('b032', 'きのこ'),
('b032', 'しいたけ'),
('b032', 'キノコ'),
('b032', 'シイタケ'),
('b032', '椎茸'),
('b032', '野菜'),
('b033', 'ちんげんさい'),
('b033', 'チンゲンサイ'),
('b033', 'チンゲン菜'),
('b033', '野菜'),
('b034', 'とうがん'),
('b034', '冬瓜'),
('b034', '野菜'),
('b035', 'とうもろこし'),
('b035', 'トウモロコシ'),
('b035', '野菜'),
('b036', 'ゴーヤ'),
('b036', 'ごーや'),
('b036', '野菜'),
('b037', 'こまつな'),
('b037', '小松菜'),
('b037', '野菜'),
('b038', 'らっきょう'),
('b038', '野菜'),
('b039', 'にら'),
('b039', 'ニラ'),
('b039', '野菜'),
('b040', 'しゅんぎく'),
('b040', '春菊'),
('b040', '野菜'),
('b041', 'せろり'),
('b041', 'セロリ'),
('b041', '野菜'),
('b042', 'かりふらわー'),
('b042', 'カリフラワー'),
('b042', '野菜'),
('b043', 'かぶ'),
('b043', 'カブ'),
('b043', '野菜'),
('b044', 'そらまめ'),
('b044', 'そら豆'),
('b044', 'ソラマメ'),
('b044', '野菜'),
('b045', 'ぐりんぴーす'),
('b045', 'グリンピース'),
('b045', '野菜'),
('b046', 'ふき'),
('b046', '野菜'),
('b047', 'あすぱらがす'),
('b047', 'アスパラガス'),
('b047', '野菜'),
('b048', 'ししとう'),
('b048', 'ししとうがらし'),
('b048', 'しし唐'),
('b048', '野菜'),
('b049', 'きのこ'),
('b049', 'まいたけ'),
('b049', 'キノコ'),
('b049', 'マイタケ'),
('b049', '舞茸'),
('b049', '野菜'),
('b050', 'きのこ'),
('b050', 'キノコ'),
('b050', 'マッシュルーム'),
('b050', '野菜'),
('b051', 'きのこ'),
('b051', 'まつたけ'),
('b051', 'キノコ'),
('b051', 'マツタケ'),
('b051', '松茸'),
('b051', '野菜'),
('b052', 'えのき'),
('b052', 'えのきだけ'),
('b052', 'きのこ'),
('b052', 'エノキ'),
('b052', 'キノコ'),
('b052', '野菜'),
('b053', 'もろへいや'),
('b053', 'モロヘイヤ'),
('b053', '野菜'),
('b054', 'いも'),
('b054', 'やまいも'),
('b054', '山芋'),
('b054', '芋'),
('b054', '野菜'),
('b055', 'えりんぎ'),
('b055', 'きのこ'),
('b055', 'エリンギ'),
('b055', 'キノコ'),
('b055', '野菜'),
('b056', 'れんこん'),
('b056', 'レンコン'),
('b056', '蓮根'),
('b056', '野菜'),
('b057', 'みずな'),
('b057', 'ミズナ'),
('b057', '水菜'),
('b057', '野菜'),
('b058', 'みょうが'),
('b058', 'ミョウガ'),
('b058', '野菜'),
('b059', 'るっこら'),
('b059', 'ルッコラ'),
('b059', '野菜'),
('b060', 'たけのこ'),
('b060', 'タケノコ'),
('b060', '筍'),
('b060', '野菜'),
('b061', 'さやいんげん'),
('b061', '野菜'),
('b062', 'さやえんどう'),
('b062', '野菜'),
('b063', 'わけぎ'),
('b063', 'ワケギ'),
('b063', '分葱'),
('b063', '野菜'),
('b064', 'あしたば'),
('b064', 'アシタバ'),
('b064', '明日葉'),
('b064', '野菜'),
('b065', 'えしゃろっと'),
('b065', 'エシャロット'),
('b065', '野菜'),
('b066', 'みず'),
('b066', '水'),
('b066', '飲料'),
('b067', 'りょくちゃ'),
('b067', '緑茶'),
('b067', '飲料'),
('b068', 'こうちゃ'),
('b068', '紅茶'),
('b068', '飲料'),
('b069', 'うーろんちゃ'),
('b069', 'ウーロン茶'),
('b069', '烏龍茶'),
('b069', '飲料'),
('b070', 'はぶちゃ'),
('b070', 'ハブ茶'),
('b070', '飲料'),
('b071', 'むぎちゃ'),
('b071', '飲料'),
('b071', '麦茶'),
('b072', 'げんまいちゃ'),
('b072', '玄米茶'),
('b072', '飲料'),
('b073', 'ほうじちゃ'),
('b073', 'ほうじ茶'),
('b073', '飲料'),
('b074', 'ぎょくろ'),
('b074', '玉露'),
('b074', '飲料'),
('b075', 'ぷーあるちゃ'),
('b075', 'プーアル茶'),
('b075', '飲料'),
('b076', 'じゃすみんちゃ'),
('b076', 'ジャスミン茶'),
('b076', '飲料'),
('b077', 'まてちゃ'),
('b077', 'マテ茶'),
('b077', '飲料'),
('b078', 'はとむぎちゃ'),
('b078', 'ハトムギ茶'),
('b078', '飲料'),
('b079', 'るいぼすてぃー'),
('b079', 'ルイボスティー'),
('b079', '飲料'),
('b080', 'こーひー'),
('b080', 'コーヒー'),
('b080', '飲料'),
('b081', 'ここあ'),
('b081', 'ココア'),
('b081', '飲料'),
('b082', 'じゅーす'),
('b082', 'ジュース'),
('b082', '飲料'),
('b083', 'たんさんいんりょう'),
('b083', '炭酸飲料'),
('b083', '飲料'),
('b084', 'すぽーつどりんく'),
('b084', 'スポーツドリンク'),
('b084', '飲料'),
('b085', 'にゅうせいいんりょう'),
('b085', '乳性飲料'),
('b085', '飲料'),
('b086', 'えいようどりんく'),
('b086', '栄養ドリンク'),
('b086', '飲料'),
('b087', 'えなじーどりんく'),
('b087', 'エナジードリンク'),
('b087', '飲料'),
('b088', 'とうにゅう'),
('b088', '豆乳'),
('b088', '飲料'),
('b089', 'あおじる'),
('b089', '青汁'),
('b089', '飲料'),
('b090', 'すむーじー'),
('b090', 'スムージー'),
('b090', '飲料'),
('b091', 'りきゅーる'),
('b091', 'リキュール'),
('b091', '飲料'),
('b092', 'にほんしゅ'),
('b092', '日本酒'),
('b092', '飲料'),
('b093', 'しょうちゅう'),
('b093', '焼酎'),
('b093', '飲料'),
('b094', 'ぶらんでー'),
('b094', 'ブランデー'),
('b094', '飲料'),
('b095', 'うぃすきー'),
('b095', 'ウィスキー'),
('b095', '飲料'),
('b096', 'びーる'),
('b096', 'ビール'),
('b096', '飲料'),
('b097', 'わいん'),
('b097', 'ワイン'),
('b097', '飲料'),
('b098','あまざけ'),
('b098','甘酒'),
('b098','飲料'),
('b099','うめしゅ'),
('b099','梅酒'),
('b099','飲料'),
('b100', 'ちゅーはい'),
('b100', 'チューハイ'),
('b100', '飲料'),
('b101', 'とりももにく'),
('b101', '肉'),
('b101', '鶏もも肉'),
('b101', '鶏肉'),
('b102', 'とりむねにく'),
('b102', '肉'),
('b102', '鶏むね肉'),
('b102', '鶏肉'),
('b103', 'とりささみ'),
('b103', '肉'),
('b103', '鶏ささみ'),
('b103', '鶏肉'),
('b104', 'とりてばにく'),
('b104', '肉'),
('b104', '鶏手羽肉'),
('b104', '鶏肉'),
('b105', 'すなぎも'),
('b105', '砂肝'),
('b105', '肉'),
('b105', '鶏肉'),
('b106', 'とりればー'),
('b106', '肉'),
('b106', '鶏レバー'),
('b106', '鶏肉'),
('b107', 'とりかわ'),
('b107', '肉'),
('b107', '鶏皮'),
('b107', '鶏肉'),
('b108', 'とりひきにく'),
('b108', '肉'),
('b108', '鶏ひき肉'),
('b108', '鶏肉'),
('b109', 'ぶたろーすにく'),
('b109', '肉'),
('b109', '豚ロース肉'),
('b109', '豚肉'),
('b109', 'ぶたかたろーすにく'),
('b110', '肉'),
('b110', '豚肉'),
('b110', '豚肩ロース肉'),
('b111', 'ぶたももにく'),
('b111', '肉'),
('b111', '豚もも肉'),
('b111', '豚肉'),
('b112', 'ぶたばらにく'),
('b112', '肉'),
('b112', '豚バラ肉'),
('b112', '豚肉'),
('b113', 'ぶたひれにく'),
('b113', '肉'),
('b113', '豚ヒレ肉'),
('b113', '豚肉'),
('b114', 'ぶたればー'),
('b114', '肉'),
('b114', '豚レバー'),
('b114', '豚肉'),
('b115', 'ぶたもつ'),
('b115', '肉'),
('b115', '豚もつ'),
('b115', '豚肉'),
('b116', 'とんとろ'),
('b116', 'トントロ'),
('b116', '肉'),
('b116', '豚肉'),
('b117', 'とんそく'),
('b117', '肉'),
('b117', '豚肉'),
('b117', '豚足'),
('b118', 'ぶたかたにく'),
('b118', '肉'),
('b118', '豚肉'),
('b118', '豚肩肉'),
('b119', 'ぶたひきにく'),
('b119', '肉'),
('b119', '豚ひき肉'),
('b119', '豚肉'),
('b120', 'ぎゅうひれにく'),
('b120', '牛ヒレ肉'),
('b120', '牛肉'),
('b120', '肉'),
('b121', 'ぎゅうばらにく'),
('b121', '牛バラ肉'),
('b121', '牛肉'),
('b121', '肉'),
('b122', 'ぎゅうももにく'),
('b122', '牛もも肉'),
('b122', '牛肉'),
('b122', '肉'),
('b123', 'ぎゅうろーすにく'),
('b123', '牛ロース肉'),
('b123', '牛肉'),
('b123', '肉'),
('b124', 'ぎゅうたん'),
('b124', '牛タン'),
('b124', '牛肉'),
('b124', '肉'),
('b125', 'ぎゅうればー'),
('b125', '牛レバー'),
('b125', '牛肉'),
('b125', '肉'),
('b126', 'ぎゅうひきにく'),
('b126', '牛ひき肉'),
('b126', '牛肉'),
('b126', '肉'),
('b127', 'らむにく'),
('b127', 'ラム肉'),
('b127', 'ひつじ'),
('b127', '羊'),
('b127', '肉'),
('b128', 'かもにく'),
('b128', '肉'),
('b128', '鴨肉'),
('b129', 'ばにく'),
('b129', '肉'),
('b129', '馬肉'),
('b130', 'べーこん'),
('b130', 'ベーコン'),
('b130', '肉'),
('b131', 'はむ'),
('b131', 'ハム'),
('b131', '肉'),
('b132', 'そーせーじ'),
('b132', 'ソーセージ'),
('b132', '肉'),
('b133', 'ちゃーしゅー'),
('b133', 'チャーシュー'),
('b133', '肉'),
('b134', 'なまはむ'),
('b134', '生ハム'),
('b134', '肉'),
('b135', 'さらみ'),
('b135', 'サラミ'),
('b135', '肉'),
('b136', 'すぱむ'),
('b136', 'スパム'),
('b136', '肉'),
('b137', 'こんびーふ'),
('b137', 'コンビーフ'),
('b137', '肉'),
('b138', 'ぱんちぇった'),
('b138', 'パンチェッタ'),
('b138', '肉'),
('b139', 'びーふじゃーきー'),
('b139', 'ビーフジャーキー'),
('b139', '肉'),
('b140','たまご'),
('b140','卵'),
('b140','肉'),
('b141','牛乳'),
('b141','ぎゅうにゅう'),
('b141','乳製品'),
('b141','肉'),
('b142','よーぐると'),
('b142','ヨーグルト'),
('b142','乳製品'),
('b142','肉'),
('b143','くりーむ'),
('b143','クリーム'),
('b143','乳製品'),
('b143','肉'),
('b144','ぷろせすちーず'),
('b144','プロセスチーズ'),
('b144','チーズ'),
('b144','乳製品'),
('b144','肉'),
('b145','すらいすちーず'),
('b145','スライスチーズ'),
('b145','チーズ'),
('b145','乳製品'),
('b145','肉'),
('b146','粉チーズ'),
('b146','こなちーず'),
('b146','チーズ'),
('b146','乳製品'),
('b146','肉'),
('b147','くりーむちーず'),
('b147','クリームチーズ'),
('b147','チーズ'),
('b147','乳製品'),
('b147','肉'),
('b148','モッツァレラチーズ'),
('b148','もっつぁれらちーず'),
('b148','チーズ'),
('b148','乳製品'),
('b148','肉'),
('b149','カマンベールチーズ'),
('b149','かまんべーるちーず'),
('b149','チーズ'),
('b149','乳製品'),
('b149','肉'),
('b150','ちーず'),
('b150','チーズ'),
('b150','乳製品'),
('b150','肉'),
('b151','さわーくりーむ'),
('b151','サワークリーム'),
('b151','乳製品'),
('b151','肉'),
('b152','うずらの卵'),
('b152','うずらのたまご'),
('b152','肉'),
('b153', 'あさり'),
('b153', 'アサリ'),
('b153', '魚介'),
('b153', '貝'),
//////////////////////////////////
('b139', 'しじみ'),
('b139', 'シジミ'),
('b139', '魚介'),
('b139', '貝'),
('b140', 'はまぐり'),
('b140', 'ハマグリ'),
('b140', '魚介'),
('b140', '貝'),
('b141', 'ほたて'),
('b141', 'ホタテ'),
('b141', '魚介'),
('b141', '貝'),
('b142', 'かき'),
('b142', 'カキ'),
('b142', '牡蠣'),
('b142', '魚介'),
('b142', '貝'),
('b143', 'むーるがい'),
('b143', 'ムール貝'),
('b143', '魚介'),
('b143', '貝'),
('b144', 'あわび'),
('b144', 'アワビ'),
('b144', '魚介'),
('b144', '貝'),
('b145', 'さざえ'),
('b145', 'サザエ'),
('b145', '魚介'),
('b145', '貝'),
('b146', 'つぶがい'),
('b146', 'つぶ貝'),
('b146', '魚介'),
('b146', '貝'),
('b147', 'あかがい'),
('b147', '赤貝'),
('b147', '魚介'),
('b147', '貝'),
('b148', 'ばいがい'),
('b148', 'ばい貝'),
('b148', '魚介'),
('b148', '貝'),
('b149', 'かいばしら'),
('b149', '貝柱'),
('b149', '魚介'),
('b149', '貝'),
('b150', 'さけ'),
('b150', 'サケ'),
('b150', '魚介'),
('b150', '鮭'),
('b151', 'さば'),
('b151', 'サバ'),
('b151', '魚介'),
('b151', '鯖'),
('b152', 'まぐろ'),
('b152', 'マグロ'),
('b152', '魚介'),
('b152', '鮪'),
('b153', 'さーもん'),
('b153', 'サーモン'),
('b153', '魚介'),
('b154', 'ぶり'),
('b154', 'ブリ'),
('b154', '魚介'),
('b154', '鰤'),
('b155', 'たら'),
('b155', 'タラ'),
('b155', '魚介'),
('b155', '鱈'),
('b156', 'たい'),
('b156', 'タイ'),
('b156', '魚介'),
('b156', '鯛'),
('b157', 'かつお'),
('b157', 'カツオ'),
('b157', '魚介'),
('b157', '鰹'),
('b158', 'かじき'),
('b158', 'カジキ'),
('b158', '梶木'),
('b158', '魚介'),
('b159', 'さんま'),
('b159', 'サンマ'),
('b159', '秋刀魚'),
('b159', '魚介'),
('b160', 'いわし'),
('b160', 'イワシ'),
('b160', '魚介'),
('b160', '鰯'),
('b161', 'あじ'),
('b161', 'アジ'),
('b161', '魚介'),
('b161', '鯵'),
('b162', 'ししゃも'),
('b162', 'シシャモ'),
('b162', '柳葉魚'),
('b162', '魚介'),
('b163', 'かれい'),
('b163', 'カレイ'),
('b163', '魚介'),
('b164', 'ひらめ'),
('b164', 'ヒラメ'),
('b164', '魚介'),
('b165', 'すずき'),
('b165', 'スズキ'),
('b165', '魚介'),
('b166', 'ほっけ'),
('b166', 'ホッケ'),
('b166', '魚介'),
('b167', 'きす'),
('b167', 'キス'),
('b167', '魚介'),
('b168', 'むつ'),
('b168', 'ムツ'),
('b168', '魚介'),
('b169', 'たちうお'),
('b169', 'タチウオ'),
('b169', '太刀魚'),
('b169', '魚介'),
('b170', 'きんき'),
('b170', 'キンキ'),
('b170', '魚介'),
('b171', 'にしん'),
('b171', 'ニシン'),
('b171', '魚介'),
('b172', 'めばる'),
('b172', 'メバル'),
('b172', '魚介'),
('b173', 'はも'),
('b173', 'ハモ'),
('b173', '魚介'),
('b174', 'かわはぎ'),
('b174', 'カワハギ'),
('b174', '魚介'),
('b175', 'わかさぎ'),
('b175', 'ワカサギ'),
('b175', '魚介'),
('b176', 'うなぎ'),
('b176', 'ウナギ'),
('b176', '魚介'),
('b176', '鰻'),
('b177', 'あなご'),
('b177', 'アナゴ'),
('b177', '穴子'),
('b177', '魚介'),
('b178', 'いか'),
('b178', 'イカ'),
('b178', '魚介'),
('b179', 'たこ'),
('b179', 'タコ'),
('b179', '魚介'),
('b180', 'えび'),
('b180', 'エビ'),
('b180', '海老'),
('b180', '魚介'),
('b181', 'あまえび'),
('b181', 'アマエビ'),
('b181', '甘海老'),
('b181', '魚介'),
('b182', 'いせえび'),
('b182', 'イセエビ'),
('b182', '伊勢海老'),
('b182', '魚介'),
('b183', 'かに'),
('b183', 'カニ'),
('b183', '蟹'),
('b183', '魚介'),
('b184', 'しらす'),
('b184', 'シラス'),
('b184', '魚介'),
('b185', 'たらこ'),
('b185', 'タラコ'),
('b185', '魚介'),
('b186', 'いくら'),
('b186', 'イクラ'),
('b186', '魚介'),
('b187', 'とびっこ'),
('b187', 'トビッコ'),
('b187', '魚介'),
('b188', 'うに'),
('b188', 'ウニ'),
('b188', '魚介'),
('b189', 'かずのこ'),
('b189', 'カズノコ'),
('b189', '数の子'),
('b189', '魚介'),
('b190', 'しらこ'),
('b190', 'シラコ'),
('b190', '白子'),
('b190', '魚介'),
('b191', 'くらげ'),
('b191', '海月'),
('b191', '魚介'),
('b192', 'ほたるいか'),
('b192', 'ホタルイカ'),
('b192', '魚介'),
('b193', 'ちくわ'),
('b193', '竹輪'),
('b193', '魚介'),
('b194', 'かまぼこ'),
('b194', '魚介'),
('b195', 'はんぺん'),
('b195', '魚介'),
('b196', 'ぎょにくそーせーじ'),
('b196', '魚介'),
('b196', '魚肉ソーセージ'),
('b197', 'さくらえび'),
('b197', 'サクラエビ'),
('b197', '桜海老'),
('b197', '魚介'),
('b198','魚介'),
('b198','すじこ'),
('b198','筋子'),
('b199','魚介'),
('b199','明太子'),
('b199','めんたいこ'),
('b200','魚介'),
('b200','しーふーどみっくす'),
('b200','シーフードミックス'),
('b201','魚介'),
('b201','ツナ缶'),
('b200','つなかん'),
('b202','魚介'),
('b202','さばの水煮缶'),
('b202','さばのみずにかん'),
('b203','デザート'),
('b203','イチゴ'),
('b203','いちご'),
('b204','デザート'),
('b204','キウイフルーツ'),
('b204','きういふるーつ'),
('b205','デザート'),
('b205','スイカ'),
('b205','すいか'),
('b206','デザート'),
('b206','ばなな'),
('b206','バナナ'),
('b207','デザート'),
('b207','みかん'),
('b207','ミカン'),
('b208','デザート'),
('b208','りんご'),
('b208','リンゴ'),
('b209','デザート'),
('b209','おれんじ'),
('b209','オレンジ'),
('b210','デザート'),
('b210','まんごー'),
('b210','マンゴー'),
('b211','デザート'),
('b211','ぱいなっぷる'),
('b211','パイナップル'),
('b212','デザート'),
('b212','めろん'),
('b212','メロン'),
('b213','デザート'),
('b213','もも'),
('b213','桃'),
('b214','デザート'),
('b214','ぶどう'),
('b214','ブドウ'),
('b215','デザート'),
('b215','ぐれーぷふるーつ'),
('b215','グレープフルーツ'),
('b216','デザート'),
('b216','ちょこれーと'),
('b216','チョコレート'),
('b217','デザート'),
('b217','あいすくりーむ'),
('b217','アイスクリーム'),
('b218','デザート'),
('b218','ぶるーべりー'),
('b218','ブルーベリー'),
('b219','デザート'),
('b219','あんこ'),
('b219','餡子'),
('b220','デザート'),
('b220','さくらんぼ'),
('b220','サクランボ'),
('b221','デザート'),
('b221','柿'),
('b221','かき'),
('b222','デザート'),
('b222','梨'),
('b222','なし'),
('b223','デザート'),
('b223','あんず'),
('b223','杏'),
('b224','デザート'),
('b224','くらんべりー'),
('b224','くらんべりー'),
('b225','デザート'),
('b225','プルーン'),
('b225','ぷるーん'),
('b226','デザート'),
('b226','ようなし'),
('b226','洋ナシ'),
('b227','デザート'),
('b227','ぷらむ'),
('b227','プラム'),
('b228','デザート'),
('b228','くり'),
('b228','栗'),
('b229','デザート'),
('b229','レーズン'),
('b229','れーずん'),
('b230','デザート'),
('b230','ナッツ'),
('b230','なっつ'),
('b231','デザート'),
('b231','けーき'),
('b231','ケーキ'),
('b232','デザート'),
('b232','しゅーくりーむ'),
('b232','シュークリーム'),
('b233','デザート'),
('b233','ぷりん'),
('b233','プリン'),
('b234','デザート'),
('b234','れもん'),
('b234','レモン'),
('b235','デザート'),
('b235','ざくろ'),
('b235','柘榴'),
('b236','デザート'),
('b236','びわ'),
('b236','ビワ'),
('b237','デザート'),
('b237','ぜりー'),
('b237','ゼリー'),
('b238','デザート'),
('b238','くらっかー'),
('b238','クラッカー'),
('b239','デザート'),
('b239','水あめ'),
('b239','みずあめ'),
('b240','調味料'),
('b240','塩'),
('b240','しお'),
('b241','調味料'),
('b241','砂糖'),
('b241','さとう'),
('b242','調味料'),
('b242','しょうゆ'),
('b242','醤油'),
('b243','調味料'),
('b243','りょうりしゅ'),
('b243','料理酒'),
('b244','調味料'),
('b244','味醂'),
('b244','みりん'),
('b245','調味料'),
('b245','す'),
('b245','酢'),
('b245','お酢'),
('b245','おす'),
('b246','調味料'),
('b246','みそ'),
('b246','味噌'),
('b247','調味料'),
('b247','ばたー'),
('b247','バター'),
('b248','調味料'),
('b248','まーがりん'),
('b248','マーガリン'),
('b249','調味料'),
('b249','サラダ油'),
('b249','さらだあぶら'),
('b249','さらだゆ'),
('b250','調味料'),
('b250','おーりぶおいる'),
('b250','オリーブオイル'),
('b251','調味料'),
('b251','ごまあぶら'),
('b251','ごま油'),
('b252','調味料'),
('b252','まよねーず'),
('b252','マヨネーズ'),
('b253','調味料'),
('b253','けちゃっぷ'),
('b253','ケチャップ'),
('b254','調味料'),
('b254','どれっしんぐ'),
('b254','ドレッシング'),
('b255','調味料'),
('b255','しおこしょう'),
('b255','塩コショウ'),
('b255','塩胡椒'),
('b256','調味料'),
('b256','めんつゆ'),
('b257','調味料'),
('b257','うすたーそーす'),
('b257','ウスターソース'),
('b258','調味料'),
('b258','蜂蜜'),
('b258','はちみつ'),
('b258','ハチミツ'),
('b259','調味料'),
('b259','わふうだし'),
('b259','和風だし'),
('b260','調味料'),
('b260','中華だし'),
('b260','ちゅうかだし'),
('b261','調味料'),
('b261','うま味調味料'),
('b261','うまみちょうみりょう'),
('b262','調味料'),
('b262','おろしにんにく'),
('b263','調味料'),
('b263','おろししょうが'),
('b264','調味料'),
('b264','おいすたーそーす'),
('b264','オイスターソース'),
('b265','調味料'),
('b265','ぶらっくぺっぱー'),
('b265','ブラックペッパー'),
('b265','こしょう'),
('b265','コショウ'),
('b265','胡椒'),
('b265','スパイス'),
('b266','調味料'),
('b266','コンソメ'),
('b266','こんそめ'),
('b267','調味料'),
('b267','わさび'),
('b267','ワサビ'),
('b268','調味料'),
('b268','からし'),
('b268','辛子'),
('b269','調味料'),
('b269','ぽんず'),
('b269','ポン酢'),
('b270','調味料'),
('b270','じゃむ'),
('b270','ジャム'),
('b271','調味料'),
('b271','ぴーなっつばたー'),
('b271','ピーナッツバター'),
('b272','調味料'),
('b272','ばにらえっせんす'),
('b272','バニラエッセンス'),
('b273','調味料'),
('b273','かれーこ'),
('b273','カレー粉'),
('b274','調味料'),
('b274','ぱせり'),
('b274','パセリ'),
('b274','スパイス'),
('b275','調味料'),
('b275','ばじる'),
('b275','バジル'),
('b275','スパイス'),
('b276','調味料'),
('b276','しなもん'),
('b276','シナモン'),
('b276','スパイス'),
('b277','調味料'),
('b277','ろーりえ'),
('b277','ローリエ'),
('b277','スパイス'),
('b278','調味料'),
('b278','なつめぐ'),
('b278','ナツメグ'),
('b278','スパイス'),
('b279','調味料'),
('b279','とうがらし'),
('b279','唐辛子'),
('b279','スパイス'),
('b280','調味料'),
('b280','ろーずまりー'),
('b280','ローズマリー'),
('b280','スパイス'),
('b281','調味料'),
('b281','さんしょう'),
('b281','山椒'),
('b281','スパイス'),
('b282','調味料'),
('b282','クミン'),
('b282','くみん'),
('b282','スパイス'),
('b283','調味料'),
('b283','たーめりっく'),
('b283','ターメリック'),
('b283','スパイス'),
('b284','調味料'),
('b284','さふらん'),
('b284','サフラン'),
('b284','スパイス'),
('b285','調味料'),
('b285','おーるすぱいす'),
('b285','オールスパイス'),
('b285','スパイス'),
('b286','調味料'),
('b286','がーりっくぱうだー'),
('b286','ガーリックパウダー'),
('b286','スパイス'),
('b287','調味料'),
('b287','おれがの'),
('b287','オレガノ'),
('b287','スパイス'),
('b288','調味料'),
('b288','たいむ'),
('b288','タイム'),
('b288','スパイス'),
('b289','調味料'),
('b289','はっかく'),
('b289','八角'),
('b289','スパイス'),
('b290','調味料'),
('b290','ちりぱうだー'),
('b290','チリパウダー'),
('b290','スパイス'),
('b291','調味料'),
('b291','パプリカパウダー'),
('b291','ぱぷりかぱうだー'),
('b291','スパイス'),
('b292','調味料'),
('b292','くろーぶ'),
('b292','クローブ'),
('b292','スパイス'),
('b293','調味料'),
('b293','かるだもん'),
('b293','カルダモン'),
('b293','スパイス'),
('b294','調味料'),
('b294','五香粉'),
('b294','ウーシャンフェン'),
('b294','うーしゃんふぇん'),
('b294','スパイス'),
('b295','調味料'),
('b295','ほわいとぺっぱー'),
('b295','ホワイトペッパー'),
('b295','スパイス'),
('b296','調味料'),
('b296','こりあんだー'),
('b296','コリアンダー'),
('b296','スパイス'),
('b297','調味料'),
('b297','めーぷるしろっぷ'),
('b297','メープルシロップ'),
('b298','調味料'),
('b298','ますたーど'),
('b298','マスタード'),
('b299','調味料'),
('b299','らーゆ'),
('b299','ラー油'),
('b300','調味料'),
('b300','こちゅじゃん'),
('b300','コチュジャン'),
('b301','調味料'),
('b301','ゆずこしょう'),
('b301','柚子胡椒'),
('b302','調味料'),
('b302','ぎょしょう'),
('b302','魚醤'),
('b303','調味料'),
('b303','ばるさみこす'),
('b303','バルサミコ酢'),
('b304','調味料'),
('b304','たばすこ'),
('b304','タバスコ'),
('b305','調味料'),
('b305','デミグラスソース'),
('b305','でみぐらすそーす'),
('b306','調味料'),
('b306','ちりそーす'),
('b306','チリソース'),
('b307','調味料'),
('b307','しおこうじ'),
('b307','塩こうじ'),
('b308','調味料'),
('b308','たれ'),
('b309','調味料'),
('b309','しちみとうがらし'),
('b309','七味唐辛子'),
('b310','調味料'),
('b310','しろだし'),
('b310','白だし'),
('b311','調味料'),
('b311','とうばんじゃん'),
('b311','豆板醤'),
('b312','調味料'),
('b312','てんめんじゃん'),
('b312','甜麺醤'),
('b313','調味料'),
('b313','とうちじゃん'),
('b313','豆鼓醤'),
('b314','調味料'),
('b314','一味唐辛子'),
('b314','いちみとうがらし'),
('b315','調味料'),
('b315','かれーるう'),
('b315','カレールウ'),
('b316','調味料'),
('b316','れもんじる'),
('b316','レモン汁'),
('b317','調味料'),
('b317','ぎゅうし'),
('b317','牛脂'),
('b318','調味料'),
('b318','らーど'),
('b318','ラード'),
('b319','調味料'),
('b319','とさず'),
('b319','土佐酢'),
('b320','主食'),
('b320','米'),
('b320','こめ'),
('b321','主食'),
('b321','うどん'),
('b322','主食'),
('b322','ぱすた'),
('b322','パスタ'),
('b323','主食'),
('b323','ショートパスタ'),
('b323','しょーとぱすた'),
('b324','主食'),
('b324','そば'),
('b325','主食'),
('b325','食パン'),
('b325','しょくぱん'),
('b326','主食'),
('b326','ぱん'),
('b326','パン'),
('b327','主食'),
('b327','ふらんすぱん'),
('b327','フランスパン'),
('b328','主食'),
('b328','かしぱん'),
('b328','菓子パン'),
('b329','主食'),
('b329','もち'),
('b329','餅'),
('b330','主食'),
('b330','そうめん'),
('b330','素麺'),
('b331','主食'),
('b331','はるさめ'),
('b331','春雨'),
('b332','主食'),
('b332','薄力粉'),
('b332','はくりきこ'),
('b333','主食'),
('b333','かたくりこ'),
('b333','片栗粉'),
('b334','主食'),
('b334','ぱんこ'),
('b334','パン粉'),
('b335','主食'),
('b335','おこのみやきこ'),
('b335','お好み焼き粉'),
('b336','主食'),
('b336','ほっとけーきみっくす'),
('b336','ホットケーキミックス'),
('b337','主食'),
('b337','べーきんぐぱうだー'),
('b337','ベーキングパウダー'),
('b338','主食'),
('b338','もちごめ'),
('b338','もち米'),
('b339','主食'),
('b339','げんまい'),
('b339','玄米'),
('b340','主食'),
('b340','どらいいーすと'),
('b340','ドライイースト'),
('b341','主食'),
('b341','ぜらちん'),
('b341','ゼラチン'),
('b342','主食'),
('b342','こめこ'),
('b342','米粉'),
('b343','主食'),
('b343','こーんすたーち'),
('b343','コーンスターチ'),
('b344','主食'),
('b344','てんぷらこ'),
('b344','天ぷら粉'),
('b345','主食'),
('b345','しらたまこ'),
('b345','白玉粉'),
('b346','主食'),
('b346','じょうしんこ'),
('b346','上新粉'),
('b347','主食'),
('b347','ざっこくまい'),
('b347','雑穀米'),
('b348','主食'),
('b348','くずこ'),
('b348','くず粉'),
('b349','主食'),
('b349','おしむぎ'),
('b349','押し麦'),
('b350','主食'),
('b350','そばこ'),
('b350','そば粉'),
('b351','主食'),
('b351','玄米粉'),
('b351','げんまいこ'),
('b352','主食'),
('b352','めん'),
('b352','麺'),
('b353','主食'),
('b353','ちゅうりきこ'),
('b353','中力粉'),
('b354','主食'),
('b354','きょうりきこ'),
('b354','強力粉'),
('b355','その他'),
('b355','とうふ'),
('b355','豆腐'),
('b356','その他'),
('b356','なっとう'),
('b356','納豆'),
('b357','その他'),
('b357','あぶらあげ'),
('b357','油揚げ'),
('b358','その他'),
('b358','あつあげ'),
('b358','厚揚げ'),
('b359','その他'),
('b359','まめ'),
('b359','豆'),
('b360','その他'),
('b360','だいず'),
('b360','大豆'),
('b361','その他'),
('b361','きな粉'),
('b361','きなこ'),
('b362','その他'),
('b362','おから'),
('b363','その他'),
('b363','小豆'),
('b363','あずき'),
('b364','その他'),
('b364','こうやどうふ'),
('b364','高野豆腐'),
('b365','その他'),
('b365','ゆば'),
('b365','湯葉'),
('b366','その他'),
('b366','ここなっつみるく'),
('b366','ココナッツミルク'),
('b367','その他'),
('b367','すきむみるく'),
('b367','スキムミルク'),
('b368','その他'),
('b368','こんにゃく'),
('b368','蒟蒻'),
('b369','その他'),
('b369','しらたき'),
('b370','その他'),
('b370','くろごま'),
('b370','黒ごま'),
('b371','その他'),
('b371','しろごま'),
('b371','白ごま'),
('b372','その他'),
('b372','しおこんぶ'),
('b372','塩昆布'),
('b373','その他'),
('b373','てんかす'),
('b373','天かす'),
('b374','その他'),
('b374','とろろこんぶ'),
('b374','とろろ昆布'),
('b375','その他'),
('b375','青のり'),
('b375','あおのり'),
('b376','その他'),
('b376','きむち'),
('b376','キムチ'),
('b377','その他'),
('b377','ざーさい'),
('b377','ザーサイ'),
('b378','その他'),
('b378','めんま'),
('b378','メンマ'),
('b379','その他'),
('b379','まっちゃ'),
('b379','抹茶'),
('b380','その他'),
('b380','おりーぶ'),
('b380','オリーブ'),
('b381','その他'),
('b381','ほわいとそーす'),
('b381','ホワイトソース'),
('b382','その他'),
('b382','がんも'),
('b383','その他'),
('b383','さつまあげ'),
('b383','さつま揚げ'),
('b384','その他'),
('b384','ぎょうざの皮'),
('b384','餃子の皮'),
('b384','ぎょうざのかわ'),
('b385','その他'),
('b385','ライスペーパー'),
('b385','らいすぺーぱー'),
('b386','その他'),
('b386','ところてん'),
('b387','その他'),
('b387','まつのみ'),
('b387','松の実'),
('b388','その他'),
('b388','クコの実'),
('b388','くこのみ'),
('b389','その他'),
('b389','ゆかり'),
('b390','その他'),
('b390','かんぴょう'),
('b391','その他'),
('b391','ふ'),
('b391','麩'),
('b392','その他'),
('b392','酒粕'),
('b392','さけかす'),
('b393','その他'),
('b393','こーひーまめ'),
('b393','コーヒー豆'),
('b394','その他'),
('b394','梅'),
('b394','うめ'),
('b395','その他'),
('b395','うめぼし'),
('b395','梅干し'),
//
('b398','魚介'),
('b398','めかぶ'),
('b399','魚介'),
('b399','わかめ'),
('b399','ワカメ'),
('b400','魚介'),
('b400','ひじき'),
('b401','魚介'),
('b401','のり'),
('b402','魚介'),
('b402','かつお節'),
('b402','鰹節'),
('b402','かつおぶし'),
('b403','魚介'),
('b403','こんぶ'),
('b403','昆布'),
('b404','魚介'),
('b404','あんちょび'),
('b404','アンチョビ'),
('b405','魚介'),
('b405','もずく'),











--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `search_index`
--
ALTER TABLE `search_index`
  ADD PRIMARY KEY (`goods_id`,`keyword`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
