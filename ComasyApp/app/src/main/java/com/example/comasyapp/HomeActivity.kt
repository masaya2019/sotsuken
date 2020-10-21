package com.example.comasyapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.transitionMemberRegistrationActivityButton
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 本体からメールアドレスとパスワードを取得
        // https://maku77.github.io/android/fw/shared-preference.html
        val pref = getSharedPreferences("my_password", Context.MODE_PRIVATE)
        val login_mail_address = pref.getString("mail_address", "")
        val login_password = pref.getString("password", "")

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

                        for (i in 0 until datas.length()) {

                            val zeroJsonObj = datas.getJSONObject(i)

                            //
                            val goods_id = zeroJsonObj.getString("goods_id")

                            Log.i("ホームのgoods_id", goods_id)

                            //
                            val category_id = zeroJsonObj.getString("category_id")

                            Log.i("ホームのcategory_id", category_id)

                            //
                            val goods_name = zeroJsonObj.getString("goods_name")

                            Log.i("ホームのgoods_name", goods_name)

                            //
                            val goods_picture_name = zeroJsonObj.getString("goods_picture_name")

                            Log.i("ホームのgoods_picture_name", goods_picture_name)

                            // テーブル作成
                            createTableView(i)
                        }

                        // エラーを表示
                        handler.post {
                            //
                        }
                    }

                }
            }
        })

        RegistrationClickActivityButton.setOnClickListener {
            // Home画面(HomeActivity.kt)へ遷移
            val intent = Intent(this, RegistrationClickActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
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

    // テーブルを生成
    fun createTableView(i : Int) {
        var row = i / 4
        var col = i % 4

        Log.i("テーブルrow", row.toString())
        Log.i("テーブルcol", col.toString())


    }
}
