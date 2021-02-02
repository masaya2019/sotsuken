package com.example.comasyapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import kotlinx.android.synthetic.main.activity_recipe_search.*
import kotlinx.android.synthetic.main.activity_recipe_search_result.recipeContainer
import kotlinx.android.synthetic.main.activity_recipe_search_result.scrollView2
import kotlinx.android.synthetic.main.activity_registration_click.searchImageView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RecipeSearchActivity : AppCompatActivity() {

    private lateinit var background: ConstraintLayout

    // キーボード表示を制御するためのオブジェクト
    private lateinit var inputMethodManager: InputMethodManager

    // トレンドタグのカテゴリIDを初期設定
    private var trendTagCategoryType1 = ""
    private var trendTagCategoryType2 = ""
    private var trendTagCategoryType3 = ""
    private var trendTagCategoryType4 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_search)

        // 背景のレイアウトを取得
        background = findViewById(R.id.background)

        // InputMethodManagerを取得
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // トレンドタグの表示（４つ）
        makeTrendTags()

        // おすすめレシピを表示（１つ）
        makePickUpData()

        // ==============================
        // メニューバーをクリックしたときの処理
        // ==============================
        transitionRefrigeratorButton.setOnClickListener {
            // Home画面(HomeActivity.kt)へ遷移
            val intent = Intent(this, HomeActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionColumnButton.setOnClickListener {
            // コラム画面(ColumnActivity.kt)へ遷移
            val intent = Intent(this, ColumnActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionSearchButton.setOnClickListener {
            // レシピ検索画面(RecipeSearchActivity.kt)へ遷移
            val intent = Intent(this, RecipeSearchActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionMemoButton.setOnClickListener {
            // メモ画面(MemoActivity.kt)へ遷移
            val intent = Intent(this, MemoActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionSettingButton.setOnClickListener {
            // 設定画面(SettingActivity.kt)へ遷移
            val intent = Intent(this, SettingActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // =====================================
        // 登録アイテムから探すをクリックしたときの処理
        // =====================================
        searchRefrigeratorItemText.setOnClickListener {
            Log.e("確認", "登録アイテム")
        }

        // ==============================
        // トレンドタグをクリックしたときの処理
        // ==============================
        // トレンドタグ１（今日の献立）
        trendTag1.setOnClickListener {
            Log.e("確認", trendTagCategoryType1)
            // レシピ一覧表示へ遷移
            val intent = Intent(this, RecipeSearchResultActivity::class.java)
            // 検索ワードを渡す
            intent.putExtra("selectTag", trendTagCategoryType1)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        // トレンドタグ2（季節のイベント）
        trendTag2.setOnClickListener {
            Log.e("確認", trendTagCategoryType2)
            // レシピ一覧表示へ遷移
            val intent = Intent(this, RecipeSearchResultActivity::class.java)
            // 検索ワードを渡す
            intent.putExtra("selectTag", trendTagCategoryType2)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        // トレンドタグ3（旬の食材（野菜））
        trendTag3.setOnClickListener {
            Log.e("確認", trendTagCategoryType3)
            // レシピ一覧表示へ遷移
            val intent = Intent(this, RecipeSearchResultActivity::class.java)
            // 検索ワードを渡す
            intent.putExtra("selectTag", trendTagCategoryType3)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        // トレンドタグ4（旬の食材（魚介））
        trendTag4.setOnClickListener {
            Log.e("確認", trendTagCategoryType4)
            // レシピ一覧表示へ遷移
            val intent = Intent(this, RecipeSearchResultActivity::class.java)
            // 検索ワードを渡す
            intent.putExtra("selectTag", trendTagCategoryType4)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // ==============================
        // 検索アイコンをクリックしたときの処理
        // ==============================
        searchImageView.setOnClickListener {

            // 入力された検索キーワードを取得
            val inputSearchKeyword = inputSearchText.text.toString()

            // 入力された値が空でなければ
            if (inputSearchKeyword != "") {
                // 食材、キーワードをDB検索する（遷移）
                val intent = Intent(this, RecipeSearchResultActivity::class.java)
                // 検索ワードを渡す
                intent.putExtra("inputSearchKeyword", inputSearchKeyword)
                    .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(searchImageView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            searchImageView.requestFocus()
        }
    }

    // ===============
    // トレンドタグを表示
    // ===============
    private fun makeTrendTags() {

        // トレンドタグ１（今日の献立）
        trendTag1.text = "今日の献立"
        trendTagCategoryType1 = "38"

        // トレンドタグ2（季節のイベント）
        val trendtag2 = GetRecipeCategoryType().gettrendTagSeasonEvent()
        trendTag2.text = trendtag2.first
        trendTagCategoryType2 = trendtag2.second

        // トレンドタグ3（旬の食材（野菜））
        val trendtag3 = GetRecipeCategoryType().gettrendTagSeasonVegetableFood()
        trendTag3.text = trendtag3.first
        trendTagCategoryType3 = trendtag3.second

        // トレンドタグ4（旬の食材（魚介））
        val trendtag4 = GetRecipeCategoryType().gettrendTagSeasonSeaFood()
        trendTag4.text = trendtag4.first
        trendTagCategoryType4 = trendtag4.second
    }

    // ===================
    // PICK　UP　レシピを表示
    // ===================
    fun makePickUpData() {

        val handler = Handler()

        // アプリID（ディベロッパーID）
        val applicationId = RakutenRecipeApplicationId().getApplicationId()

        // カテゴリID
        val categoryId = GetRecipeCategoryType().getrecipeId()

        Log.e("カテゴリID", categoryId)

        Log.e("ID確認", applicationId + " " + categoryId)

        // リクエスト先URL
        val url = "https://app.rakuten.co.jp/services/api/Recipe/CategoryRanking/20170426?format=json&categoryId=${categoryId}&elements=foodImageUrl%2CrecipeTitle%2CrecipeUrl&applicationId=${applicationId}"

        Log.e("url", url)

//        // リクエスト先URL（テスト用）
//        val url = "http://r02isc2t119.sub.jp/api/test.json"

        // トップ表示するレシピをランダムで決める
        val randomNumber = (0..3).random()

        Log.e("random", randomNumber.toString())

        val body = FormBody.Builder(charset("UTF-8"))
//            .add("refrigerator_id", now_refrigerator_id)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            // リクエスト結果受取に失敗
            override fun onFailure(call: Call, e: IOException) {
                Log.e("エラー", "失敗")
                Log.e("カテゴリID", categoryId)
                handler.post {
                    // 今あるrecipeContainer下のviewを消す
                    recipeContainer.removeAllViewsInLayout()

                    // スクロールの一番上に戻す
                    scrollView2.scrollTo(0, 0)

                    // レシピタイトルを配置
                    val textViewTitle = TextView(applicationContext)
                    textViewTitle.text = "\nレシピの読み込みに失敗しました\n"
                    textViewTitle.textSize = 24F
                    textViewTitle.gravity = Gravity.CENTER
                    textViewTitle.setTypeface(Typeface.DEFAULT_BOLD)
                    textViewTitle.setTextColor(Color.BLACK)
                    recipeContainer.addView(textViewTitle)
                }
            }

            // リクエスト結果受取に成功
            override fun onResponse(call: Call, response: Response) {

                // 全体のJSONObjectをとる
                val jsonObj = JSONObject(response.body()?.string())

                //　dataをjson配列に入れる
                val datas = jsonObj.getJSONArray("result")

                handler.post {

                    // 今あるrecipeContainer下のviewを消す
                    recipeContainer.removeAllViewsInLayout()

                    // スクロールの一番上に戻す
                    scrollView2.scrollTo(0, 0)

                    // データを1個づつ取り出す
                    // 1レコードをjsonObjectに入れる
                    val zeroJsonObj = datas.getJSONObject(randomNumber)

                    // レシピ画像を取得
                    val foodImageUrl = zeroJsonObj.getString("foodImageUrl")
                    // レシピ名を取得
                    val recipeTitle = zeroJsonObj.getString("recipeTitle")
                    // レシピURLを取得
                    val recipeUrl = zeroJsonObj.getString("recipeUrl")

                    Log.i(
                        "取得レコード",
                        "${foodImageUrl} ${recipeTitle} ${recipeUrl}"
                    )

                    // レシピタイトルを配置
                    val textViewTitle = TextView(applicationContext)
                    textViewTitle.text = "\n▼▼　PICK　UP　レシピ　▼▼"
                    textViewTitle.textSize = 24F
                    textViewTitle.gravity = Gravity.CENTER
                    textViewTitle.setTypeface(Typeface.DEFAULT_BOLD)
                    textViewTitle.setTextColor(Color.RED)
                    recipeContainer.addView(textViewTitle)

                    // レシピタイトルを配置
                    val textView = TextView(applicationContext)
                    textView.text = recipeTitle
                    textView.textSize = 24F
                    textView.setTypeface(Typeface.DEFAULT_BOLD)
                    textView.setTextColor(Color.BLACK)
                    recipeContainer.addView(textView)
                    // テキストがクリックされたら
                    textView.setOnClickListener {
                        Log.e("URL", recipeUrl)
                        // 楽天レシピのページを開く
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(recipeUrl))
                        startActivity(intent)
                    }

                    // レシピ画像を配置
                    val imageView = ImageView(applicationContext)
                    recipeContainer.addView(imageView)
                    // レシピの画像を取ってくる
                    Picasso.get()
                        .load(foodImageUrl)
                        .resize(0, background.width)
                        .into(imageView)
                    imageView.layoutParams =
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    imageView.adjustViewBounds = true
                    // 画像がクリックされたら
                    imageView.setOnClickListener {
                        Log.e("URL", recipeUrl)
                        // 楽天レシピのページを開く
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(recipeUrl))
                        startActivity(intent)
                    }
                }
            }
        })
    }

    // 画面タップ時に呼ばれる
    override fun onTouchEvent(event: MotionEvent): Boolean {

        // キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(background.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)

        // 背景にフォーカスを移す
        background.requestFocus()

        return false
    }
}