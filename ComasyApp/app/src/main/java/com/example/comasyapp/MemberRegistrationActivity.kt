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
import kotlinx.android.synthetic.main.activity_member_registration.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log


class MemberRegistrationActivity : AppCompatActivity() {

    private lateinit var background: ConstraintLayout

    // キーボード表示を制御するためのオブジェクト
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_registration)

        // 背景のレイアウトを取得
        background = findViewById(R.id.background)

        // InputMethodManagerを取得
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // 仮登録ボタン（transitionMemberRegistrationFormActivityButton）をクリックしたら、
        transitionMemberRegistrationFormActivityButton.setOnClickListener {

            val handler = Handler()

            // 入力されたメールアドレスを取得
            val mailAddress = inputTextTextEmailAddress.text.toString()

            // メールアドレスから認証コードを生成し、認証コードをメールで送るAPIにリクエストを投げる
            sendMailApi(mailAddress, handler, errorTextPre)
        }
    }


    // メールアドレスから認証コードを生成し、認証コードをメールで送るAPIにリクエストを投げる
    private fun sendMailApi(mail_address: String, handler: Handler, errorText: TextView) {


        val url = "http://10.0.2.2/sotsuken/api/pre_registration.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("mail_address", mail_address)
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

                    //  アドレスが正常で、メールを送った場合
                    "yes" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "認証コードを\n${mail_address}に送りました！"
                        }
                        // 会員登録画面詳細（MemberRegistrationFormActivity.kt）へ遷移する
                        val intent = Intent(applicationContext, MemberRegistrationFormActivity::class.java)
                        // メールアドレスを渡す
                        intent.putExtra("mail_address", mail_address)
                            .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
                    // すでに登録されているアドレスの場合
                    "used_address_error" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "${mail_address}は既に登録されています！\n別のアドレスを利用してください"
                        }
                    }
                    // メールアドレスのに形式になっていない場合
                    "address_type_error" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "メールアドレスの形式ではありません！\nもう一度入力してください！"
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