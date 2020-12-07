package com.example.comasyapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private lateinit var background: ConstraintLayout

    // キーボード表示を制御するためのオブジェクト
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        // 削除ボタン（テスト用）が押されたとき
//        deleteButton.setOnClickListener {
//            // 本体からメールアドレスと冷蔵庫IDを削除
//            getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
//                clear()
//                commit()
//            }
//        }

//        // 削除ボタンを間違って押したとき（復旧用①）
//        getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
//            putString("mail_address", "bjmk1290313@gn.iwasaki.ac.jp")
//            putString("refrigerator_id", "r0001")
//            commit()
//        }

//        // 削除ボタンを間違って押したとき（復旧用②）
//        getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
//            putString("mail_address", "info.comasy@gmail.com")
//            putString("refrigerator_id", "r0007")
//            commit()
//        }

//        getSharedPreferences("my_password", Context.MODE_PRIVATE).edit().apply {
//            clear()
//            commit()
//        }

        // 背景のレイアウトを取得
        background = findViewById(R.id.background)

        // InputMethodManagerを取得
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // 本体からメールアドレスとパスワードを取得
        // https://maku77.github.io/android/fw/shared-preference.html
        val pref = getSharedPreferences("my_password", Context.MODE_PRIVATE)
        val login_mail_address = pref.getString("mail_address", "").toString()
        val login_password = pref.getString("password", "").toString()

        // 本体にログイン情報（メールアドレスとパスワード）が保存されていたら、
        if (login_mail_address != "" && login_password != "") {

            val handler = Handler()

            // メールアドレスとパスワードが一致するかを返すＡＰＩにリクエストを送る
            LoginCheck(login_mail_address, login_password, handler, errortextLogin)
        }

        // 新規会員登録はこちらボタン（transitionMemberRegistrationActivityButton）をクリックしたら、
        transitionMemberRegistrationActivityButton.setOnClickListener {
            // 新規会員登録画面（MemberRegistrationActivity.kt）へ遷移する
            val intent = Intent(this, MemberRegistrationActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // ログインボタン（transitionHomeActivityButton）をクリックしたら、
        transitionHomeActivityButton.setOnClickListener {

            // 入力されたメールアドレスを取得
            val mailAddress = inputEmailAddress.text.toString()

            // 入力されたパスワードを取得
            val inputPassword = inputPassword.text.toString()

            val handler = Handler()

            Log.e("確認", mailAddress + " " + inputPassword)

            // メールアドレスとパスワードが一致するかを返すＡＰＩにリクエストを送る
            LoginCheck(mailAddress, inputPassword, handler, errortextLogin)
        }
    }

    // メールアドレスとパスワードが一致するかを返すＡＰＩにリクエストを送る
    private fun LoginCheck(mail_address: String, password: String, handler: Handler, errorText: TextView) {

        val url = "http://r02isc2t119.sub.jp/api/login_check.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("mail_address", mail_address)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {

                // responseのstatusに対応する値（）を取得
                val jsonData = JSONObject(response.body()?.string())
                val apiStatus = jsonData.getString("status")

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  データベースに登録された場合
                    "yes" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "ログインします！"
                        }
                        // 本体にメールアドレスとパスワードを保存
                        saveUserData(mail_address, password)

                        // refrigerator_idが保存されていなかったら、mail_addressと対応するrefrigerator_idを発行し、冷蔵庫IDを本体に登録する
                        addRefrigeratorId(mail_address)
                    }
//                    // 以下はエラー用に仮作成
//                    // えらー１
//                    "" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = ""
//                        }
//                    }
//                    // えらー２
//                    "" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = ""
//                        }
//                    }
                }
            }
        })
    }

    // refrigerator_idが保存されていなかったら、mail_addressと対応するrefrigerator_idを発行し、登録するAPIにリクエストを送る
    private fun addRefrigeratorId(mail_address: String) {
        // 本体からmail_addressとrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val login_mail_address = pref.getString("mail_address", "").toString()
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // もし、refrigerator_idが保存されていなかったら
        if (login_mail_address == "" && now_refrigerator_id == "") {

            val url = "http://r02isc2t119.sub.jp/api/create_refrigerator_id.php"

            val body = FormBody.Builder(charset("UTF-8"))
                .add("mail_address", mail_address)
                .build()

            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            OkHttpClient().newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {}

                // レスポンスが帰ってきたら、
                override fun onResponse(call: Call, response: Response) {

                    // responseのstatusに対応する値（）を取得
                    val jsonData = JSONObject(response.body()?.string())
                    val apiStatus = jsonData.getString("status")

                    // responseのstatusによって次の画面に進むorエラーを表示する
                    when (apiStatus) {

                        //  データベースに登録された場合
                        "yes" -> {

                            // refrigerator_idを取得
                            val refrigerator_id = jsonData.getString("refrigerator_id")

                            // 本体に冷蔵庫情報（メールアドレスと冷蔵庫ID）を保存
                            saveRefrigeratorData(mail_address, refrigerator_id)

                            // ホーム画面（HomeActivity.kt）へ遷移する
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            startActivity(intent)
                        }
//                    // 以下はエラー用に仮作成
//                    // えらー１
//                    "" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = ""
//                        }
//                    }
//                    // えらー２
//                    "" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = ""
//                        }
//                    }
                    }
                }
            })
            // 保存されていたら、
        } else {
            // ホーム画面（HomeActivity.kt）へ遷移する
            val intent = Intent(applicationContext, HomeActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }


    // 本体にログイン情報（メールアドレスとパスワード）を保存
    // https://maku77.github.io/android/fw/shared-preference.html
    fun saveUserData(mail_address: String, password: String) {
        getSharedPreferences("my_password", Context.MODE_PRIVATE).edit().apply {
            putString("mail_address", mail_address)
            putString("password", password)
            commit()
        }
    }

    // 本体に冷蔵庫情報（メールアドレスと冷蔵庫ID）を保存
    fun saveRefrigeratorData(mail_address: String, refrigerator_id: String) {
        getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
            putString("mail_address", mail_address)
            putString("refrigerator_id", refrigerator_id)
            commit()
        }
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