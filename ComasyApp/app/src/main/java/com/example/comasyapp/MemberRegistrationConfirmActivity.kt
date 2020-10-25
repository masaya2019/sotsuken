package com.example.comasyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_member_registration_confirm.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MemberRegistrationConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_registration_confirm)

        // 前の画面からニックネームを受け取る
        val user_name = intent.getStringExtra("user_name").toString()

        // ニックネームをセットする
        userNameTextView.text = user_name

        // 前の画面からメールアドレスを受け取る
        val mail_address = intent.getStringExtra("mail_address").toString()

        val array = mail_address.split("@")

        val set_mail_address = array[0] + "\n" + "　@" + array[1]

        // メールアドレスをセットする
        mailAddressTextView.text = set_mail_address

        // 前の画面からパスワードを受け取る
        val password = intent.getStringExtra("password").toString()

        // パスワードをセットする
        passwordTextView.text = password

        // 登録ボタン（transitionLoginActivityButton）が押されたら、
        transitionLoginActivityButton.setOnClickListener {

            val handler = Handler()

            // データベースにメールアドレス、ニックネーム、パスワードを登録するＡＰＩ
            memberRegistrationAPI(mail_address, user_name, password, handler, errorTextConfirm)
        }
    }

    // データベースにメールアドレス、ニックネーム、パスワードを登録するＡＰＩ
    private fun memberRegistrationAPI(mail_address: String, user_name:String, password: String, handler: Handler, errorText: TextView) {

        val url = "http://10.0.2.2/sotsuken/api/member_registration.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("mail_address", mail_address)
            .add("user_name", user_name)
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

                Log.i("apiStatus", apiStatus)

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  データベースに登録された場合
                    "yes" -> {
                        // ログイン画面（LoginActivity.kt）へ遷移する
                        val intent = Intent(applicationContext, LoginActivity::class.java)
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
}