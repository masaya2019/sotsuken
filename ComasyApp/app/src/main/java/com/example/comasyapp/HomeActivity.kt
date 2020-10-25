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
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.cat00_btn
import kotlinx.android.synthetic.main.activity_home.cat01_btn
import kotlinx.android.synthetic.main.activity_home.cat02_btn
import kotlinx.android.synthetic.main.activity_home.cat03_btn
import kotlinx.android.synthetic.main.activity_home.cat04_btn
import kotlinx.android.synthetic.main.activity_home.cat05_btn
import kotlinx.android.synthetic.main.activity_home.cat06_btn
import kotlinx.android.synthetic.main.activity_home.cat07_btn
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class HomeActivity : AppCompatActivity(), SelectNextActionDialog.NoticeNextActionDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // デフォルトは全部表示
        viewSearchData("cat00")

//        // 本体からメールアドレスと冷蔵庫IDを削除
//        deleteButton.setOnClickListener {
//            getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
//                clear()
//                commit()
//            }
//        }

        // ============================
        // ＋ボタンをクリックしたときの処理
        // ============================
        selectNextActionButton.setOnClickListener {

            // selectNextActionDialogを呼び出す
            val dialog = SelectNextActionDialog()
            dialog.show(supportFragmentManager, "SelectNextActionDialog")
        }

        // ============================
        // categoryボタンをクリックしたとき
        // ============================
        // ALLボタンをクリックしたら
        cat00_btn.setOnClickListener {

            // 全部表示
            viewSearchData("cat00")
        }
        // 野菜categoryボタンをクリックしたら
        cat01_btn.setOnClickListener {

            // 野菜カテゴリを表示
            viewSearchData("cat01")
        }
        // 飲み物categoryボタンをクリックしたら
        cat02_btn.setOnClickListener {

            // 飲み物カテゴリを表示
            viewSearchData("cat02")
        }
        // 肉類categoryボタンをクリックしたら
        cat03_btn.setOnClickListener {

            // 肉類カテゴリを表示
            viewSearchData("cat03")
        }
        // 魚介categoryボタンをクリックしたら
        cat04_btn.setOnClickListener {

            // 魚介類カテゴリを表示
            viewSearchData("cat04")
        }
        // デザートcategoryボタンをクリックしたら
        cat05_btn.setOnClickListener {

            // デザートカテゴリを表示
            viewSearchData("cat05")
        }
        // 調味料categoryボタンをクリックしたら
        cat06_btn.setOnClickListener {

            // 調味料カテゴリを表示
            viewSearchData("cat06")
        }
        // その他categoryボタンをクリックしたら
        cat07_btn.setOnClickListener {

            // その他カテゴリを表示
            viewSearchData("cat07")
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

    // =======================================
    // 検索結果（カテゴリーorキーワード）を表示するAPI
    // =======================================
    fun viewSearchData(search_data: String) {

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // 画面の横幅サイズを取得
        val w_width = dm.widthPixels

        val handler = Handler()

        // リクエスト先URL
        val url = "http://10.0.2.2/sotsuken/api/response_all_goods.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("search_data", search_data)
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

                        // データを一時的に保存する配列を作成（データ数 X 4）
                        val AllDataArray: Array<String?> = arrayOfNulls(datas.length() * 4)

                        handler.post {

                            //　外側のcontainer部分を取得
                            val linearLayout = findViewById<LinearLayout>(R.id.viewLinearlayoutContainer)

                            // 今あるviewLinearlayoutContainer下のviewを消す
                            linearLayout.removeAllViewsInLayout()

                            // スクロールの一番上に戻す
                            homeScrollView.scrollTo(0,0)

                            // データを1個づつ取り出す
                            for (i in 0 until datas.length()) {

                                // 1レコードをjsonObjectに入れる
                                val zeroJsonObj = datas.getJSONObject(i)

                                // グッズID、グッズ名、グッズ写真名、所有個数を配列に追加
                                AllDataArray[i * 4 + 0] = zeroJsonObj.getString("goods_id")
                                AllDataArray[i * 4 + 1] = zeroJsonObj.getString("goods_name")
                                AllDataArray[i * 4 + 2] = zeroJsonObj.getString("goods_picture_name")
                                AllDataArray[i * 4 + 3] = zeroJsonObj.getString("content_number")

                                // 配列に4つのデータを入れるか配列の最後までデータを入れたら
                                if (i % 4 == 3 || i == datas.length() - 1) {

                                    // 写真用のLinearLayout（HORIZONTAL）を作成
                                    val targetLinearLayout1 = LinearLayout(applicationContext)
                                    targetLinearLayout1.layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    targetLinearLayout1.orientation = LinearLayout.HORIZONTAL
                                    targetLinearLayout1.id = (i / 4) * 6 + 0
                                    linearLayout.addView(targetLinearLayout1)

                                    // テキスト用のLinearLayout（HORIZONTAL）を作成
                                    val targetLinearLayout2 = LinearLayout(applicationContext)
                                    targetLinearLayout2.layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    targetLinearLayout2.orientation = LinearLayout.HORIZONTAL
                                    targetLinearLayout2.id = (i / 4) * 6 + 1
                                    linearLayout.addView(targetLinearLayout2)

                                    // 写真用のcontainerのidを取得
                                    val viewIdFrame =
                                        resources.getIdentifier("${(i / 4) * 6 + 0}", "id", packageName)

                                    // テキスト用のcontainerのidを取得
                                    val viewIdTxt = resources.getIdentifier(
                                        "${(i / 4) * 6 + 1}",
                                        "id",
                                        packageName
                                    )

                                    // フレームレイアウト用のcontainer部分を取得
                                    val linearLayoutFrame = findViewById<LinearLayout>(viewIdFrame)

                                    // テキスト用のcontainer部分を取得
                                    val linearLayoutTxt = findViewById<LinearLayout>(viewIdTxt)

                                    // それぞれのcontainerにグッズ画像、グッズ名を配置（最大４つ）
                                    for (j in i % 4 downTo 0) {

                                        // FrameLayoutを作成
                                        val frameLayout = FrameLayout(applicationContext)
                                        frameLayout.layoutParams = LinearLayout.LayoutParams(w_width / 5, w_width / 5)
                                            .apply { topMargin = 20 }
                                            .apply { rightMargin = w_width / 40 }
                                            .apply { leftMargin = w_width / 40 }
                                        frameLayout.id = (i / 4) * 6 + 5 - j
                                        linearLayoutFrame.addView(frameLayout)

                                        // 写真用のcontainerのidを取得
                                        val viewIdPic =
                                            resources.getIdentifier("${(i / 4) * 6 + 5 - j}", "id", packageName)

                                        // 写真用のcontainer部分を取得
                                        val FrameLayoutPic = findViewById<FrameLayout>(viewIdPic)

                                        // グッズ画像を配置
                                        val imageView = ImageView(applicationContext)
                                        // 仮の画像としてみかん（未完）を配置
                                        imageView.setImageResource(R.drawable.test_pic_mikan)
                                        // 仮のidとしてデータベースから取得したレコードの順番(i - j)に10000を足したものを用意（idのかぶりをなくすため）
                                        imageView.id = 10000 + i - j
                                        FrameLayoutPic.addView(imageView)
                                        imageView.layoutParams =
                                            FrameLayout.LayoutParams(w_width / 5, w_width / 5)
                                        // 画像がクリックされたら
                                        imageView.setOnClickListener {
                                            // その画像のIdを取得
                                            val thisId = imageView.id
                                            Toast.makeText(applicationContext, "${thisId}", Toast.LENGTH_LONG).show()
                                        }

                                        // 数量ボタン
                                        val imageView2 = ImageView(applicationContext)
                                        // 仮の画像と+を配置
                                        imageView2.setImageResource(R.drawable.icon_plus)
                                        FrameLayoutPic.addView(imageView2)
                                        imageView2.layoutParams =
                                            FrameLayout.LayoutParams(w_width / 15, w_width / 15)
                                                .apply { leftMargin = w_width * 2 / 15 }

                                        // グッズ名を配置
                                        val textView = TextView(applicationContext)
                                        textView.text = AllDataArray[(i - j) * 4 + 1]
                                        // 文字数によりテキストサイズを調整する
                                        if (AllDataArray[(i - j) * 4 + 1]!!.length > 7) {
                                            textView.textSize = 10F
                                        } else {
                                            textView.textSize = 12F
                                        }
                                        textView.setTypeface(Typeface.DEFAULT_BOLD)
                                        textView.setTextColor(Color.BLACK)
                                        linearLayoutTxt.addView(textView)
                                        textView.layoutParams =
                                            LinearLayout.LayoutParams(w_width / 4, 80)
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

    // ＋ボタンをクリックしたときの処理（選択された値による）
    override fun onSelectNextActionDialogClick(dialog: DialogFragment, which: Int) {

        Log.i("何する？", which.toString())

        when (which) {
            // 冷蔵庫の写真を登録する処理
            0 -> {
                // Toastを表示
                Toast.makeText(this, "その処理まだできてないんですよー", Toast.LENGTH_LONG).show()
            }
            // 冷蔵庫の中身を登録画面(RegistrationActivity.kt)へ遷移
            1 -> {
                val intent = Intent(this, RegistrationActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }
    }

    override fun onSelectNextActionDialogNegativeClick(dialog: DialogFragment) {
    }
}
