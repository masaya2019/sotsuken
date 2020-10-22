package com.example.comasyapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import kotlinx.android.synthetic.main.activity_registration_click.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RegistrationClickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_click)

        // デフォルトで野菜categoryを表示
        viewCategoryData("cat01")

        // ============================
        // categoryボタンをクリックしたとき
        // ============================
        // 野菜categoryボタンをクリックしたら
        cat01_btn.setOnClickListener {
            viewCategoryData("cat01")
        }
        // 飲み物categoryボタンをクリックしたら
        cat02_btn.setOnClickListener {
            viewCategoryData("cat02")
        }
        // 肉categoryボタンをクリックしたら
        cat03_btn.setOnClickListener {
            viewCategoryData("cat03")
        }
        // 魚介categoryボタンをクリックしたら
        cat04_btn.setOnClickListener {
            viewCategoryData("cat04")
        }
        // デザートcategoryボタンをクリックしたら
        cat05_btn.setOnClickListener {
            viewCategoryData("cat05")
        }
        // 調味料categoryボタンをクリックしたら
        cat06_btn.setOnClickListener {
            viewCategoryData("cat06")
        }
        // その他categoryボタンをクリックしたら
        cat07_btn.setOnClickListener {
            viewCategoryData("cat07")
        }

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
    }

    // ====================================
    // 選択されたcategory_idのデータを表示する
    // ====================================
    fun viewCategoryData(category_id: String) {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        // 画面の横幅サイズを取得
        val w_width = dm.widthPixels

        val handler = Handler()

        // リクエスト先URL
        val url = "http://10.0.2.2/sotsuken/api/response_all_goods.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("category_id", category_id)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            // リクエスト結果受取を失敗
            override fun onFailure(call: Call, e: IOException) {}

            // リクエスト結果受取に成功
            override fun onResponse(call: Call, response: Response) {

                // 全体のJSONObjectをとる
                val jsonObj = JSONObject(response.body()?.string())

                // responseのstatusに対応する値（）を取得
                val apiStatus = jsonObj.getString("status")

//                Log.i("ホーム画面のapiStatus", apiStatus)

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  結果がyesなら
                    "yes" -> {
                        //　dataをjson配列に入れる
                        val datas = jsonObj.getJSONArray("data")

//                        Log.i("データの長さ", datas.length().toString())

                        // データを一時的に保存する配列を作成（データ数 X 3）
                        val AllDataArray : Array<String?> = arrayOfNulls(datas.length() * 3)

                        handler.post {
                            //　外側のcontainer部分を取得
                            val linearLayout = findViewById<LinearLayout>(R.id.container)

                            // 今あるcontainer下のviewを消す
                            linearLayout.removeAllViewsInLayout()

                            // スクロールの一番上に戻す
                            scrollView.scrollTo(0,0)

                            // データを1個づつ取り出す
                            for (i in 0 until datas.length()) {

//                                Log.i("データi", "${i}")

                                // 1レコードをjsonObjectに入れる
                                val zeroJsonObj = datas.getJSONObject(i)

                                // グッズID、グッズ名、グッズ写真名を配列に追加
                                AllDataArray[i * 3 + 0] = zeroJsonObj.getString("goods_id")
                                AllDataArray[i * 3 + 1] = zeroJsonObj.getString("goods_name")
                                AllDataArray[i * 3 + 2] = zeroJsonObj.getString("goods_picture_name")

                                // 配列に4つのデータを入れるか配列の最後までデータを入れたら
                                if (i % 4 == 3 || i == datas.length() - 1) {

                                    // 写真用のLinearLayout（HORIZONTAL）を作成
                                    val targetLinearLayout1 = LinearLayout(applicationContext)
                                    targetLinearLayout1.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                                    targetLinearLayout1.orientation = LinearLayout.HORIZONTAL
                                    targetLinearLayout1.id = (i / 4) * 2
                                    linearLayout.addView(targetLinearLayout1)

                                    // テキスト用のLinearLayout（HORIZONTAL）を作成
                                    val targetLinearLayout2 = LinearLayout(applicationContext)
                                    targetLinearLayout2.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                                    targetLinearLayout2.orientation = LinearLayout.HORIZONTAL
                                    targetLinearLayout2.id = (i / 4) * 2 + 1
                                    linearLayout.addView(targetLinearLayout2)

                                    // 写真用のcontainerのidを取得
                                    val viewIdPic = resources.getIdentifier("${(i / 4) * 2}", "id", packageName)

                                    // テキスト用のcontainerのidを取得
                                    val viewIdTxt = resources.getIdentifier("${(i / 4) * 2 + 1}", "id", packageName)

                                    // 写真用のcontainer部分を取得
                                    val linearLayoutPic = findViewById<LinearLayout>(viewIdPic)

                                    // テキスト用のcontainer部分を取得
                                    val linearLayoutTxt = findViewById<LinearLayout>(viewIdTxt)

                                    // それぞれのcontainerにグッズ画像、グッズ名を配置（最大４つ）
                                    for (j in i % 4 downTo 0) {
                                        // グッズ画像を配置
                                        val imageView = ImageView(applicationContext)
                                        // 仮の画像としてみかん（未完）を配置
                                        imageView.setImageResource(R.drawable.test_pic_mikan)
//                                        imageView.setBackgroundColor(Color.GREEN)
                                        linearLayoutPic.addView(imageView)
                                        imageView.layoutParams = LinearLayout.LayoutParams(w_width / 5, w_width / 5)
                                            .apply { topMargin = 20 }
                                            .apply { rightMargin = w_width / 40 }
                                            .apply { leftMargin = w_width / 40 }


                                        // グッズ名を配置
                                        val textView = TextView(applicationContext)
                                        textView.text = AllDataArray[(i - j) * 3 + 1]
//                                        textView.setBackgroundColor(Color.CYAN)
                                        // 文字数によりテキストサイズを調整する
                                        if (AllDataArray[(i - j) * 3 + 1]!!.length > 7) {
                                            textView.textSize = 10F
                                        } else {
                                            textView.textSize = 12F
                                        }
                                        textView.setTypeface(Typeface.DEFAULT_BOLD)
                                        textView.setTextColor(Color.BLACK)
                                        linearLayoutTxt.addView(textView)
                                        textView.layoutParams = LinearLayout.LayoutParams(w_width / 4, 80)
                                        textView.gravity = Gravity.CENTER
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }

}