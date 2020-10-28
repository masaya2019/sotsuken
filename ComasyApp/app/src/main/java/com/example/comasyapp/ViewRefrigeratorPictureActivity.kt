package com.example.comasyapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.constraintLayout
import kotlinx.android.synthetic.main.activity_view_refrigerator_picture.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ViewRefrigeratorPictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_refrigerator_picture)

        // 前の画面から写真名を受け取る
        val picture_name = intent.getStringExtra("picture_name").toString()

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        // 画面の横幅サイズを取得
        val w_height = dm.heightPixels

        // 画面の横幅サイズを取得
        val w_width = dm.widthPixels

        val handler = Handler()

        viewRefrigeratorPictureMain(handler, picture_name, w_height)

        viewRefrigeratorPicture(handler, w_width, w_height)
    }

    // ======================
    // 冷蔵庫の写真を表示（選択）
    // ======================
    private fun viewRefrigeratorPictureMain(handler: Handler, picture_name: String, w_height: Int) {
        val imageView = ImageView(applicationContext)
        refrigeratorPictureView.addView(imageView)
        // 冷蔵庫の画像を取ってくる
        Picasso.get()
            .load("http://10.0.2.2/sotsuken/api/images/${picture_name}.png")
            .resize(0, w_height)
            .into(imageView)
        imageView.layoutParams =
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        imageView.adjustViewBounds = true
    }

    // ======================
    // 冷蔵庫の写真を表示（一覧）
    // ======================
    private fun viewRefrigeratorPicture(handler: Handler, w_width: Int, w_height: Int) {

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // リクエスト先URL
        val url = "http://10.0.2.2/sotsuken/api/my_refrigerator_picture.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("refrigerator_id", now_refrigerator_id)
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

                // responseのstatusに対応する値（）を取得
                val apiStatus = jsonObj.getString("status")

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  結果がyesなら
                    "yes" -> {
                        //　dataをjson配列に入れる
                        val datas = jsonObj.getJSONArray("data")

                        handler.post {

                            // 今あるpictureLinearLayoutContainer下のviewを消す
                            pictureLinearLayoutContainer2.removeAllViewsInLayout()

                            // スクロールの一番上に戻す
                            pictuteScrollView2.scrollTo(0, 0)

                            val pictureDataArray: Array<String?> = arrayOfNulls(datas.length() * 4)

                            // データを1個づつ取り出す
                            for (i in 0 until datas.length()) {

                                // 1レコードをjsonObjectに入れる
                                val zeroJsonObj = datas.getJSONObject(i)

                                // 写真名
                                val pictureName = zeroJsonObj.getString("refrigerator_picture_name")

                                // 写真名を配列に追加
                                pictureDataArray[i] = pictureName

                                // グッズ画像を配置
                                val imageView = ImageView(applicationContext)
                                // 仮のidとしてデータベースから取得したレコードの順番に20000を足したものを用意（idのかぶりをなくすため）
                                imageView.id = 20000 + i
                                pictureLinearLayoutContainer2.addView(imageView)
                                // 冷蔵庫の画像を取ってくる
                                Picasso.get()
                                    .load("http://10.0.2.2/sotsuken/api/images/${pictureName}.png")
                                    .resize(0, constraintLayout.height)
                                    .into(imageView)
                                imageView.layoutParams =
                                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                                        .apply { rightMargin = w_width /  200 }
                                imageView.adjustViewBounds = true
                                // 画像がクリックされたら
                                imageView.setOnClickListener {
                                    // その画像のIdを取得
                                    val thisId = imageView.id
                                    // 写真名に変換
                                    viewRefrigeratorPictureMain(handler, pictureDataArray[thisId - 20000].toString(), w_height)

                                }
                            }
                        }
                    }
                }
            }
        })
    }
}