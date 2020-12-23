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
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.constraintLayout
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import kotlinx.android.synthetic.main.activity_recipe_search_result.*
import kotlinx.android.synthetic.main.activity_registration_click.*
import kotlinx.android.synthetic.main.activity_registration_click.inputSearchText
import kotlinx.android.synthetic.main.activity_registration_click.searchImageView
import kotlinx.android.synthetic.main.activity_view_refrigerator_picture.*
import kotlinx.android.synthetic.main.activity_view_refrigerator_picture.pictureLinearLayoutContainer2
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RecipeSearchResultActivity : AppCompatActivity() {

    private lateinit var background: ConstraintLayout

    // キーボード表示を制御するためのオブジェクト
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_search_result)

        // 背景のレイアウトを取得
        background = findViewById(R.id.background)

        // InputMethodManagerを取得
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // レシピタイトルを表示
        makeSearchData()

        // メニューバーをクリックしたときの処理
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

        // ==============================
        // 検索アイコンをクリックしたときの処理
        // ==============================
        searchImageView.setOnClickListener {

            // 仮遷移
            val intent = Intent(this, RecipeSearchResultActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)

//            // 入力された検索キーワードを取得
//            val inputSearchKeyword = inputSearchText.text.toString()
//
//            // 入力された値が空でなければ
//            if (inputSearchKeyword != "") {
//                // 食材、キーワードをDBから検索する
//            }

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(searchImageView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            searchImageView.requestFocus()
        }
    }

    // ================
    // レシピタイトルを表示
    // ================
    fun makeSearchData() {

        val handler = Handler()

//        // アプリID（ディベロッパーID）
//        val applicationId = RakutenRecipeApplicationId().getApplicationId()
//
//        // カテゴリID
//        val categoryId = "10-66-50"
//
//        Log.e("ID確認", applicationId + " " + categoryId)
//
//        // リクエスト先URL
//        val url = "https://app.rakuten.co.jp/services/api/Recipe/CategoryRanking/20170426?format=json&categoryId=${categoryId}&elements=foodImageUrl%2CrecipeTitle%2CrecipeUrl&applicationId=${applicationId}"

        // リクエスト先URL（テスト用）
        val url = "http://r02isc2t119.sub.jp/api/test.json"

        val body = FormBody.Builder(charset("UTF-8"))
//            .add("refrigerator_id", now_refrigerator_id)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            // リクエスト結果受取に失敗
            override fun onFailure(call: Call, e: IOException) {}

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
                    for (i in 0 until datas.length()) {

                        // 1レコードをjsonObjectに入れる
                        val zeroJsonObj = datas.getJSONObject(i)

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

                        // 最初以外は1行空白を入れる
                        if (i != 0) {
                            val textView = TextView(applicationContext)
                            textView.text = ""
                            textView.textSize = 24F
                            recipeContainer.addView(textView)
                        }

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