package com.example.comasyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_member_registration_form.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MemberRegistrationFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_registration_form)

        // 次へボタン（transitionMemberRegistrationConfirmActivityButton）をクリックしたら、
        transitionMemberRegistrationConfirmActivityButton.setOnClickListener {
            // パスワードが4文字以上 かつ ニックネームが入力されている　かつ 認証コードが数字かつ4文字
            if (checkNickname() && checkPassword() && checkToken()) {

                // 前の画面からメールアドレスを受け取る
                val mail_address = intent.getStringExtra("mail_address").toString()

                // 認証コードを取得し、数字に変換
                val inputToken = inputToken.text.toString().toInt()

                val handler = Handler()

                // メールアドレスと認証コードをチェックするAPIにリクエストを投げる
                checkTokenApi(mail_address, inputToken, handler, errorTextForm)
            }
        }
    }

    // ニックネームが入力されているか？
    private fun checkNickname(): Boolean {
        // ニックネームが入力されている
        return if (inputUserName.text.toString().isNotEmpty()) {
            errorTextForm.text = "ニックネームok"

            true
            // ニックネームが入力されている
        } else {
            errorTextForm.text = "ニックネームを入力してください！"

            false
        }
    }

    // パスワードが4文字以上かつ一致するか？
    private fun checkPassword(): Boolean {
        // 入力されたパスワードを取得
        val inputPassword = inputPassword.text.toString()

        // 入力されたパスワード（再）を取得
        val inputReEnterPassword = inputReEnterPassword.text.toString()

        // 入力されたパスワードと入力されたパスワード（再）が4文字以下
        if (inputPassword.length < 4 || inputReEnterPassword.length < 4) {
            errorTextForm.text = "パスワードの文字数が不足しています！"

            return false

            // 入力されたパスワードと入力されたパスワード（再）が等しいかつ4文字以上
        } else if (inputPassword == inputReEnterPassword && inputPassword.length >= 4 && inputReEnterPassword.length >= 4) {
            errorTextForm.text = "パスワード一致！"

            return true

            // パスワードが不一致
        } else {
            errorTextForm.text = "パスワードが一致しません！\nもう一度入力してください！"

            return false
        }
    }

    // 認証コードが数字かつ4文字
    private fun checkToken(): Boolean {
        // 入力された認証コードを取得
        val inputToken = inputToken.text.toString()
        // 文字数が4文字か？
        when {
            // 認証コードが4文字
            inputToken.length == 4 -> {
                errorTextForm.text = "認証コードが入力されています！"
                return true
            }
            // 認証コードが未入力
            inputToken.isEmpty() -> {
                errorTextForm.text = "認証コードが入力されていません！\n認証コードを入力してください！"
                return false
            }
            // 認証コードの文字数が異なる
            else -> {
                errorTextForm.text = "認証コードは4文字です！\nもう一度入力してください！"
                return false
            }
        }
    }

    // メールアドレスと認証コードをチェックするAPIにリクエストを投げる
    // 結果はjson形式で
    // メールアドレスアドレスが正常で、認証コードが合っている場合　{ "status" : "yes" }
    // メールアドレスアドレスが正常で、認証コードが違っている場合　{ "status" : "different_token_error" }
    // メールアドレスアドレスがデータベースにない場合　{ "status" : "no_address_error" }
    // メールアドレスのに形式になっていない場合　{ "status" :　"address_type_error" }
    // 認証コードの文字数が違う場合　{ "status" : "token_error" }
    // を受け取る
    private fun checkTokenApi(mail_address: String, token: Int , handler: Handler, errorText: TextView) {

        val url = "http://10.0.2.2/sotsuken/api/token_check.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("mail_address", mail_address)
            .add("token", token.toString())
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

                    // メールアドレスアドレスが正常で、認証コードが合っている場合
                    "yes" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "認証コードが一致しました！"
                        }
                        // 新規会員登録確認画面（MemberRegistrationConfirmActivity.kt）へ遷移する
                        val intent = Intent(applicationContext, MemberRegistrationConfirmActivity::class.java)
                        // メールアドレスを渡す
                        intent.putExtra("mail_address", mail_address)
                        // ニックネームを渡す
                        intent.putExtra("user_name", inputUserName.text.toString())
                        // パスワードを渡す
                        intent.putExtra("password", inputPassword.text.toString())
                        startActivity(intent)
                    }
                    // メールアドレスアドレスが正常で、認証コードが違っている場合
                    "different_token_error" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "認証コードが違います！\nもう一度入力してください！"
                        }
                    }

                    // APIの返す値だが、使用されない
//                    // メールアドレスアドレスがデータベースにない場合
//                    "no_address_error" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = "このアドレスはデータベースに存在しません"
//                        }
//                    }
//                    // メールアドレスのに形式になっていない場合
//                    "address_type_error" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = "メールアドレスの形式ではありません！"
//                        }
//                    }
//                    // 認証コードの文字数が違う場合
//                    "token_error" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = "認証コードの文字数が違います"
//                        }
//                    }
                }
            }
        })
    }

}