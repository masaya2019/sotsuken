package com.example.comasyapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

            // メールアドレスとパスワードが一致するかを返すＡＰＩにリクエストを送る
            LoginCheck(mailAddress, inputPassword, handler, errortextLogin)

        }
    }

    // メールアドレスとパスワードが一致するかを返すＡＰＩにリクエストを送る
    private fun LoginCheck(mail_address: String, password: String, handler: Handler, errorText: TextView) {

        val url = "http://10.0.2.2/sotsuken/api/login_check.php"

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

//                Log.i("apiStatus", apiStatus)

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
}