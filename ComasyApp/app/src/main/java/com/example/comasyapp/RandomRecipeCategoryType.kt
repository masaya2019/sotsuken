package com.example.comasyapp

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class RandomRecipeCategoryType {

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

    // 季節ごとのカテゴリー(10～14)
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

    // ランダムでカテゴリIDを返す
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
            val month = getToday().toInt()

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

    // 現在月の取得
    fun getToday(): String {
        val date = Date()
        val format = SimpleDateFormat("MM", Locale.getDefault())
        return format.format(date)
    }
}