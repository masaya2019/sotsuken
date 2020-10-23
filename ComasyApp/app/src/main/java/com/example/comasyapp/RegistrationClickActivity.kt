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
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
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

    private lateinit var background: ConstraintLayout
    private lateinit var categoryBtnContainer: LinearLayout

    // キーボード表示を制御するためのオブジェクト
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_click)

        // 背景のレイアウトを取得
        background = findViewById(R.id.background)
        categoryBtnContainer = findViewById(R.id.categoryBtnContainer)

        // InputMethodManagerを取得
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // デフォルトで野菜categoryを表示
        viewCategoryData("cat01")

        // ============================
        // categoryボタンをクリックしたとき
        // ============================
        // 野菜categoryボタンをクリックしたら
        cat01_btn.setOnClickListener {
            viewCategoryData("cat01")

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(cat01_btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            cat01_btn.requestFocus()
        }
        // 飲み物categoryボタンをクリックしたら
        cat02_btn.setOnClickListener {
            viewCategoryData("cat02")

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(cat02_btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            cat02_btn.requestFocus()
        }
        // 肉categoryボタンをクリックしたら
        cat03_btn.setOnClickListener {
            viewCategoryData("cat03")

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(cat03_btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            cat03_btn.requestFocus()
        }
        // 魚介categoryボタンをクリックしたら
        cat04_btn.setOnClickListener {
            viewCategoryData("cat04")

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(cat04_btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            cat04_btn.requestFocus()
        }
        // デザートcategoryボタンをクリックしたら
        cat05_btn.setOnClickListener {
            viewCategoryData("cat05")

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(cat05_btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            cat05_btn.requestFocus()
        }
        // 調味料categoryボタンをクリックしたら
        cat06_btn.setOnClickListener {
            viewCategoryData("cat06")

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(cat06_btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            cat06_btn.requestFocus()
        }
        // その他categoryボタンをクリックしたら
        cat07_btn.setOnClickListener {
            viewCategoryData("cat07")

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(cat07_btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            cat07_btn.requestFocus()
        }

        // ==============================
        // 検索アイコンをクリックしたときの処理
        // ==============================
        searchImageView.setOnClickListener {
            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(searchImageView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
            // 背景にフォーカスを移す
            searchImageView.requestFocus()
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
                                        // 仮のidとしてデータベースから取得したレコードの順番(i - j)に10000を足したものを用意（idのかぶりをなくすため）
                                        imageView.id = 10000 + i - j
//                                        imageView.setBackgroundColor(Color.GREEN)
                                        linearLayoutPic.addView(imageView)
                                        imageView.layoutParams = LinearLayout.LayoutParams(w_width / 5, w_width / 5)
                                            .apply { topMargin = 20 }
                                            .apply { rightMargin = w_width / 40 }
                                            .apply { leftMargin = w_width / 40 }
                                        // 画像がクリックされたら
                                        imageView.setOnClickListener {
                                            // その画像のIdを取得
                                            val thisId = imageView.id
                                            // 選択された画像のgoods_id
                                            val select_goods_id = AllDataArray[(thisId - 10000) * 3 + 0]
                                            // 選択された画像のgoods_idをToastを表示
                                            Toast.makeText(applicationContext, "${select_goods_id}", Toast.LENGTH_LONG).show()

                                            // Bundleのインスタンスを作成する
                                            val bundle = Bundle()
                                            // Key/Pairの形で値をセットする
                                            bundle.putString("KEY_GOODS_NAME", AllDataArray[(thisId - 10000) * 3 + 1])
                                            // Fragmentに値をセットする
                                            val dialog = AddGoodsQuantityDialog()
                                            dialog.setArguments(bundle)
                                            // AddGoodsQuantityDialogを表示
                                            dialog.show(supportFragmentManager, "NumberPickerDialog")
                                        }

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

    // 画面タップ時に呼ばれる
    override fun onTouchEvent(event: MotionEvent): Boolean {

        // キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(background.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)

        // 背景にフォーカスを移す
        background.requestFocus()

        return false
    }

}