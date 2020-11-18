package com.example.comasyapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.constraintLayout
import kotlinx.android.synthetic.main.activity_view_refrigerator_picture.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ViewRefrigeratorPictureActivity : AppCompatActivity(), DeleteVerificationDialog.NoticeDeleteVerificationDialogListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_refrigerator_picture)

        // 前の画面から写真名を受け取る
        val picture_name = intent.getStringExtra("picture_name").toString()

        val handler = Handler()

        // 冷蔵庫の写真を表示（選択）
        viewRefrigeratorPictureMain(handler, picture_name)

        // 冷蔵庫の写真を表示（一覧）
        viewRefrigeratorPicture(handler)
    }

    // ===============================
    // 冷蔵庫の写真を表示（選択されたもの）
    // ===============================
    private fun viewRefrigeratorPictureMain(handler: Handler, picture_name: String) {

        // 今あるrefrigeratorPictureView下のviewを消す
        refrigeratorPictureView.removeAllViewsInLayout()

        // 選択された冷蔵庫の写真
        val imageView = ImageView(applicationContext)
        refrigeratorPictureView.addView(imageView)
        // 冷蔵庫の画像を取ってくる
        Picasso.get()
            .load("http://r02isc2t119.sub.jp/api/images/${picture_name}.png")
            .resize(0, get_w_height())
            .into(imageView)
        imageView.layoutParams =
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        imageView.adjustViewBounds = true

        // 閉じるボタン
        val closeIcon = ImageView(applicationContext)
        closeIcon.setImageResource(R.drawable.icon_close)
        refrigeratorPictureView.addView(closeIcon)
        closeIcon.layoutParams = FrameLayout.LayoutParams( 150, 150)
            .apply {
                gravity = Gravity.END
                rightMargin = 8
                topMargin = 8
            }
        closeIcon.setOnClickListener {
            // ホーム画面へ(HomeActivity.kt)遷移
            val intent = Intent(this, HomeActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // サーバーから写真データを取得する
        getRefrigeratorPicture(handler, picture_name)
    }

    // ======================
    // 冷蔵庫の写真を表示（一覧）
    // ======================
    private fun viewRefrigeratorPicture(handler: Handler) {

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // リクエスト先URL
        val url = "http://r02isc2t119.sub.jp/api/my_refrigerator_picture.php"

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
                                        .apply {
                                            leftMargin = get_w_width() / 100
                                        }
                                imageView.adjustViewBounds = true
                                // 画像がクリックされたら
                                imageView.setOnClickListener {
                                    // その画像のIdを取得
                                    val thisId = imageView.id
                                    // 写真名に変換
                                    viewRefrigeratorPictureMain(handler, pictureDataArray[thisId - 20000].toString())
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    // ============================
    // サーバーから写真データを取得する
    // ============================
    private fun getRefrigeratorPicture(handler: Handler, picture_name: String) {

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // リクエスト先URL
        val url = "http://r02isc2t119.sub.jp/api/my_refrigerator_picture.php"

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

                Log.i("yesかな？" , "${apiStatus}")

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  結果がyesなら
                    "yes" -> {
                        //　dataをjson配列に入れる
                        val datas = jsonObj.getJSONArray("data")

                        // 配列の数
                        val array_num = datas.length()

                        handler.post {

                            val pictureDataArray: Array<String?> = arrayOfNulls(array_num * 4)

                            // データを1個づつ取り出し、配列に入れる
                            for (i in 0 until array_num) {

                                // 1レコードをjsonObjectに入れる
                                val zeroJsonObj = datas.getJSONObject(i)

                                // 写真名
                                val pictureName = zeroJsonObj.getString("refrigerator_picture_name")

                                // 写真名を配列に追加
                                pictureDataArray[i] = pictureName
                            }

                            // 選択された写真が配列のどこにあるか
                            var array_position = 0

                            for (i in 0 until array_num) {
                                if (pictureDataArray[i] == picture_name) {
                                    array_position = i
                                    break
                                }
                            }

                            // 次に表示する写真名
                            var next_picture_name = ""

                            Log.i("array_position", array_position.toString())
                            Log.i("array_num", array_num.toString())

                            // 写真が1枚でなく、最後の写真ではない
                            if (array_position != array_num - 1 && array_num != 1) {
                                // 次の写真を表示
                                next_picture_name = pictureDataArray[array_position + 1].toString()

                                // 写真が1枚でなく、最後の写真
                            } else if (array_position == array_num - 1 && array_num != 1) {
                                // 前の写真を表示
                                next_picture_name = pictureDataArray[array_position - 1].toString()
                            }

                            Log.i("次の写真名", next_picture_name)

                            // 先頭のデータでなければ
                            if (array_position != 0) {
                                // 前に戻るボタンを表示
                                val prevIcon = ImageView(applicationContext)
                                prevIcon.setImageResource(R.drawable.icon_prev)
                                refrigeratorPictureView.addView(prevIcon)
                                prevIcon.layoutParams = FrameLayout.LayoutParams( 100, 100)
                                    .apply {
                                        gravity = Gravity.CENTER
                                        rightMargin = get_w_width() / 2 - 75
                                    }
                                prevIcon.setOnClickListener {
                                    // 1個前の冷蔵庫の写真を表示
                                    viewRefrigeratorPictureMain(handler, pictureDataArray[array_position - 1].toString())
                                }
                            }

                            // 最後のデータか、データが1つしかない時以外なら
                            if (array_position != array_num - 1 && array_num != 1) {
                                // 次に進むボタンを表示
                                val nextIcon = ImageView(applicationContext)
                                nextIcon.setImageResource(R.drawable.icon_next)
                                refrigeratorPictureView.addView(nextIcon)
                                nextIcon.layoutParams = FrameLayout.LayoutParams( 100, 100)
                                    .apply {
                                        gravity = Gravity.CENTER
                                        leftMargin = get_w_width() / 2 - 75
                                    }
                                nextIcon.setOnClickListener {
                                    // 1個後の冷蔵庫の写真を表示
                                    viewRefrigeratorPictureMain(handler, pictureDataArray[array_position + 1].toString())
                                }
                            }

                            // ゴミ箱ボタン（冷蔵庫写真の削除）
                            val garbageBoxIcon = ImageView(applicationContext)
                            garbageBoxIcon.setImageResource(R.drawable.icon_garbage_box)
                            refrigeratorPictureView.addView(garbageBoxIcon)
                            garbageBoxIcon.layoutParams = FrameLayout.LayoutParams( 150, 150)
                                .apply {
                                    gravity = Gravity.BOTTOM
                                    leftMargin = get_w_width() - 158
                                    bottomMargin = 8
                                }
                            garbageBoxIcon.setOnClickListener {
                                //　削除確認のAlertDialogを呼び出す！
                                // Bundleのインスタンスを作成する
                                val bundle = Bundle()
                                // Key/Pairの形で値をセットする
                                // 消去する写真
                                bundle.putString("KEY_PICTURE_NAME", picture_name)
                                // 次に表示する写真
                                bundle.putString("KEY_NEXT_PICTURE_NAME", next_picture_name)
                                // Fragmentに値をセットする
                                val dialog = DeleteVerificationDialog()
                                dialog.setArguments(bundle)
                                // DeleteVerificationDialogを表示
                                dialog.show(supportFragmentManager, "DeleteVerificationDialog")
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onDeleteVerificationDialogPositiveClick(
        dialog: DialogFragment,
        picture_name: String,
        next_picture_name: String
    ) {
        Log.i("削除！", picture_name)
        // データベースから写真を削除
        deleteRefrigeratorPicture(picture_name, next_picture_name)
    }

    override fun onDeleteVerificationDialogNegativeClick(dialog: DialogFragment) {
        Log.i("削除！", "キャンセル")
        return
    }

    // ==========================
    // データベースから写真を削除する
    // ==========================
    private fun deleteRefrigeratorPicture(picture_name: String, next_picture_name: String) {

        val handler = Handler()

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // リクエスト先URL
        val url = "http://r02isc2t119.sub.jp/api/delete_my_refrigerator_picture.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("refrigerator_id", now_refrigerator_id)
            .add("picture_name", picture_name)
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

                // responseのstatusに対応する値（）を取得
                val jsonData = JSONObject(response.body()?.string())
                val apiStatus = jsonData.getString("status")

                Log.i("yesかな？", "${apiStatus}")

                when (apiStatus) {
                    // 削除が完了したら
                    "yes" -> {
                        Log.i("削除完了！", picture_name)

                        handler.post {
                            // 次の写真名がセットされていれば
                            if (next_picture_name != "") {
                                // 次の（or前の）画像を表示
                                viewRefrigeratorPictureMain(handler, next_picture_name)

                                // 冷蔵庫の写真を表示（一覧）
                                viewRefrigeratorPicture(handler)

                            } else {
                                // Home画面(HomeActivity.kt)へ遷移
                                val intent = Intent(applicationContext, HomeActivity::class.java)
                                    .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        })
    }

    // 画面の横幅を取得
    private fun get_w_width(): Int {

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        // 画面の横幅サイズを取得
        val w_width = dm.widthPixels

        return w_width
    }

    // 画面の縦幅を取得
    private fun get_w_height(): Int {

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        // 画面の横幅サイズを取得
        val w_height = dm.heightPixels

        return w_height
    }
}