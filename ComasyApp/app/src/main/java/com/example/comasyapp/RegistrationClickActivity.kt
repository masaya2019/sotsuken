package com.example.comasyapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.util.Log.i
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.cat01btn
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RegistrationClickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_click)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val w_width = dm.widthPixels

        // 最初に冷蔵庫の中身データを受け取りたい！
        val handler = Handler()

//        val url = "http://10.0.2.2/sotsuken/api/testdata.json"
        val url = "http://10.0.2.2/sotsuken/api/response_all_goods.php"

        val body = FormBody.Builder(charset("UTF-8"))
            // 仮のcategory_id
            .add("category_id", "cat01")
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

                        for (i in 0 until datas.length()) {

                            val zeroJsonObj = datas.getJSONObject(i)

                            // グッズID
                            val goods_id = zeroJsonObj.getString("goods_id")

                            // グッズ名
                            val goods_name = zeroJsonObj.getString("goods_name")

                            // グッズ画像
                            val goods_picture_name = zeroJsonObj.getString("goods_picture_name")

                            // エラーを表示
                            handler.post {
                                // 行のcontainer
                                val linearLayout = findViewById<LinearLayout>(R.id.container)

                                // グッズ画像
                                val imageView = ImageView(applicationContext)
                                imageView.setImageResource(R.drawable.icon_plus)
                                imageView.setBackgroundColor(Color.GREEN)
//                                imageView.setColorFilter(Color.GREEN)
                                linearLayout.addView(imageView)
                                imageView.layoutParams = LinearLayout.LayoutParams(w_width / 5, w_width / 5)

                                // グッズ名
                                val textView = TextView(applicationContext)
                                textView.text = goods_name
                                textView.setBackgroundColor(Color.CYAN)
//                                textView.setTextColor(Color.CYAN)
                                linearLayout.addView(textView)
                                textView.layoutParams = LinearLayout.LayoutParams(w_width / 4, LinearLayout.LayoutParams.WRAP_CONTENT)
                                textView.gravity = Gravity.CENTER
                            }

//                            // テーブル作成
//                            createTableView(i)
                        }


                    }

                }
            }
        })

//        // category（野菜）ボタンをクリックしたら(Fragment)
//        cat01btn.setOnClickListener {
//            replaceFragment(CategoryAddFragment())
//        }



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

    //R.id.containerに引数で渡されたフラグメントを入れる。
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    // テーブルを生成
    fun createTableView(i : Int) {
        var row = i / 4
        var col = i % 4

        Log.i("テーブルrow", row.toString())
        Log.i("テーブルcol", col.toString())

    }
}