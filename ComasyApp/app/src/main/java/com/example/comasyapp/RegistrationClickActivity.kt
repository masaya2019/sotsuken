package com.example.comasyapp

import android.content.Intent
import android.graphics.Color
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

        // デフォルトで表示
        viewCategoryData("cat01")

        // category（野菜）ボタンをクリックしたら
        cat01_btn.setOnClickListener {
            //
            viewCategoryData("cat01")
        }
        // category（飲み物）ボタンをクリックしたら
        cat02_btn.setOnClickListener {
            //
            viewCategoryData("cat02")
        }
        // category（肉）ボタンをクリックしたら
        cat03_btn.setOnClickListener {
            //
            viewCategoryData("cat03")
        }
        // category（魚介）ボタンをクリックしたら
        cat04_btn.setOnClickListener {
            //
            viewCategoryData("cat04")
        }
        // category（デザート）ボタンをクリックしたら
        cat05_btn.setOnClickListener {
            //
            viewCategoryData("cat05")
        }
        // category（調味料）ボタンをクリックしたら
        cat06_btn.setOnClickListener {
            //
            viewCategoryData("cat06")
        }
        // category（その他）ボタンをクリックしたら
        cat07_btn.setOnClickListener {
            //
            viewCategoryData("cat07")
        }

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
    }

    // 選択されたcategory_idのデータを表示する
    fun viewCategoryData(category_id: String) {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val w_width = dm.widthPixels

        // 最初に冷蔵庫の中身データを受け取りたい！
        val handler = Handler()

//        val url = "http://10.0.2.2/sotsuken/api/testdata.json"
        val url = "http://10.0.2.2/sotsuken/api/response_all_goods.php"

        val body = FormBody.Builder(charset("UTF-8"))
            // 仮のcategory_id
            .add("category_id", category_id)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {

                // 全体のJSONObjectをとる
                val jsonObj = JSONObject(response.body()?.string())
                // responseのstatusに対応する値（）を取得
                val apiStatus = jsonObj.getString("status")

                Log.i("ホーム画面のapiStatus", apiStatus)

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  データベースに登録された場合
                    "yes" -> {

                        val datas = jsonObj.getJSONArray("data")

                        Log.i("データの長さ", datas.length().toString())

                        // データを一時的に保存する配列
                        val AllDataArray : Array<String?> = arrayOfNulls(datas.length() * 3)

                        handler.post {
                            //
                            val linearLayout = findViewById<LinearLayout>(R.id.container)

                            linearLayout.removeAllViewsInLayout()

                            scrollView.scrollTo(0,0)

                            for (i in 0 until datas.length()) {

                                Log.i("データi", "${i}")

                                val zeroJsonObj = datas.getJSONObject(i)

                                // グッズID
                                val goods_id = zeroJsonObj.getString("goods_id")

                                Log.i("グッズID", goods_id)

                                // グッズ名
                                val goods_name = zeroJsonObj.getString("goods_name")

                                Log.i("グッズ名", goods_name)

                                // グッズ画像
                                val goods_picture_name = zeroJsonObj.getString("goods_picture_name")

                                AllDataArray[i * 3 + 0] = goods_id
                                AllDataArray[i * 3 + 1] = goods_name
                                AllDataArray[i * 3 + 2] = goods_picture_name

                                if (i % 4 == 3 || i == datas.length() - 1) {

                                    val targetLinearLayout1 = LinearLayout(applicationContext)
                                    targetLinearLayout1.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                                    targetLinearLayout1.orientation = LinearLayout.HORIZONTAL
                                    targetLinearLayout1.id = (i / 4) * 2
                                    linearLayout.addView(targetLinearLayout1)

                                    val targetLinearLayout2 = LinearLayout(applicationContext)
                                    targetLinearLayout2.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                                    targetLinearLayout2.orientation = LinearLayout.HORIZONTAL
                                    targetLinearLayout2.id = (i / 4) * 2 + 1
                                    linearLayout.addView(targetLinearLayout2)

                                    val viewIdPic = resources.getIdentifier("${(i / 4) * 2}", "id", packageName)
                                    val viewIdTxt = resources.getIdentifier("${(i / 4) * 2 + 1}", "id", packageName)
                                    // 行のcontainer
                                    val linearLayoutPic = findViewById<LinearLayout>(viewIdPic)
                                    val linearLayoutTxt = findViewById<LinearLayout>(viewIdTxt)

                                    for (j in i % 4 downTo 0) {
                                        // グッズ画像
                                        val imageView = ImageView(applicationContext)
                                        imageView.setImageResource(R.drawable.icon_plus)
                                        imageView.setBackgroundColor(Color.GREEN)
                                        linearLayoutPic.addView(imageView)
                                        imageView.layoutParams = LinearLayout.LayoutParams(w_width / 4, w_width / 5)

                                        // グッズ名
                                        val textView = TextView(applicationContext)
                                        textView.text = AllDataArray[(i - j) * 3 + 1]
                                        textView.setBackgroundColor(Color.CYAN)
                                        // 文字数によりテキストサイズを調整するかどうか
                                        if (AllDataArray[(i - j) * 3 + 1]!!.length > 8) {
                                            textView.textSize = 10F
                                        } else {
                                            textView.textSize = 12F
                                        }
                                        textView.setTextColor(Color.BLACK)
                                        linearLayoutTxt.addView(textView)
                                        textView.layoutParams = LinearLayout.LayoutParams(w_width / 4, 100)
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