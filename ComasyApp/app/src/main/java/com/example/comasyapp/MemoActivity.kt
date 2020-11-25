package com.example.comasyapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import kotlinx.android.synthetic.main.activity_member_registration_form.*
import kotlinx.android.synthetic.main.activity_memo.*
import kotlinx.android.synthetic.main.activity_memo_text.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MemoActivity() : AppCompatActivity(), ViewMemoDetailsDialog.ViewMemoDetailsDialogListener,
    Parcelable {

    private lateinit var adapter:CustomRecyclerViewAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        //区切り線
        itemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        memoTitleTextView.addItemDecoration(itemDecoration)

        //LayoutManager
        layoutManager = LinearLayoutManager(this)
        memoTitleTextView.layoutManager = layoutManager

        //Adapter
        adapter = CustomRecyclerViewAdapter(makeRecyclerData())
        memoTitleTextView.adapter = this.adapter

        // 前の画面からメールアドレスと日付を受け取ったら
        if (intent.getStringExtra("mail_address").toString() != null && intent.getStringExtra("datetime").toString() != null) {
            // メモ詳細を表示
            setMemoDetails(intent.getStringExtra("mail_address").toString(), intent.getStringExtra("datetime").toString())
        }

        //追加ボタンを押したときの処理
        MemoAddButton.setOnClickListener {
            //メモ追加画面(MemoTextActivity.kt)に遷移
            val intent = Intent(this, MemoTextActivity::class.java)
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

    // ================
    // メモタイトルを表示
    // ================
    fun makeRecyclerData():MutableList<OneItemMemoTitle> {
        var recyclerData :MutableList<OneItemMemoTitle> = mutableListOf()

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        val handler = Handler()

        // リクエスト先URL
        val url = "http://r02isc2t119.sub.jp/api/memo_check.php"

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

                            // データを1個づつ取り出す
                            for (i in 0 until datas.length()) {

                                // 1レコードをjsonObjectに入れる
                                val zeroJsonObj = datas.getJSONObject(i)

                                // メールアドレスを取得
                                val mail_address = zeroJsonObj.getString("mail_address")
                                // 日付を取得
                                val datetime = zeroJsonObj.getString("datetime")
                                // メモタイトルと取得
                                val memo_title = zeroJsonObj.getString("memo_title")

                                Log.i("取得レコード", "${datetime} ${mail_address} ${memo_title}")

                                var oneitem: OneItemMemoTitle = OneItemMemoTitle()
                                oneitem.textDatetime = datetime
                                oneitem.textMemoTitle = memo_title
                                oneitem.textAddress = mail_address
                                recyclerData.add(oneitem)

                            }
                    }
//                    // レコードがなければ
//                    "no_recode_error" -> {
//                    }
                }
            }
        })
        return recyclerData
    }

    // メモ詳細を表示
    private fun setMemoDetails(mail_address: String, datetime: String) {

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        val handler = Handler()

        // リクエスト先URL
        val url = "http://r02isc2t119.sub.jp/api/memo_get.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("refrigerator_id", now_refrigerator_id)
            .add("mail_address", mail_address)
            .add("datetime", datetime)
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
                        // メモ内容（タイトル）を取得
                        val memo_title = jsonObj.getString("memo_title")
                        // メモ内容（詳細）を取得
                        val memo_contents = jsonObj.getString("memo_contents")

                        Log.i("メモ", "${memo_title} ${memo_contents}")

                        // Bundleのインスタンスを作成する
                        val bundle = Bundle()
                        // Key/Pairの形で値をセットする
                        bundle.putString("KEY_MEMO_TITLE", memo_title)
                        bundle.putString("KEY_MEMO_CONTENTS", memo_contents)

                        // selectNextActionDialogを呼び出す
                        val dialog = ViewMemoDetailsDialog()
                        dialog.setArguments(bundle)
                        dialog.show(supportFragmentManager, "ViewMemoDetailsDialog")
                    }
                }
            }
        })
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun onViewMemoDetailsDialogPositiveClick(dialog: DialogFragment) {
        Log.i("Dialog", "OK!!")
    }

    companion object CREATOR : Parcelable.Creator<MemoActivity> {
        override fun createFromParcel(parcel: Parcel): MemoActivity {
            return MemoActivity(parcel)
        }

        override fun newArray(size: Int): Array<MemoActivity?> {
            return arrayOfNulls(size)
        }
    }
}