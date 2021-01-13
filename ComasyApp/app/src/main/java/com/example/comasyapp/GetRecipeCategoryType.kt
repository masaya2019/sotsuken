package com.example.comasyapp

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class GetRecipeCategoryType {

    // =====================================
    // ランダムでカテゴリIDを返す（レシピタイトル用）
    // =====================================
    // 定番のカテゴリー（0~9）
    val classicCategory = listOf(
        // 人気メニュー
        "30",
        // 定番の肉料理
        "31",
        // 定番の魚料理
        "32",
        // お弁当
        "20",
        // 今日の献立
        "38",
        // 簡単料理・時短
        "36",
        // お菓子
        "21",
        // パスタ
        "15",
        // ご飯もの
        "14",
        // 汁物・スープ
        "17"
    )
    // 月ごとのカテゴリー(10～14)
    val janCategory = listOf(
        // 冬
        "55",
        // 行事・イベント
        "24",
        // 鍋料理
        "23",
        // おせち料理
        "49",
        // ななくさ粥（七草粥）
        "55-673"
    )
    val febCategory = listOf(
        // 冬
        "55",
        // 行事・イベント
        "24",
        // 鍋料理
        "23",
        // 恵方巻き
        "55-672",
        // バレンタイン
        "55-674"
    )
    val marCategory = listOf(
        // 春
        "52",
        // 行事・イベント
        "24",
        // ひな祭り
        "51",
        // お花見・春の行楽
        "52-661",
        // ホワイトデー
        "52-660"
    )
    val aprCategory = listOf(
        // 春
        "52",
        // 行事・イベント
        "24",
        // お花見・春の行楽
        "52-661",
        // 春の果物
        "34-692",
        // お疲れ気味の方
        "39-508"
    )
    val mayCategory = listOf(
        // 春
        "52",
        // 行事・イベント
        "24",
        // 子供の日
        "52-662",
        // 母の日
        "52-663",
        // 春の果物
        "34-692"
    )
    val junCategory = listOf(
        // 夏
        "53",
        // 行事・イベント
        "24",
        // 父の日
        "53-664",
        // 夏の果物
        "34-693",
        // お疲れ気味の方
        "39-508"
    )
    val julCategory = listOf(
        // 夏
        "53",
        // 行事・イベント
        "24",
        // 夏バテ対策
        "53-665",
        // お祭り
        "53-666",
        // 夏の果物
        "34-693"
    )
    val augCategory = listOf(
        // 夏
        "53",
        // 行事・イベント
        "24",
        // 夏バテ対策
        "53-665",
        // お祭り
        "53-666",
        // 夏の果物
        "34-693"
    )
    val sepCategory = listOf(
        // 秋
        "54",
        // 行事・イベント
        "24",
        // 秋の行楽・紅葉
        "54-669",
        // 十五夜・お月見
        "53-667",
        // 秋の果物
        "34-689"
    )
    val octCategory = listOf(
        // 秋
        "54",
        // 行事・イベント
        "24",
        // 秋の行楽・紅葉
        "54-669",
        // ハロウィン
        "54-668",
        // 秋の果物
        "34-689"
    )
    val novCategory = listOf(
        // 秋
        "54",
        // 行事・イベント
        "24",
        // 秋の行楽・紅葉
        "54-669",
        // 鍋料理
        "23",
        // 秋の果物
        "34-689"
    )
    val decCategory = listOf(
        // 冬
        "55",
        // 行事・イベント
        "24",
        // 鍋料理
        "23",
        // クリスマス
        "50",
        // 冬の果物
        "34-695"
    )

    // ランダムでカテゴリIDを返す（レシピタイトル用）
    fun getrecipeId(): String {
        // どのレシピカテゴリーを採用するか（0～14）
        val randomNumber = (0..14).random()

        Log.e("ランダムナンバー", randomNumber.toString())

        //　返却値
        var returnCategoryType = ""

        // 定番のカテゴリー（0~9）の時
        return if (randomNumber < 10) {
            returnCategoryType = classicCategory[randomNumber]

            return returnCategoryType

            // 季節ごとのカテゴリー(10～14)の時
        } else {
            // 現在月の取得
            val month = getToday()

            Log.e("現在月", "${month}")
            // 月ごと
            when (month) {
                1 -> {
                    returnCategoryType = janCategory[randomNumber - 10]
                    return returnCategoryType
                }
                2 -> {
                    returnCategoryType = febCategory[randomNumber - 10]
                    return returnCategoryType
                }
                3 -> {
                    returnCategoryType = marCategory[randomNumber - 10]
                    return returnCategoryType
                }
                4 -> {
                    returnCategoryType = aprCategory[randomNumber - 10]
                    return returnCategoryType
                }
                5 -> {
                    returnCategoryType = mayCategory[randomNumber - 10]
                    return returnCategoryType
                }
                6 -> {
                    returnCategoryType = junCategory[randomNumber - 10]
                    return returnCategoryType
                }
                7 -> {
                    returnCategoryType = julCategory[randomNumber - 10]
                    return returnCategoryType
                }
                8 -> {
                    returnCategoryType = augCategory[randomNumber - 10]
                    return returnCategoryType
                }
                9 -> {
                    returnCategoryType = sepCategory[randomNumber - 10]
                    return returnCategoryType
                }
                10 -> {
                    returnCategoryType = octCategory[randomNumber - 10]
                    return returnCategoryType
                }
                11 -> {
                    returnCategoryType = novCategory[randomNumber - 10]
                    return returnCategoryType
                }
                12 -> {
                    returnCategoryType = decCategory[randomNumber - 10]
                    return returnCategoryType
                }
                else -> {
                    return "error"
                }
            }
        }
    }

    // ========================================
    // 季節のイベントのカテゴリIDを返す（トレンドタグ用）
    // ========================================
    // 季節のイベント名
    val seasonEventName = listOf(
        // 1月
        "おせち料理",
        // 2月
        "バレンタイン",
        // 3月
        "ホワイトデー",
        // 4月
        "お花見・春の行楽",
        // 5月
        "母の日",
        // 6月
        "父の日",
        // 7月
        "お祭り",
        // 8月
        "お祭り",
        // 9月
        "十五夜・お月見",
        // 10月
        "ハロウィン",
        // 11月
        "秋の行楽・紅葉",
        // 12月
        "クリスマス"
    )

    // 季節のイベントカテゴリID
    val seasonEventCategoryType = listOf(
        // 1月
        "49",
        // 2月
        "55-674",
        // 3月
        "52-660",
        // 4月
        "52-661",
        // 5月
        "52-663",
        // 6月
        "52-664",
        // 7月
        "53-666",
        // 8月
        "53-666",
        // 9月
        "53-667",
        // 10月
        "54-668",
        // 11月
        "54-669",
        // 12月
        "50"
    )

    // 季節のイベントのカテゴリIDを返す（トレンドタグ用）
    fun gettrendTagSeasonEvent(): Pair<String, String> {
        Log.e("月", getToday().toString() + seasonEventName[getToday() - 1] + seasonEventCategoryType[getToday() - 1])
        return Pair(seasonEventName[getToday() - 1], seasonEventCategoryType[getToday() - 1])
    }

    // =========================================
    // 旬の食材（野菜）のカテゴリIDを返す（トレンドタグ用）
    // =========================================
    // 1月の旬の食材（野菜）名/カテゴリID
    val janVegetableFood = listOf(
        "長ネギ（ねぎ）", "12-103-8",
        "ブロッコリー", "12-458",
        "カリフラワー", "12-103-34",
        "小松菜", "12-456",
        "にんじん", "12-95"
    )
    // 2月の旬の食材（野菜）名/カテゴリID
    val febVegetableFood = listOf(
        "長ネギ（ねぎ）", "12-103-8",
        "ブロッコリー", "12-458",
        "カリフラワー", "12-103-34",
        "小松菜", "12-456",
        "じゃがいも", "12-97",
        "にら", "12-103-4",
        "いちご", "34-692-1965"
    )
    // 3月の旬の食材（野菜）名/カテゴリID
    val marVegetableFood = listOf(
        "長ネギ（ねぎ）", "12-103-8",
        "ブロッコリー", "12-458",
        "カリフラワー", "12-103-34",
        "小松菜", "12-456",
        "じゃがいも", "12-97",
        "にら", "12-103-4",
        "いちご", "34-692-1965",
        "レタス", "12-100-2",
        "セロリ", "12-103-314",
        "アスパラ", "12-100-11"
    )
    // 4月の旬の食材（野菜）名/カテゴリID
    val aprVegetableFood = listOf(
        "じゃがいも", "12-97",
        "にら", "12-103-4",
        "いちご", "34-692-1965",
        "レタス", "12-100-2",
        "セロリ", "12-103-314",
        "アスパラ", "12-100-11",
        "かぶ", "12-102-16",
        "キャベツ", "12-98",
        "玉ねぎ", "12-96"
    )
    // 5月の旬の食材（野菜）名/カテゴリID
    val mayVegetableFood = listOf(
        "じゃがいも", "12-97",
        "レタス", "12-100-2",
        "アスパラ", "12-100-11",
        "かぶ", "12-102-16",
        "キャベツ", "12-98",
        "玉ねぎ", "12-96"
    )
    // 6月の旬の食材（野菜）名/カテゴリID
    val junVegetableFood = listOf(
        "アスパラ", "12-100-11",
        "いんげん", "12-101-22",
        "オクラ", "12-101-32",
        "かぼちゃ", "12-448",
        "ししとう", "12-101-1532",
        "とうもろこし", "12-101-422",
        "ピーマン", "12-101-30"
    )
    // 7月の旬の食材（野菜）名/カテゴリID
    val julVegetableFood = listOf(
        "いんげん", "12-101-22",
        "オクラ", "12-101-32",
        "かぼちゃ", "12-448",
        "ししとう", "12-101-1532",
        "レタス", "12-100-2",
        "とうもろこし", "12-101-422",
        "ピーマン", "12-101-30",
        "枝豆", "12-101-24",
        "きゅうり", "12-450",
        "トマト", "12-454",
        "なす", "12-447",
        "みょうが", "12-107-36"
    )
    // 8月の旬の食材（野菜）名/カテゴリID
    val augVegetableFood = listOf(
        "いんげん", "12-101-22",
        "オクラ", "12-101-32",
        "かぼちゃ", "12-448",
        "ししとう", "12-101-1532",
        "レタス", "12-100-2",
        "とうもろこし", "12-101-422",
        "ぶどう", "34-689-1983",
        "枝豆", "12-101-24",
        "きゅうり", "12-450",
        "トマト", "12-454",
        "なす", "12-447",
        "みょうが", "12-107-36"
    )
    // 9月の旬の食材（野菜）名/カテゴリID
    val sepVegetableFood = listOf(
        "いんげん", "12-101-22",
        "オクラ", "12-101-32",
        "かぼちゃ", "12-448",
        "じゃがいも", "12-97",
        "里芋", "12-103-308",
        "なす", "12-447",
        "セロリ", "12-103-314",
        "玉ねぎ", "12-96",
        "みょうが", "12-107-36",
        "栗", "34-689-1981",
        "しめじ", "12-105-76",
        "チンゲン菜", "12-102-319",
        "しいたけ", "12-105-75",
        "まいたけ", "12-105-77",
        "松茸", "12-105-337"
    )
    // 10月の旬の食材（野菜）名/カテゴリID
    val octVegetableFood = listOf(
        "じゃがいも", "12-97",
        "里芋", "12-103-308",
        "セロリ", "12-103-314",
        "玉ねぎ", "12-96",
        "栗", "34-689-1981",
        "しめじ", "12-105-76",
        "チンゲン菜", "12-102-319",
        "しいたけ", "12-105-75",
        "まいたけ", "12-105-77",
        "松茸", "12-105-337",
        "ごぼう", "12-455",
        "柿", "34-460",
        "かぶ", "12-102-16",
        "さつまいも", "12-452",
        "りんご", "34-688"
    )
    // 11月の旬の食材（野菜）名/カテゴリID
    val novVegetableFood = listOf(
        "柿", "34-460",
        "かぶ", "12-102-16",
        "カリフラワー", "12-103-34",
        "ごぼう", "12-455",
        "さつまいも", "12-452",
        "里芋", "12-103-308",
        "しめじ", "12-105-76",
        "大根", "12-449",
        "チンゲン菜", "12-102-319",
        "しいたけ", "12-105-75",
        "にんじん", "12-95",
        "長ネギ（ねぎ）", "12-103-8",
        "白菜", "12-453",
        "ブロッコリー", "12-458",
        "ほうれん草", "12-457",
        "松茸", "12-105-337",
        "りんご", "34-688",
        "れんこん", "12-102-15"
    )
    // 12月の旬の食材（野菜）名/カテゴリID
    val decVegetableFood = listOf(
        "長ネギ（ねぎ）", "12-103-8",
        "大根", "12-449",
        "白菜", "12-453",
        "ブロッコリー", "12-458",
        "カリフラワー", "12-103-34",
        "れんこん", "12-102-15",
        "小松菜", "12-456",
        "ごぼう", "12-455",
        "みかん", "34-695-1987",
        "ほうれん草", "12-457",
        "にんじん", "12-95",
        "里芋", "12-103-308"
    )

    // 旬の食材（野菜）のカテゴリIDを返す（トレンドタグ用）
    fun gettrendTagSeasonVegetableFood(): Pair<String, String> {

        // 現在月の取得
        val month = getToday()

        Log.e("現在月", "${month}")
        // 月ごと
        when (month) {
            1 -> {
                val randomEvenNumberm = (0..4).random() * 2
                return Pair(janVegetableFood[randomEvenNumberm], janVegetableFood[randomEvenNumberm + 1])
            }
            2 -> {
                val randomEvenNumberm = (0..6).random() * 2
                return Pair(febVegetableFood[randomEvenNumberm], febVegetableFood[randomEvenNumberm + 1])
            }
            3 -> {
                val randomEvenNumberm = (0..9).random() * 2
                return Pair(marVegetableFood[randomEvenNumberm], marVegetableFood[randomEvenNumberm + 1])
            }
            4 -> {
                val randomEvenNumberm = (0..8).random() * 2
                return Pair(aprVegetableFood[randomEvenNumberm], aprVegetableFood[randomEvenNumberm + 1])
            }
            5 -> {
                val randomEvenNumberm = (0..5).random() * 2
                return Pair(mayVegetableFood[randomEvenNumberm], mayVegetableFood[randomEvenNumberm + 1])
            }
            6 -> {
                val randomEvenNumberm = (0..6).random() * 2
                return Pair(junVegetableFood[randomEvenNumberm], junVegetableFood[randomEvenNumberm + 1])
            }
            7 -> {
                val randomEvenNumberm = (0..11).random() * 2
                return Pair(julVegetableFood[randomEvenNumberm], julVegetableFood[randomEvenNumberm + 1])
            }
            8 -> {
                val randomEvenNumberm = (0..11).random() * 2
                return Pair(augVegetableFood[randomEvenNumberm], augVegetableFood[randomEvenNumberm + 1])
            }
            9 -> {
                val randomEvenNumberm = (0..14).random() * 2
                return Pair(sepVegetableFood[randomEvenNumberm], sepVegetableFood[randomEvenNumberm + 1])
            }
            10 -> {
                val randomEvenNumberm = (0..14).random() * 2
                return Pair(octVegetableFood[randomEvenNumberm], octVegetableFood[randomEvenNumberm + 1])
            }
            11 -> {
                val randomEvenNumberm = (0..17).random() * 2
                return Pair(novVegetableFood[randomEvenNumberm], novVegetableFood[randomEvenNumberm + 1])
            }
            12 -> {
                val randomEvenNumberm = (0..11).random() * 2
                return Pair(decVegetableFood[randomEvenNumberm], decVegetableFood[randomEvenNumberm + 1])
            }
            else -> {
                return Pair("error", "error")
            }
        }
    }


    // =========================================
    // 旬の食材（魚介）のカテゴリIDを返す（トレンドタグ用）
    // =========================================
    // 1月の旬の食材（魚介）名/カテゴリID
    val janSeaFood = listOf(
        "あさり", "11-82-60",
        "まぐろ", "11-77",
        "カニ", "11-83",
        "ヒラメ", "11-78-1499",
        "ぶり", "11-74",
        "たら", "11-443"
    )
    // 2月の旬の食材（魚介）名/カテゴリID
    val febSeaFood = listOf(
        "あさり", "11-82-60",
        "まぐろ", "11-77",
        "カニ", "11-83"
    )
    // 3月の旬の食材（魚介）名/カテゴリID
    val marSeaFood = listOf(
        "あさり", "11-82-60",
        "ヒラメ", "11-78-1499"
    )
    // 4月の旬の食材（魚介）名/カテゴリID
    val aprSeaFood = listOf(
        "あさり", "11-82-60",
        "かつお", "11-78-324",
        "にしん", "11-78-1497",
        "ヒラメ", "11-78-1499",
        "さば", "11-72"
    )
    // 5月の旬の食材（魚介）名/カテゴリID
    val maySeaFood = listOf(
        "かつお", "11-78-324",
        "きす", "11-78-840",
        "にしん", "11-78-1497",
        "かれい", "11-78-323",
        "さば", "11-72"
    )
    // 6月の旬の食材（魚介）名/カテゴリID
    val junSeaFood = listOf(
        "穴子", "11-78-472",
        "きす", "11-78-840",
        "にしん", "11-78-1497",
        "かれい", "11-78-323",
        "さば", "11-72"
    )
    // 7月の旬の食材（魚介）名/カテゴリID
    val julSeaFood = listOf(
        "穴子", "11-78-472",
        "うなぎ", "11-78-334",
        "きす", "11-78-840",
        "スズキ", "11-78-2029",
        "あじ", "11-73"
    )
    // 8月の旬の食材（魚介）名/カテゴリID
    val augSeaFood = listOf(
        "穴子", "11-78-472",
        "うなぎ", "11-78-334",
        "スズキ", "11-78-2029",
        "はも", "11-78-1501",
        "あじ", "11-73"
    )
    // 9月の旬の食材（魚介）名/カテゴリID
    val sepSeaFood = listOf(
        "あさり", "11-82-60",
        "さけ", "11-70",
        "さんま", "11-75",
        "はも", "11-78-1501",
        "太刀魚", "11-78-2027",
        "あじ", "11-73",
        "いわし", "11-71"
    )
    // 10月の旬の食材（魚介）名/カテゴリID
    val octSeaFood = listOf(
        "あさり", "11-82-60",
        "牡蠣", "11-444",
        "まぐろ", "11-77",
        "さけ", "11-70",
        "さんま", "11-75",
        "ししゃも", "11-78-471",
        "太刀魚", "11-78-2027",
        "ホタテ", "11-82-61",
        "いわし", "11-71",
        "さば", "11-72",
        "たこ", "11-81"
    )
    // 11月の旬の食材（魚介）名/カテゴリID
    val novSeaFood = listOf(
        "あさり", "11-82-60",
        "牡蠣", "11-444",
        "まぐろ", "11-77",
        "さけ", "11-70",
        "ししゃも", "11-78-471",
        "カニ", "11-83",
        "はまぐり", "11-82-63",
        "太刀魚", "11-78-2027",
        "ホタテ", "11-82-61",
        "いわし", "11-71",
        "さば", "11-72",
        "鯛", "11-76",
        "たこ", "11-81"
    )
    // 12月の旬の食材（魚介）名/カテゴリID
    val decSeaFood = listOf(
        "あさり", "11-82-60",
        "牡蠣", "11-444",
        "まぐろ", "11-77",
        "さけ", "11-70",
        "ししゃも", "11-78-471",
        "カニ", "11-83",
        "はまぐり", "11-82-63",
        "ヒラメ", "11-78-1499",
        "ぶり", "11-74",
        "ホタテ", "11-82-61",
        "さば", "11-72",
        "鯛", "11-76",
        "たこ", "11-81",
        "たら", "11-443"
    )

    // 旬の食材（魚介）のカテゴリIDを返す（トレンドタグ用）
    fun gettrendTagSeasonSeaFood(): Pair<String, String> {

        // 現在月の取得
        val month = getToday()

        Log.e("現在月", "${month}")
        // 月ごと
        when (month) {
            1 -> {
                val randomEvenNumberm = (0..5).random() * 2
                return Pair(janSeaFood[randomEvenNumberm], janSeaFood[randomEvenNumberm + 1])
            }
            2 -> {
                val randomEvenNumberm = (0..2).random() * 2
                return Pair(febSeaFood[randomEvenNumberm], febSeaFood[randomEvenNumberm + 1])
            }
            3 -> {
                val randomEvenNumberm = (0..1).random() * 2
                return Pair(marSeaFood[randomEvenNumberm], marSeaFood[randomEvenNumberm + 1])
            }
            4 -> {
                val randomEvenNumberm = (0..4).random() * 2
                return Pair(aprSeaFood[randomEvenNumberm], aprSeaFood[randomEvenNumberm + 1])
            }
            5 -> {
                val randomEvenNumberm = (0..4).random() * 2
                return Pair(maySeaFood[randomEvenNumberm], maySeaFood[randomEvenNumberm + 1])
            }
            6 -> {
                val randomEvenNumberm = (0..4).random() * 2
                return Pair(junSeaFood[randomEvenNumberm], junSeaFood[randomEvenNumberm + 1])
            }
            7 -> {
                val randomEvenNumberm = (0..4).random() * 2
                return Pair(julSeaFood[randomEvenNumberm], julSeaFood[randomEvenNumberm + 1])
            }
            8 -> {
                val randomEvenNumberm = (0..4).random() * 2
                return Pair(augSeaFood[randomEvenNumberm], augSeaFood[randomEvenNumberm + 1])
            }
            9 -> {
                val randomEvenNumberm = (0..6).random() * 2
                return Pair(sepSeaFood[randomEvenNumberm], sepSeaFood[randomEvenNumberm + 1])
            }
            10 -> {
                val randomEvenNumberm = (0..10).random() * 2
                return Pair(octSeaFood[randomEvenNumberm], octSeaFood[randomEvenNumberm + 1])
            }
            11 -> {
                val randomEvenNumberm = (0..12).random() * 2
                return Pair(novSeaFood[randomEvenNumberm], novSeaFood[randomEvenNumberm + 1])
            }
            12 -> {
                val randomEvenNumberm = (0..13).random() * 2
                return Pair(decSeaFood[randomEvenNumberm], decSeaFood[randomEvenNumberm + 1])
            }
            else -> {
                return Pair("error", "error")
            }
        }
    }

    // ====================
    // 汎用処理（現在月の取得）
    // ====================
    // 現在月の取得
    fun getToday(): Int {
        val date = Date()
        val format = SimpleDateFormat("MM", Locale.getDefault())
        return format.format(date).toInt()
    }
}